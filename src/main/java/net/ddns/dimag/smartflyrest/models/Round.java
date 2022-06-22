package net.ddns.dimag.smartflyrest.models;

import javax.persistence.*;

@Entity
@Table(name = "ROUNDS")
public class Round implements Comparable<Round> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @ManyToOne
    @JoinColumn(name = "fight_id")
    private Fight fight;

    @Column(name = "round_number")
    private Integer roundNumber;

    @Column(name = "red_score")
    private Integer redScore;

    @Column(name = "blue_score")
    private Integer blueScore;

    // 1 complete
    // -1 stopped
    @Column(name = "status")
    private Integer status;

    public Round() {
    }

    public Round(Fight fight, int roundNumber, int redScore, int blueScore, int status) {
        this.fight = fight;
        this.roundNumber = roundNumber;
        this.redScore = redScore;
        this.blueScore = blueScore;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Fight getFight() {
        return fight;
    }

    public void setFight(Fight fight) {
        this.fight = fight;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public Integer getRedScore() {
        return redScore;
    }

    public void setRedScore(int redScore) {
        this.redScore = redScore;
    }

    public Integer getBlueScore() {
        return blueScore;
    }

    public void setBlueScore(int blueScore) {
        this.blueScore = blueScore;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int compareTo(Round o) {
        return this.getRoundNumber().compareTo(o.getRoundNumber());
    }
}
