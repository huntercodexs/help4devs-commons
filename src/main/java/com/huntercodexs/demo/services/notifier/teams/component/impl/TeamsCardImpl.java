package com.huntercodexs.demo.services.notifier.teams.component.impl;

import com.google.gson.Gson;
import com.huntercodexs.demo.services.notifier.teams.component.TeamsCard;
import com.huntercodexs.demo.services.notifier.teams.model.adaptivecard.*;
import com.huntercodexs.demo.services.notifier.teams.model.messagecard.Facts;
import com.huntercodexs.demo.services.notifier.teams.model.messagecard.MessageCardLayout;
import com.huntercodexs.demo.services.notifier.teams.model.messagecard.PotentialAction;
import com.huntercodexs.demo.services.notifier.teams.model.messagecard.Sections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.huntercodexs.demo.services.notifier.teams.constant.TeamsFeature.WRAP_TRUE;

@Component
public class TeamsCardImpl implements TeamsCard {

    @Value("${tier.webhook.teams:}")
    public String teamsWebhook;

    private String layoutFix(String input) {
        return input
                .replaceAll("[\n\t\r]", "")
                .replace("\"schema\":", "\"$schema\":")
                .replace("{\"bodyColumSet\":{", "{")
                .replace(",\"bodyColumSet\":", ",")
                .replace("{\"imageObject\":{", "{")
                .replace(",\"imageObject\":", ",")
                .replace("{\"bodyTitle\":{", "{")
                .replace(",\"bodyTitle\":", ",")
                .replace("{\"textObject\":{", "{")
                .replace(",\"textObject\":", ",")
                .replace("\"bodyFactsSet\":", "")
                .replace("\"bodyPayload\":", "")
                .replace("\"bodySourcePayload\":", "")
                .replace("\"bodyActions\":", "")
                .replaceAll("}]}}]}$", "]}}]}");
    }

    private String imageFix(String path) {
        if (path != null && path.matches("^http(s?)://.*(.jpg|.png|.jpeg|.bmp|.gif)$")) {
            return path;
        }
        return null;
    }

    @Override
    public String teamsWebhook() {
        return this.teamsWebhook;
    }

    @Override
    public String notifierCard(
            String title,
            String subtitle,
            String image
    ) {

        List<Sections> sectionsList = new ArrayList<>();
        sectionsList.add(Sections
                .builder()
                .activityTitle(title)
                .activitySubtitle(subtitle)
                .activityImage(image)
                .facts(null)
                .build());

        Gson gson = new Gson();
        return gson.toJson(MessageCardLayout
                .builder()
                .summary("Notifier")
                .sections(sectionsList)
                .potentialAction(null)
                .build());
    }

    @Override
    public String messageCard(
            String theme,
            String summary,
            String title,
            String subtitle,
            String image,
            List<Facts> facts,
            List<PotentialAction> actions
    ) {
        List<Sections> sectionsList = new ArrayList<>();
        sectionsList.add(Sections
                .builder()
                        .activityTitle(title)
                        .activitySubtitle(subtitle)
                        .activityImage(image)
                        .facts(facts)
                .build());

        Gson gson = new Gson();
        return gson.toJson(MessageCardLayout
                        .builder()
                        .themeColor(theme)
                        .summary(summary)
                        .sections(sectionsList)
                        .potentialAction(actions)
                        .build())
                .replace("\"type\":", "\"@type\":")
                .replace("\"context\":", "\"@context\":");
    }

    @Override
    public String adaptiveCard(
            String payload,
            String bodyTitle,
            List<AdaptiveCardFacts> facts,
            List<Actions> actions,
            String fontColor
    ) {
        List<Attachments> attachments = new ArrayList<>();

        List<BodyContent> body = new ArrayList<>();
        body.add(BodyContent
                .builder()
                    .bodyTitle(BodyTitle.builder().text(bodyTitle).color(fontColor).build())
                    .bodyFactsSet(BodyFactsSet.builder().facts(facts).build())
                    .bodyPayload(BodyPayload.builder().color(fontColor).build())
                    .bodySourcePayload(BodySourcePayload.builder().text(payload).build())
                    .bodyActions(BodyActionSet.builder().actions(actions).build())
                .build());

        attachments.add(Attachments
                .builder()
                .content(Content
                        .builder()
                        .msteams(MSTeams.builder().build())
                        .body(body)
                        .build())
                .build());

        return layoutFix(new Gson().toJson(AdaptiveCardLayout
                        .builder()
                        .attachments(attachments)
                        .build()));
    }

    @Override
    public String adaptiveCardWithImage(
            String payload,
            String bodyTitle,
            String imageUrl,
            String altText,
            List<AdaptiveCardFacts> facts,
            List<Actions> actions,
            String fontColor
    ) {
        imageUrl = imageFix(imageUrl);

        List<Attachments> attachments = new ArrayList<>();

        List<BodyContent> body = new ArrayList<>();
        body.add(BodyContent
                .builder()
                    .imageObject(ImageObject.builder().url(imageUrl).altText(altText).build())
                    .bodyTitle(BodyTitle.builder().text(bodyTitle).color(fontColor).build())
                    .bodyFactsSet(BodyFactsSet.builder().facts(facts).build())
                    .bodyPayload(BodyPayload.builder().color(fontColor).build())
                    .bodySourcePayload(BodySourcePayload.builder().text(payload).build())
                    .bodyActions(BodyActionSet.builder().actions(actions).build())
                .build());

        attachments.add(Attachments
                .builder()
                .content(Content
                        .builder()
                        .msteams(MSTeams.builder().build())
                        .body(body)
                        .build())
                .build());

        return layoutFix(new Gson().toJson(AdaptiveCardLayout
                .builder()
                .attachments(attachments)
                .build()));
    }

    @Override
    public String adaptiveCardWithHeader(
            String payload,
            String bodyTitle,
            List<AdaptiveCardFacts> facts,
            List<ColumnsSet> columns,
            List<Actions> actions,
            String fontColor
    ) {
        try {
            columns.get(0).getItems().get(0).setUrl(imageFix(columns.get(0).getItems().get(0).getUrl()));
        } catch (RuntimeException re) {
            //Suppress exception
        }

        List<Attachments> attachments = new ArrayList<>();

        List<BodyContent> body = new ArrayList<>();
        body.add(BodyContent
                .builder()
                    .bodyColumSet(BodyColumSet.builder().columns(columns).build())
                    .bodyTitle(BodyTitle.builder().text(bodyTitle).color(fontColor).build())
                    .bodyFactsSet(BodyFactsSet.builder().facts(facts).build())
                    .bodyPayload(BodyPayload.builder().color(fontColor).build())
                    .bodySourcePayload(BodySourcePayload.builder().text(payload).build())
                    .bodyActions(BodyActionSet.builder().actions(actions).build())
                .build());

        attachments.add(Attachments
                .builder()
                .content(Content
                        .builder()
                        .msteams(MSTeams.builder().build())
                        .body(body)
                        .build())
                .build());

        return layoutFix(new Gson().toJson(AdaptiveCardLayout
                .builder()
                .attachments(attachments)
                .build()));
    }

    @Override
    public String adaptiveCardWithHeaderAndImage(
            String payload,
            String bodyTitle,
            String imageUrl,
            String textAbout,
            List<AdaptiveCardFacts> facts,
            List<ColumnsSet> columns,
            List<Actions> actions,
            String fontColor
    ) {
        try {
            imageUrl = imageFix(imageUrl);
            columns.get(0).getItems().get(0).setUrl(imageFix(columns.get(0).getItems().get(0).getUrl()));
        } catch (RuntimeException re) {
            //Suppress exception
        }

        List<Attachments> attachments = new ArrayList<>();

        List<BodyContent> body = new ArrayList<>();
        body.add(BodyContent
                .builder()
                    .bodyColumSet(BodyColumSet.builder().columns(columns).build())
                    .imageObject(ImageObject.builder().url(imageUrl).build())
                    .textObject(TextObject.builder().text(textAbout).wrap(WRAP_TRUE).build())
                    .bodyTitle(BodyTitle.builder().text(bodyTitle).color(fontColor).build())
                    .bodyFactsSet(BodyFactsSet.builder().facts(facts).build())
                    .bodyPayload(BodyPayload.builder().color(fontColor).build())
                    .bodySourcePayload(BodySourcePayload.builder().text(payload).build())
                    .bodyActions(BodyActionSet.builder().actions(actions).build())
                .build());

        attachments.add(Attachments
                .builder()
                .content(Content
                        .builder()
                        .msteams(MSTeams.builder().build())
                        .body(body)
                        .build())
                .build());

        return layoutFix(new Gson().toJson(AdaptiveCardLayout
                .builder()
                .attachments(attachments)
                .build()));
    }

}
