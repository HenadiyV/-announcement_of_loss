package adver.example.adver.repos;

import adver.example.adver.models.Message;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Integer> {
   // List<Message> findByTo_Id(boolean activ,int id);
    List<Message> findById(int id);
    List <Message> findByFrom_Id(int id);
    List <Message> findByTo_Id(int id);
}
