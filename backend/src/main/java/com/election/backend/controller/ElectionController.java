package com.election.backend.controller;

import com.election.backend.model.Election;
import com.election.backend.repository.ElectionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ElectionController {

    private final ElectionRepository electionRepository;

    public ElectionController(ElectionRepository electionRepository) {
        this.electionRepository = electionRepository;
    }

    @GetMapping("/elections")
    public List<Election> getAllElections() {
        return electionRepository.findAll();
    }

    @GetMapping("/elections/{id}")
    public ResponseEntity<Election> getElectionById(@PathVariable Long id) {
        return electionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/elections")
    public ResponseEntity<Election> createElection(@RequestBody Election election) {
        Election savedElection = electionRepository.save(election);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedElection);
    }
}
