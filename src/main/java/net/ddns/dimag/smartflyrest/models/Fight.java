package net.ddns.dimag.smartflyrest.models;


import javafx.beans.property.SimpleStringProperty;
import net.ddns.dimag.smartflyrest.utils.Utils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static net.ddns.dimag.smartflyrest.utils.Utils.formatMS;


@Entity
@Table(name = "FIGHTS")
public class Fight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @ManyToOne
    @JoinColumn(name = "tour_category_id")
    private TourCategory tourCategory;

    @ManyToOne
    @JoinColumn(name = "red_sensor_id")
    private ForceSensor redSensor;

    @ManyToOne
    @JoinColumn(name = "blue_sensor_id")
    private ForceSensor blueSensor;

    @ManyToOne
    @JoinColumn(name = "red_competitor_id")
    private Competitor redCompetitor;

    @ManyToOne
    @JoinColumn(name = "blue_competitor_id")
    private Competitor blueCompetitor;

    @Column(name = "date_start")
    private long dateStart;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private Set<Round> rounds;

    public Fight() {
    }

    public Fight(TourPair pair) {
        this.tourCategory = pair.getTournament().getCompetitionCategory();
        this.redCompetitor = pair.getFirst();
        this.blueCompetitor = pair.getSecond();
        this.dateStart = new Date().getTime();
    }

//    public Fight(TourCategory tourCategory,
//                 ForceSensor redSensor, Competitor redCompetitor,
//                 ForceSensor blueSensor, Competitor blueCompetitor) {
//        this.tourCategory = tourCategory;
//        this.redSensor = redSensor;
//        this.redCompetitor = redCompetitor;
//        this.blueSensor = blueSensor;
//        this.blueCompetitor = blueCompetitor;
//        this.dateStart = new Date().getTime();
//    }

    public long getId() {
        return id;
    }

    public TourCategory getCompetitionType() {
        return tourCategory;
    }

    public void setCompetitionType(TourCategory tourCategory) {
        this.tourCategory = tourCategory;
    }

    public ForceSensor getRedSensor() {
        return redSensor;
    }

    public void setRedSensor(ForceSensor redSensor) {
        this.redSensor = redSensor;
    }

    public ForceSensor getBlueSensor() {
        return blueSensor;
    }

    public void setBlueSensor(ForceSensor blueSensor) {
        this.blueSensor = blueSensor;
    }

    public Competitor getRedCompetitor() {
        return redCompetitor;
    }

    public void setRedCompetitor(Competitor redCompetitor) {
        this.redCompetitor = redCompetitor;
    }

    public Competitor getBlueCompetitor() {
        return blueCompetitor;
    }

    public void setBlueCompetitor(Competitor blueCompetitor) {
        this.blueCompetitor = blueCompetitor;
    }

    public long getDateStart() {
        return dateStart;
    }

    public void setDateStart(long dateStart) {
        this.dateStart = dateStart;
    }

    public Set<Round> getRounds() {
        if (rounds == null) {
            return new HashSet<>();
        }
        return rounds;
    }

    public void setRounds(Set<Round> rounds) {
        this.rounds = rounds;
    }

    public void addRound(Round round) {
        Set<Round> tmp = getRounds();
        tmp.add(round);
        setRounds(tmp);
    }

    public void deleteRound(Round round) {
        Set<Round> tmp = getRounds();
        tmp.remove(round);
        setRounds(tmp);
    }

    public SimpleStringProperty getFormattedDate() {
        return new SimpleStringProperty(Utils.getFormattedDate(getDateStart()));
    }

    public SimpleStringProperty getTourCategoryName() {
        return tourCategory == null ? new SimpleStringProperty("") :
                new SimpleStringProperty(tourCategory.getName());
    }

    public SimpleStringProperty getFormattedRoundTime() {
        return new SimpleStringProperty(formatMS(TimeUnit.SECONDS.toMillis(getCompetitionType().getRoundTime())));
    }

    public SimpleStringProperty getRoundCountProperty() {
        return new SimpleStringProperty(String.valueOf(getCompetitionType().getRoundCount()));
    }

    public int getRedScore() {
        Round r = getLastRound();
        return r != null ? r.getRedScore(): 0;
    }

    public int getBlueScore() {
        Round r = getLastRound();
        return r != null ? r.getBlueScore(): 0;
    }

    public Competitor getWinner() {
        Round r = getLastRound();
        if (r != null && (int) r.getRedScore() != r.getBlueScore()) {
            return r.getRedScore() > r.getBlueScore() ? getRedCompetitor() : getBlueCompetitor();
        }
        return null;
    }

    public Round getLastRound() {
        Optional<Round> r = Optional.of(getRounds()
                        .stream()
                        .max(Comparator.comparing(Round::getRoundNumber))).get();
        return r.orElse(null);
    }

    public SimpleStringProperty getRedScoreProperty() {
        return new SimpleStringProperty(String.valueOf(getRedScore()));
    }

    public SimpleStringProperty getBlueScoreProperty() {
        return new SimpleStringProperty(String.valueOf(getBlueScore()));
    }




    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
