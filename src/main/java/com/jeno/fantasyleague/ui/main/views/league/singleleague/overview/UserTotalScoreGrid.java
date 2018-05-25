package com.jeno.fantasyleague.ui.main.views.league.singleleague.overview;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.jeno.fantasyleague.data.service.leaguetemplates.worldcup2018.FifaWorldCup2018Stages;
import com.jeno.fantasyleague.resources.Resources;
import com.jeno.fantasyleague.ui.common.grid.CustomGrid;
import com.jeno.fantasyleague.ui.common.grid.CustomGridBuilder;
import com.jeno.fantasyleague.util.ImageUtil;
import com.vaadin.data.provider.GridSortOrderBuilder;

public class UserTotalScoreGrid extends CustomGrid<UserTotalScoreBean> {

	public UserTotalScoreGrid(List<UserTotalScoreBean> items) {
		super();
		initColumns();

		setItems(items);
	}

	private void initColumns() {
		addIconColumn(
				new CustomGridBuilder.ColumnProvider<>(
						"userIcon",
						userTotalScoreBean -> new CustomGridBuilder.IconColumnValue(ImageUtil.getUserProfilePictureResource(userTotalScoreBean.getUser())),
						""));
		addColumn(userTotalScoreBean -> userTotalScoreBean.getUser().getUsername())
				.setCaption(Resources.getMessage("username"))
				.setId("userName");
		Arrays.stream(FifaWorldCup2018Stages.values())
				.sorted(Comparator.comparingInt(FifaWorldCup2018Stages::getSeq))
				.forEach(stage -> {
					addColumn(bean -> OverviewUtil.getScoreFormatted(bean.getScore(stage)))
							.setCaption(Resources.getMessage(stage.getName()))
							.setId(stage.getName());
				});
		Column<UserTotalScoreBean, String> userScoreColumn =
				addColumn(bean -> OverviewUtil.getScoreFormatted(bean.getTotalScore()))
						.setCaption(Resources.getMessage("totalScore"))
						.setId("totalScore");
		setSortOrder(new GridSortOrderBuilder().thenDesc(userScoreColumn));
	}

}
