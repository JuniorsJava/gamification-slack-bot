package ua.com.juja.microservices.gamification.slackbot.model.achievements;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.juja.slack.command.handler.model.SlackParsedCommand;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Artem
 * @author Nikolay Horushko
 */
@Getter
@ToString
@EqualsAndHashCode
public class InterviewAchievement {
    @JsonProperty("from")
    private String fromUuid;
    @JsonProperty
    private String description;

    public InterviewAchievement(String fromUuid, String description) {
        this.fromUuid = fromUuid;
        this.description = description;
    }

    public InterviewAchievement(SlackParsedCommand slackParsedCommand) {
        this.fromUuid = slackParsedCommand.getFromUser().getUuid();
        this.description = slackParsedCommand.getText();
    }
}


