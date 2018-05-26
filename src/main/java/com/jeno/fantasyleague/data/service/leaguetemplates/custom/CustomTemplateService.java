package com.jeno.fantasyleague.data.service.leaguetemplates.custom;

import java.util.Map;

import com.google.common.collect.Maps;
import com.jeno.fantasyleague.data.service.leaguetemplates.LeagueSettingRenderer;
import com.jeno.fantasyleague.data.service.leaguetemplates.LeagueTemplateService;
import com.jeno.fantasyleague.data.service.leaguetemplates.TemplateException;
import com.jeno.fantasyleague.data.service.leaguetemplates.worldcup2018.FifaWorldCup2018Stages;
import com.jeno.fantasyleague.model.League;
import com.jeno.fantasyleague.model.Prediction;
import com.jeno.fantasyleague.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomTemplateService implements LeagueTemplateService {

	@Autowired
	private CustomSettingRenderer customSettingRenderer;

	@Override
	public void run(League newLeague, User user) throws TemplateException {

	}

	@Override
	public LeagueSettingRenderer getLeagueSettingRenderer() {
		return customSettingRenderer;
	}

	@Override
	public Map<FifaWorldCup2018Stages, Double> calculateTotalUserScore(League league, User user) {
		return Maps.newHashMap();
	}

	@Override
	public double calculateScoreOfPrediction(League league, Prediction prediction, User user) {
		return 0;
	}

}