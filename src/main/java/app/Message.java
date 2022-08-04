package app;

public class Message {

    private String id;
    private String text;
    @Override
    public String toString() {
        return id+":"+text;
    }

    public String getAuthor() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Message(String text, String id){
        this.id=id;
        this.text=text;
    }
}
