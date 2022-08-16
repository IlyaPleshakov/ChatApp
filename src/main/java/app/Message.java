package app;

public class Message {

    private final String id;
    private final String text;
    private final String type;
    @Override
    public String toString() {
        return id+":"+text+" type:"+type;
    }

    public String getAuthor() {
        return id;
    }

    public String getText() {
        return text;
    }
    public String getType() {
        return type;
    }

    public Message(String text, String id, String type){
        this.id=id;
        this.text=text;
        this.type=type;
    }
}
