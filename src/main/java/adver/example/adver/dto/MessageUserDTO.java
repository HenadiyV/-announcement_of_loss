package adver.example.adver.dto;

import adver.example.adver.models.User;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *10.06.2019
 */
public class MessageUserDTO {
private int toUser;
    private String fromUser;
    @NotEmpty(message="Не вказано текст повідомленя .")
    private  String textMessage;

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public int getToUser() {
        return toUser;
    }

    public void setToUser(int toUser) {
        this.toUser = toUser;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }
}
