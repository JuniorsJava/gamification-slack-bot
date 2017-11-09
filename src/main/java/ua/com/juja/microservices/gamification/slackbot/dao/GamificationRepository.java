package ua.com.juja.microservices.gamification.slackbot.dao;

import ua.com.juja.microservices.gamification.slackbot.model.achievements.CodenjoyAchievement;
import ua.com.juja.microservices.gamification.slackbot.model.achievements.DailyAchievement;
import ua.com.juja.microservices.gamification.slackbot.model.achievements.InterviewAchievement;
import ua.com.juja.microservices.gamification.slackbot.model.achievements.TeamAchievement;
import ua.com.juja.microservices.gamification.slackbot.model.achievements.ThanksAchievement;

/**
 * @author Danil Kuznetsov
 */
public interface GamificationRepository {

    String[] saveDailyAchievement(DailyAchievement daily);

    String[] saveCodenjoyAchievement(CodenjoyAchievement codenjoy);

    String[] saveThanksAchievement(ThanksAchievement thanks);

    String[] saveInterviewAchievement(InterviewAchievement interview);

    String[] saveTeamAchievement(TeamAchievement team);
}