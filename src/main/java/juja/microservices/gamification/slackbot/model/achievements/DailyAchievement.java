package juja.microservices.gamification.slackbot.model.achievements;

import com.fasterxml.jackson.annotation.JsonProperty;
import juja.microservices.gamification.slackbot.model.SlackParsedCommand;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Nikolay Horushko
 */
@Getter
@ToString
public class DailyAchievement {
    @JsonProperty("from")
    private String fromUuid;
    @JsonProperty
    private String description;

    public DailyAchievement(String fromUuid, String description) {
        this.fromUuid = fromUuid;
        this.description = description;
    }

    public DailyAchievement(SlackParsedCommand slackParsedCommand) {
        this.fromUuid = slackParsedCommand.getFromUser().getUuid();
        this.description = slackParsedCommand.getText();
    }
}
