
package net.ddns.dimag.smartflyrest.controllers;

import net.ddns.dimag.smartflyrest.models.Competitor;
import net.ddns.dimag.smartflyrest.models.Team;
import net.ddns.dimag.smartflyrest.repo.CompetitorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8088")
@RestController
@RequestMapping("/api")
public class CompetitorController {
    final
    CompetitorRepo competitorRepo;

    @Autowired
    public CompetitorController(CompetitorRepo competitorRepo) {
        this.competitorRepo = competitorRepo;
    }

    @GetMapping("/competitors")
    public ResponseEntity<List<Competitor>> getAllTeams() {
        try {
            List<Competitor> competitors = new ArrayList<>();
            competitorRepo.findAll().forEach(competitors::add);
            if (competitors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(competitors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/competitors/{id}")
    public ResponseEntity<Competitor> getCompetitorById(@PathVariable("id") long id) {
        Optional<Competitor> competitorData = competitorRepo.findById(id);

        if (competitorData.isPresent()) {
            return new ResponseEntity<>(competitorData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}