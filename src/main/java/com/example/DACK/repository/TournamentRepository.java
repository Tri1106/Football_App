package com.example.DACK.repository;

import com.example.DACK.model.Tournament;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    @Override
    @EntityGraph(attributePaths = "field")
    List<Tournament> findAll();

    @Override
    @EntityGraph(attributePaths = "field")
    Optional<Tournament> findById(Long id);
}
