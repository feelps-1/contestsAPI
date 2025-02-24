package hacka.mangue.contests.controller;

import hacka.mangue.contests.controller.dto.ContestDTO;
import hacka.mangue.contests.service.ContestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/contests")
@Tag(name = "Contests Controller", description = "RESTful API for managing contests.")
public class ContestController {

    private final ContestService contestService;

    public ContestController(ContestService contestService) {
        this.contestService = contestService;
    }

    @GetMapping
    @Operation(summary = "Get all contests", description = "Retrieve a list of all registered contests")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful")
    })
    public ResponseEntity<List<ContestDTO>> findAll() {
        var contests = contestService.findAll();
        var contestsDto = contests.stream()
                .map(ContestDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(contestsDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a contest by ID", description = "Retrieve a specific contest based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "Contest not found")
    })
    public ResponseEntity<ContestDTO> findById(@PathVariable Long id) {
        var contest = contestService.findById(id);
        return ResponseEntity.ok(new ContestDTO(contest));
    }

    @PostMapping
    @Operation(summary = "Create a new contest", description = "Create a new contest and return the created contest's data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contest created successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid contest data provided")
    })
    public ResponseEntity<ContestDTO> create(@RequestBody ContestDTO contestDto) {
        var contest = contestService.create(contestDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(contest.getId())
                .toUri();
        return ResponseEntity.created(location).body(new ContestDTO(contest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a contest", description = "Update the data of an existing contest based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contest updated successfully"),
            @ApiResponse(responseCode = "404", description = "Contest not found"),
            @ApiResponse(responseCode = "422", description = "Invalid contest data provided")
    })
    public ResponseEntity<ContestDTO> update(@PathVariable Long id, @RequestBody ContestDTO contestDto) {
        var contest = contestService.update(id, contestDto.toModel());
        return ResponseEntity.ok(new ContestDTO(contest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a contest", description = "Delete an existing contest based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Contest deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Contest not found")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contestService.delete(id);
        return ResponseEntity.noContent().build();
    }
}