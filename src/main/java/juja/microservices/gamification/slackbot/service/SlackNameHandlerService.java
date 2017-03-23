package juja.microservices.gamification.slackbot.service;

import juja.microservices.gamification.slackbot.exceptions.WrongCommandFormatException;
import juja.microservices.gamification.slackbot.model.Command;
import juja.microservices.gamification.slackbot.model.User;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nikolay on 3/16/2017.
 */
public class SlackNameHandlerService {
    @Inject
    private UserService userService;
    private final String USER_UUID_START_TOKEN = "@#";
    private final String USER_UUID_FINISH_TOKEN = "#@";
    /**
     * Slack name cannot be longer than 21 characters and
     * can only contain letters, numbers, periods, hyphens, and underscores.
     * ([a-z0-9\.\_\-]){1,21}
     * Command example -1th @slack_nick_name -2th @slack_nick_name2 -3th @slack_nick_name3
     * quick test regExp http://regexr.com/
     */
    private final String SLACK_NAME_PATTERN = "@([a-zA-z0-9\\.\\_\\-]){1,21}";

    public SlackNameHandlerService(UserService userService) {
        this.userService = userService;
    }

    public Command handle(Command command) {
        check(command);
        return changeSlackNamesToUuids(command);
    }

    private void check(Command command) {
        if (command == null) {
            throw new WrongCommandFormatException("Command is null");
        }
        if (command.getFromUser() == null) {
            throw new WrongCommandFormatException("User name sent command is null");
        }
        checkSlackName(command.getFromUser());
        if (command.getText() == null) {
            command.setText("");
        }
    }

    private void checkSlackName(String slackName) {
        Pattern p = Pattern.compile(SLACK_NAME_PATTERN);
        Matcher m = p.matcher(slackName);
        if (!m.matches()) {
            throw new WrongCommandFormatException(String.format("The slack name '%s' is not impossible", slackName));
        }
    }

    private Command changeSlackNamesToUuids(Command command) {
        command.setFromUser(changeSlackNameToUuid(command.getFromUser()));
        command.setText(changeSlackNamesToUuids(command.getText()));
        return command;
    }

    private String changeSlackNameToUuid(String slackName) {
        User user = userService.findUserBySlack(slackName.toLowerCase());
        String uuid = user.getUuid();
        return uuid;
    }

    private String changeSlackNamesToUuids(String text) {
        List<String> slackNames = extractSlackNamesFromText(text);
        return changeSlackNamesToUuids(text, slackNames);
    }

    private List<String> extractSlackNamesFromText(String text) {
        Pattern pattern = Pattern.compile(SLACK_NAME_PATTERN);
        Matcher matcher = pattern.matcher(text);
        List<String> slackNames = new ArrayList<>();
        while (matcher.find()) {
            slackNames.add(matcher.group());
        }
        return slackNames;
    }

    private String changeSlackNamesToUuids(String text, List<String> slackNames) {
        for (String slackName : slackNames) {
            //todo if slack name not found
            String uuid = userService.findUserBySlack(slackName.toLowerCase()).getUuid();
            text = text.replaceAll(slackName, USER_UUID_START_TOKEN + uuid + USER_UUID_FINISH_TOKEN);
        }
        return text;
    }
}
