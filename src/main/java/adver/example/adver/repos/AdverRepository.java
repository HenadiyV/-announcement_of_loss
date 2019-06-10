package adver.example.adver.repos;

import adver.example.adver.models.Adver;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdverRepository extends CrudRepository<Adver, Integer> {
    List<Adver> findByStatus_Id(int id);
    List<Adver> findById(int id);
    List<Adver> findByStatusName(String name);
    List<Adver> findByCity_IdAndCategory_IdAndStatus_Id(int city,int category,int id);
    List<Adver> findByCity_IdAndStatus_Id(int city,int id);
    List<Adver> findByCategory_IdAndStatus_Id(int category,int id);

}
