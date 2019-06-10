package adver.example.adver.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *09.06.2019
 */
public class AddAdvers {
    @NotNull(message="Виберіть city.")
    private City city ;
    @NotNull(message="Виберіть category.")
    private Category category ;
    @NotNull( message="Виберіть status.")
    private Status status ;
    @NotEmpty(message="Виберіть textAdver")
    private String textAdver;
    @NotNull( message="Виберіть місто.dataStop")
    // @Temporal(TemporalType.DATE)
//private String dataStop;
    public AddAdvers() {
    }

//    public Date getDataStop() {
//        return dataStop;
//    }
//
//    public void setDataStop(Date dataStop) {
//        this.dataStop = dataStop;
//    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTextAdver() {
        return textAdver;
    }

    public void setTextAdver(String textAdver) {
        this.textAdver = textAdver;
    }
}
