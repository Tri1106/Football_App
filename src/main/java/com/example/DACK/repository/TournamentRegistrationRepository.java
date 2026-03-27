package com.example.DACK.repository;

import com.example.DACK.model.TournamentRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRegistrationRepository extends JpaRepository<TournamentRegistration, Long> {
    List<TournamentRegistration> findByTournamentId(Long tournamentId);
    List<TournamentRegistration> findByTeamId(Long teamId);
}
