package com.jeno.fantasyleague.backend.data.service.leaguetemplates.worldcup2018;

import java.util.List;
import java.util.Map;

import com.jeno.fantasyleague.backend.data.service.leaguetemplates.LeagueSettingRenderer;
import com.jeno.fantasyleague.backend.data.service.leaguetemplates.LeagueTemplateService;
import com.jeno.fantasyleague.backend.data.service.leaguetemplates.TemplateException;
import com.jeno.fantasyleague.backend.data.service.repo.league.UserLeagueScore;
import com.jeno.fantasyleague.backend.model.ContestantWeight;
import com.jeno.fantasyleague.backend.model.League;
import com.jeno.fantasyleague.backend.model.Prediction;
import com.jeno.fantasyleague.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
public class FifaWorldCup2018Service implements LeagueTemplateService {

	@Autowired
	private FifaWorldCup2018SettingRenderer fifaWorldCup2018SettingRenderer;
	@Autowired
	private FifaWorldCup2018Initializer fifaWorldCup2018Initializer;
	@Autowired
	private FifaWorldCup2018ScoreHelper fifaWorldCup2018ScoreHelper;

	@Override
	public LeagueSettingRenderer getLeagueSettingRenderer() {
		return fifaWorldCup2018SettingRenderer;
	}

	@Override
	public List<UserLeagueScore> calculateTotalUserScores(League league) {
		return fifaWorldCup2018ScoreHelper.calculateTotalUserScores(league);
	}

	@Override
	public double calculateScoreOfPrediction(League league, Prediction prediction, User user) {
		return fifaWorldCup2018ScoreHelper.calculateScoreOfPrediction(league, prediction, user);
	}

	@Override
	public Map<Long, Double> calculateScoresForUser(
			League league,
			List<Prediction> predictionsWithJoinedGames,
			List<ContestantWeight> contestantWeights,
			User user) {
		return fifaWorldCup2018ScoreHelper.calculateScoresForUser(league, predictionsWithJoinedGames, contestantWeights, user);
	}

	@Override
	public void run(League newLeague, User user) throws TemplateException {
		fifaWorldCup2018Initializer.addNewLeague(newLeague, user);
	}

}