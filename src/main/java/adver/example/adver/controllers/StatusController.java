package adver.example.adver.controllers;


import adver.example.adver.DTO.StutusDto;
import adver.example.adver.models.Status;
import adver.example.adver.repos.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin_status/status")
public class StatusController {
    final StatusRepository statusRepo;

    @Autowired
    public StatusController(StatusRepository statusRepo) {
        this.statusRepo = statusRepo;
    }

    @GetMapping
    public List<Status> getListStatus(){
        StutusDto ST=new StutusDto((List<Status>) statusRepo.findAll());
        return ST.getListStatus();
    }

    @GetMapping("{id}")
    public Status getOne(@PathVariable("id") Status status) {
        return status;
    }

    @PostMapping
    public Status createStatus(@RequestBody Status status) { ;
        return statusRepo.save(status);

    }

    @PutMapping("{id}")
    public Status updateStatus(@RequestBody Status newStatus, @PathVariable int id) {

        return statusRepo.findById(id)
                .map(Status -> {
                    Status.setName(newStatus.getName());
                    return statusRepo.save(Status);
                }).get();

    }

    @DeleteMapping("{id}")
    public void deleteStatus(@PathVariable("id") Status status) {
        statusRepo.delete(status);
    }
}