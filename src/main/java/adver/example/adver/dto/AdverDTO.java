package adver.example.adver.dto;

import adver.example.adver.models.Adver;

import java.util.ArrayList;
import java.util.List;

/*
 *@autor Hennadiy Voroboiv
 *@email henadiyv@gmail.com
 *06.07.2019
 */
public class AdverDTO {
    private Adver adver;
    List<Adver> adverList=new ArrayList<>();
    public AdverDTO(){}
    public AdverDTO(List<Adver> advList){
        if(advList.size()<1) return;
        for(Adver adv: advList){
            Adver ad=new Adver();
            ad.setId(adv.getId());
            ad.setCategory(adv.getCategory());
            ad.setTextAdver(adv.getTextAdver());
            ad.setDataStop(adv.getDataStop());
            ad.setDataStart(adv.getDataStart());
            ad.setCity(adv.getCity());
            ad.setStatus(adv.getStatus());
            ad.setPhoto(adv.getPhoto());
            ad.setUser(adv.getUser());
            adverList.add(ad);
        }
    }
    public List<Adver> getListAdver(){return adverList;}
}
