package app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;


@ServerEndpoint(value = "/websocket/chat/{roomNum}")
public class ChatAnnotation {
    private static final Map<String,CopyOnWriteArraySet <ChatAnnotation>> connections =
            new HashMap<>();
    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private final String nickname;
    private Session session;
    String MSG_USER_LIST  = "{\"type\":\"userslist\",\"users\":%s}";
    public ChatAnnotation() {

        nickname = "guest№"+ connectionIds.getAndIncrement();
    }

    @OnOpen
    public void open(@PathParam("roomNum") String roomNum, Session session) {
        this.session = session;
        if (connections.containsKey(roomNum)) {
            connections.get(roomNum).add(this);
        } else {
            CopyOnWriteArraySet<ChatAnnotation> webSocketSet = new CopyOnWriteArraySet<>();
            webSocketSet.add(this);
            connections.put(roomNum, webSocketSet);
        }
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        CopyOnWriteArraySet<String> setUsers = new CopyOnWriteArraySet<>();
        Iterator<ChatAnnotation> iterator = connections.get(roomNum).iterator();

        while (iterator.hasNext())
        {
            setUsers.add(iterator.next().nickname);
        }
        String listUsers=gson.toJson(setUsers);
        String usersmessage=String.format(MSG_USER_LIST, listUsers);
        System.out.println(usersmessage);
        String message = String.format("* %s %s", nickname, "has joined.");
        broadcast(roomNum, message);
        broadcast(roomNum,usersmessage);

    }


    @OnClose
    public void close(@PathParam("roomNum") String roomNum) {
        CopyOnWriteArraySet<ChatAnnotation> set = connections.get(roomNum);
        set.remove(this);
        String message = String.format("* %s has disconnected from chat room N%s",
                nickname, roomNum);
        broadcast(roomNum, message);
    }


    @OnMessage
    public void incoming(@PathParam("roomNum") String roomNum, String message) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Message msg = gson.fromJson(message, Message.class);
        // Never trust the client
        System.out.println("MESSAGE="+message);
        String filteredMessage = String.format("%s: %s",
                nickname, message);
        broadcast(roomNum, message);
    }




    @OnError
    public void onError(Throwable t) throws Throwable {
        //log.error("Chat Error: " + t.toString(), t);
    }


    private static void broadcast(String roomNum,String msg) {
        CopyOnWriteArraySet<ChatAnnotation> set = connections.get(roomNum);
        for (ChatAnnotation client : set) {
            try {
                synchronized (client) {
                    client.session.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
                //log.debug("Chat Error: Failed to send message to client", e);
                connections.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                    // Ignore
                }
                String message = String.format("* %s %s",
                        client.nickname, "has been disconnected.");
                broadcast(roomNum,message);
            }
        }
    }

}
