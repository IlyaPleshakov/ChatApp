package app;

public class Message {

    private String text;
    private String author;
    @Override
    public String toString() {
        return author+":"+text;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public Message(String text, String author){
        this.author=author;
        this.text=text;
    }
}
