package example.mqy.messagelayout;

/**
 * Created by MQY on 2016/3/22.
 */
public class Message {
    public static final int SEND = 0;
    public static final int RECEIVE = 1;
    public String content;
    private int type;
    public Message(String content,int type){
        this.content = content;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
