public class Message {

    private String content;

    public void createMessage() { DatabaseHandler db;}
    public void getMessage() {}
    public void updateMessage() {}
    public void deleteMessage() {}
}


class MessageController {

    public Message sendMessage(String content, Account sender, Account receiver) { return new Message();}

}