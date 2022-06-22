package net.ddns.dimag.smartflyrest.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Comparator;

@Entity
@Table(name = "TOUR_PAIR")
public class TourPair implements Comparable<TourPair> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "first_id")
    private Competitor first;

    @ManyToOne
    @JoinColumn(name = "second_id")
    private Competitor second;

    @Column(name = "pair_num")
    private int pairNum;

    @Column(name = "tour")
    private int tour;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private Fight fight;

    public TourPair() {
    }

    public TourPair(Tournament tournament, Competitor first, Competitor second, int pairNum, int tour) {
        this.tournament = tournament;
        this.first = first;
        this.second = second;
        this.pairNum = pairNum;
        this.tour = tour;
    }

    public long getId() {
        return id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Competitor getFirst() {
        return first;
    }

    public void setFirst(Competitor first) {
        this.first = first;
    }

    public Competitor getSecond() {
        return second;
    }

    public void setSecond(Competitor second) {
        this.second = second;
    }

    public int getPairNum() {
        return pairNum;
    }

    public void setPairNum(int pairNum) {
        this.pairNum = pairNum;
    }

    public int getTour() {
        return tour;
    }

    public void setTour(int tour) {
        this.tour = tour;
    }

    public Fight getFight() {
        return fight;
    }

    public void setFight(Fight fight) {
        this.fight = fight;
    }

    public boolean isPairComplete() {
        return getFirst() != null && getSecond() != null;
    }

    @Override
    public int compareTo(TourPair o) {
        return Comparator.comparing(TourPair::getTour)
                .thenComparing(TourPair::getPairNum)
                .compare(this, o);
    }

}
