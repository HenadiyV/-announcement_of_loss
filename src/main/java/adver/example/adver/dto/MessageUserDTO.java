package adver.example.adver.dto;

import adver.example.adver.models.User;

import javax.validation.constraints.NotEmpty;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *10.06.2019
 */
public class MessageUserDTO {
   // private User user;
private int idUser;
    @NotEmpty(message="Не вказано текст повідомленя .")
    private  String textMessage;

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
