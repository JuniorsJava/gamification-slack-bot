package ua.com.juja.microservices.gamification.slackbot.dao;

import ua.com.juja.slack.command.handler.model.UserDTO;

import java.util.List;
import java.util.Set;

/**
 * @author Artem
 */
public interface UserRepository {

    List<UserDTO> findUsersBySlackNames(List<String> slackNames);

    Set<UserDTO> findUsersByUuids(Set<String> uuids);
}