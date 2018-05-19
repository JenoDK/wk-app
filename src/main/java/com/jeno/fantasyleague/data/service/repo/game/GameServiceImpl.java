package com.jeno.fantasyleague.data.service.repo.game;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jeno.fantasyleague.data.repository.ContestantRepository;
import com.jeno.fantasyleague.data.repository.GameRepository;
import com.jeno.fantasyleague.model.Contestant;
import com.jeno.fantasyleague.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private ContestantRepository contestantRepository;

	@Override
	public void updateGroupStageGameScores(List<Game> games) {
		games.stream().forEach(this::distributePointsAndUpdateGame);
		gameRepository.saveAll(games);
		contestantRepository.saveAll(games.stream()
				.flatMap(game -> Stream.of(game.getHome_team(), game.getAway_team()))
				.collect(Collectors.toList()));
	}

	private void distributePointsAndUpdateGame(Game game) {
		Contestant homeTeam = game.getHome_team();
		Integer homeScore = game.getHome_team_score();
		Contestant awayTeam = game.getAway_team();
		Integer awayScore = game.getAway_team_score();
		homeTeam.setGoals_in_group(homeTeam.getGoals_in_group() + homeScore);
		awayTeam.setGoals_in_group(awayTeam.getGoals_in_group() + awayScore);
		// Home won
		if (homeScore > awayScore) {
			homeTeam.setPoints_in_group(homeTeam.getPoints_in_group() + 3);
			game.setWinner(homeTeam);
		// Draw
		} else if (homeScore == awayScore) {
			homeTeam.setPoints_in_group(homeTeam.getPoints_in_group() + 1);
			awayTeam.setPoints_in_group(homeTeam.getPoints_in_group() + 1);
		// Away won
		} else {
			awayTeam.setPoints_in_group(homeTeam.getPoints_in_group() + 3);
			game.setWinner(awayTeam);
		}
	}
}
