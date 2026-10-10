package com.election.backend;

import com.election.backend.model.Candidate;
import com.election.backend.model.Election;
import com.election.backend.repository.CandidateRepository;
import com.election.backend.repository.ElectionRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataSeeder implements ApplicationRunner {

    private final CandidateRepository candidateRepository;
    private final ElectionRepository electionRepository;

    public DataSeeder(CandidateRepository candidateRepository, ElectionRepository electionRepository) {
        this.candidateRepository = candidateRepository;
        this.electionRepository = electionRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (candidateRepository.count() == 0) {
            candidateRepository.save(new Candidate(null, "Aisha Rahman", "National Progressive Party", "Central District", "Improving public health and digital access for every citizen.", "https://images.unsplash.com/photo-1494790108377-be9c29b29330?auto=format&fit=crop&w=800&q=80"));
            candidateRepository.save(new Candidate(null, "Daniel Lewis", "People's Unity Alliance", "Northern District", "Ensuring affordable housing and better education infrastructure.", "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?auto=format&fit=crop&w=800&q=80"));
            candidateRepository.save(new Candidate(null, "Sofia Nguyen", "Green Future Party", "Eastern District", "Supporting clean energy, youth employment, and transparent governance.", "https://images.unsplash.com/photo-1487412720507-e7ab37603c6f?auto=format&fit=crop&w=800&q=80"));
        }

        if (electionRepository.count() == 0) {
            electionRepository.save(new Election(null, "National Assembly Elections 2026", "General election for constituency representatives and civic participation.", LocalDate.now().plusDays(30), "Scheduled"));
        }
    }
}
