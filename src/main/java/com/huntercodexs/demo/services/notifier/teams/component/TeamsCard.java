package com.huntercodexs.demo.services.notifier.teams.component;


import com.huntercodexs.demo.services.notifier.teams.model.adaptivecard.Actions;
import com.huntercodexs.demo.services.notifier.teams.model.adaptivecard.AdaptiveCardFacts;
import com.huntercodexs.demo.services.notifier.teams.model.adaptivecard.ColumnsSet;
import com.huntercodexs.demo.services.notifier.teams.model.messagecard.Facts;
import com.huntercodexs.demo.services.notifier.teams.model.messagecard.PotentialAction;

import java.util.List;

public interface TeamsCard {

    String teamsWebhook();

    String notifierCard(String title, String subtitle, String image);

    String messageCard(
            String theme,
            String summary,
            String title,
            String subtitle,
            String image,
            List<Facts> facts,
            List<PotentialAction> actions);

    String adaptiveCard(
            String payload,
            String bodyTitle,
            List<AdaptiveCardFacts> facts,
            List<Actions> actions,
            String fontColor);

    String adaptiveCardWithImage(
            String payload,
            String bodyTitle,
            String imageUrl,
            String altText,
            List<AdaptiveCardFacts> facts,
            List<Actions> actions,
            String fontColor);

    String adaptiveCardWithHeader(
            String payload,
            String bodyTitle,
            List<AdaptiveCardFacts> facts,
            List<ColumnsSet> columns,
            List<Actions> actions,
            String fontColor);

    String adaptiveCardWithHeaderAndImage(
            String payload,
            String bodyTitle,
            String imageUrl,
            String textAbout,
            List<AdaptiveCardFacts> facts,
            List<ColumnsSet> columns,
            List<Actions> actions,
            String fontColor);

}
