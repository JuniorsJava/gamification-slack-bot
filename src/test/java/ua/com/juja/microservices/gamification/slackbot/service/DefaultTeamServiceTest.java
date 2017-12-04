package ua.com.juja.microservices.gamification.slackbot.service;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.netflix.feign.FeignAutoConfiguration;
import org.springframework.cloud.netflix.feign.ribbon.FeignRibbonClientAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import ua.com.juja.microservices.gamification.slackbot.dao.TeamRepository;
import ua.com.juja.microservices.gamification.slackbot.model.DTO.TeamDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.LinkedHashSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(TeamService.class)
@ImportAutoConfiguration({RibbonAutoConfiguration.class, FeignRibbonClientAutoConfiguration.class, FeignAutoConfiguration.class})
public class DefaultTeamServiceTest {

    @Inject
    private TeamService service;

    @MockBean
    private TeamRepository repository;

    @Test
    public void getTeamByUuid(){
        //given
        TeamDTO expectedTeam = new TeamDTO(new LinkedHashSet<>(Arrays.asList("uuid1", "uuid2", "uuid3", "uuid4")));
        System.out.println(expectedTeam.getMembers());

        //when
        when(repository.getTeamByUserUuid("uuid")).thenReturn(expectedTeam);
        TeamDTO actualTeam = service.getTeamByUserUuid("uuid");

        //then
        assertEquals(expectedTeam, actualTeam);
    }
}