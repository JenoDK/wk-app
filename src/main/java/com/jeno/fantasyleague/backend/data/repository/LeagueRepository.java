package com.jeno.fantasyleague.backend.data.repository;

import com.jeno.fantasyleague.backend.model.League;
import com.jeno.fantasyleague.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {

	List<League> findByUsers(User user);

	@Query("SELECT l FROM League l INNER JOIN FETCH l.users WHERE l.id = :id")
	Optional<League> findByIdAndJoinUsers(@Param("id") Long leagueId);

	@Query("SELECT l.users FROM League l WHERE l.id = :id")
	List<User> fetchLeagueUsers(@Param("id") Long leagueId);

	@Query("SELECT l.owners FROM League l WHERE l.id = :id")
	List<User> fetchLeagueOwners(@Param("id") Long leagueId);

}