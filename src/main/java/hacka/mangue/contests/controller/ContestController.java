package hacka.mangue.contests.controller;

import hacka.mangue.contests.domain.models.Contest;
import hacka.mangue.contests.service.ContestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/contests")
public class ContestController {

    private final ContestService contestService;

    public ContestController(ContestService contestService) { this.contestService = contestService; }

    @GetMapping("/{id}")
    public ResponseEntity<Contest> findById(@PathVariable Long id){
        var contest = contestService.findById(id);
        return ResponseEntity.ok(contest);
    }

    @GetMapping
    public ResponseEntity<List<Contest>> findAll(){
        List<Contest> allContests = contestService.findAll();
        return ResponseEntity.ok(allContests);
    }

    @PostMapping
    public ResponseEntity<Contest> create(@RequestBody Contest contestToCreate) {
        var contestCreated = contestService.create(contestToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contestCreated.getId()).toUri();
        return ResponseEntity.created(location).body(contestCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contest> update(@PathVariable Long id, @RequestBody Contest contestToUpdate) {
        var updatedContest = contestService.update(id, contestToUpdate);
        return ResponseEntity.ok(updatedContest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contestService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
