package net.ddns.dimag.smartflyrest;

import com.github.javafaker.Faker;
import net.ddns.dimag.smartflyrest.models.Competitor;
import net.ddns.dimag.smartflyrest.models.Team;
import net.ddns.dimag.smartflyrest.repo.CompetitorRepo;
import net.ddns.dimag.smartflyrest.repo.TeamRepo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@SpringBootApplication
public class SmartFlyRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartFlyRestApplication.class, args);
    }

    @Bean
    ApplicationRunner init(TeamRepo teamRepo, CompetitorRepo competitorRepo) {
        Faker faker = new Faker();
        Random rand = new Random();

        return args -> {
            for (int i = 0; i < 5; i++) {
                Team team = new Team(faker.team().name(), null);
                teamRepo.save(team);
            }

            List<Long> teamList = new ArrayList<>();
            teamRepo.findAll().forEach(c -> teamList.add(c.getId()));

            for (int i = 0; i < 500; i++){
                Competitor competitor = new Competitor(faker.name().firstName(), faker.name().lastName());
                competitor.setTeam(teamRepo.findById(teamList.get(rand.nextInt(teamList.size()))).get());
                competitorRepo.save(competitor);
            }
           competitorRepo.findAll().forEach(System.out::println);

        };
    }

}
