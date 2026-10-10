package com.election.backend.controller;

import com.election.backend.repository.CandidateRepository;
import com.election.backend.repository.ElectionRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final CandidateRepository candidateRepository;
    private final ElectionRepository electionRepository;

    public DashboardController(CandidateRepository candidateRepository, ElectionRepository electionRepository) {
        this.candidateRepository = candidateRepository;
        this.electionRepository = electionRepository;
    }

    @GetMapping("/dashboard")
    public Map<String, Long> getDashboardSummary() {
        Map<String, Long> summary = new HashMap<>();
        summary.put("candidates", candidateRepository.count());
        summary.put("elections", electionRepository.count());
        return summary;
    }
}
