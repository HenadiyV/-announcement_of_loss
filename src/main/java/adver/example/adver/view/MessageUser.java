package adver.example.adver.view;

import adver.example.adver.models.User;

import javax.validation.constraints.NotEmpty;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *10.06.2019
 */
public class MessageUser {
    private User user;

    @NotEmpty(message="Не вказано текст повідомленя .")
    private  String textMessage;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
}
