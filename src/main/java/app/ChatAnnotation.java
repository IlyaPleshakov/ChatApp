package app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;


@ServerEndpoint(value = "/websocket/chat/{roomNum}")
public class ChatAnnotation {
    private static Logger log = Logger.getLogger(ChatAnnotation.class.getName());
    private static final Map<String, CopyOnWriteArraySet<ChatAnnotation>> connections =
            new HashMap<>();
    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private String nickname;
    private Session session;

    private static final String MSG_USER_LIST = "{\"type\":\"userslist\",\"users\":%s}";

    public ChatAnnotation() {

    }

    @OnOpen
    public void open(@PathParam("roomNum") String roomNum, Session session) {
        this.session = session;
        nickname=session.getUserPrincipal().getName();
        if (connections.containsKey(roomNum)) {
            connections.get(roomNum).add(this);
        } else {
            CopyOnWriteArraySet<ChatAnnotation> webSocketSet = new CopyOnWriteArraySet<>();
            webSocketSet.add(this);
            connections.put(roomNum, webSocketSet);
        }
        String message = String.format("* %s %s",nickname, "has joined.");
        String usersmessage = getMSG_USER_LIST(roomNum);
        broadcast(roomNum, message);
        broadcast(roomNum, usersmessage);

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
        broadcast(roomNum, message);
    }


    @OnError
    public void onError(Throwable t) throws Throwable {

        log.info("Chat Error: " + t.toString());
    }


    private static void broadcast(String roomNum, String msg) {
        CopyOnWriteArraySet<ChatAnnotation> set = connections.get(roomNum);
        for (ChatAnnotation client : set) {
            try {
                synchronized (client) {
                    client.session.getBasicRemote().sendText(msg);
                    log.info("message delivered");
                }
            } catch (IOException e) {
                log.info("Chat Error: Failed to send message to client");
                connections.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                    log.info("Wrong closing WS session");
                }
                String message = String.format("* %s %s",
                        client.nickname, "has been disconnected.");
                broadcast(roomNum, message);
            }
        }
    }


    private static String getMSG_USER_LIST(String roomNum) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        CopyOnWriteArraySet<String> setUsers = new CopyOnWriteArraySet<>();
        Iterator<ChatAnnotation> iterator = connections.get(roomNum).iterator();

        while (iterator.hasNext()) {
            setUsers.add(iterator.next().nickname);
        }
        String listUsers = gson.toJson(setUsers);
        String usersmessage = String.format(MSG_USER_LIST, listUsers);
        System.out.println("users="+usersmessage);

        return usersmessage;
    }


}
