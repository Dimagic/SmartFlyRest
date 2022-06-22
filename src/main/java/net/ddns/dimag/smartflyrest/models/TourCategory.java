package net.ddns.dimag.smartflyrest.models;

import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;

@Entity
@Table(name = "TOUR_CATEGORY")
public class TourCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "round_count", nullable = false)
    private int roundCount;

    @Column(name = "round_time", nullable = false)
    private int roundTime;

    @Column(name = "weight_min")
    private int weightMin;

    @Column(name = "weight_max")
    private int weightMax;

    @Column(name = "age_min")
    private int ageMin;

    @Column(name = "age_max")
    private int ageMax;

    @Column(name = "score_point")
    private String scorePoint;

    @Column(name = "ignore_weight", columnDefinition = "int default 0")
    private int ignoreWeight;

    @Column(name = "ignore_age", columnDefinition = "int default 0")
    private int ignoreAge;

    public TourCategory() {
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

    public int getRoundTime() {
        return roundTime;
    }

    public void setRoundTime(int roundTime) {
        this.roundTime = roundTime;
    }

    public int getWeightMin() {
        return weightMin;
    }

    public void setWeightMin(int weightMin) {
        this.weightMin = weightMin;
    }

    public int getWeightMax() {
        return weightMax;
    }

    public void setWeightMax(int weightMax) {
        this.weightMax = weightMax;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int agetMax) {
        this.ageMax = agetMax;
    }

    public String getWeightCategory() {
        return String.format("%s-%s kg", getWeightMin(), getWeightMax());
    }

    public String getAgeCategory() {
        return String.format("%s-%s years", getAgeMin(), getAgeMax());
    }

    public String getScorePoint() {
        if (scorePoint != null && !scorePoint.isEmpty()) {
            return scorePoint.replace("[","")
                    .replace("]", "")
                    .replace(" ", "");
        }
        return null;
    }

    public int getIgnoreWeight() {
        return ignoreWeight;
    }

    public void setIgnoreWeight(int ignoreWeight) {
        this.ignoreWeight = ignoreWeight;
    }

    public void setIgnoreWeight(boolean ignoreWeight) {
        this.ignoreWeight = ignoreWeight ? 1: 0;
    }

    public int getIgnoreAge() {
        return ignoreAge;
    }

    public void setIgnoreAge(int ignoreAge) {
        this.ignoreAge = ignoreAge;
    }

    public void setIgnoreAge(boolean ignoreAge) {
        this.ignoreAge = ignoreAge ? 1: 0;
    }

    public void setScorePoint(String scorePoint) {
        this.scorePoint = scorePoint;
    }

    public SimpleStringProperty getTypeProperty() {
        return new SimpleStringProperty(String.format("%s a: %s w: %s", getName(), getAgeCategory(), getWeightCategory()));
    }

    @Override
    public String toString() {
        return getTypeProperty().getValue();
    }

}
