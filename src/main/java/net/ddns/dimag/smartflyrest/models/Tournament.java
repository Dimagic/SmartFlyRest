package net.ddns.dimag.smartflyrest.models;

import javafx.beans.property.SimpleStringProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "TOURNAMENTS")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @ManyToOne
    @JoinColumn(name = "competition_category_id")
    private TourCategory tourCategory;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    private List<TourPair> pairs = new ArrayList<>();

    public Tournament() {
    }

    public Tournament(TourCategory tourCategory) {
        this.tourCategory = tourCategory;
    }

    public long getId() {
        return id;
    }

    public TourCategory getCompetitionCategory() {
        return tourCategory;
    }

    public void setCompetitionCategory(TourCategory tourCategory) {
        this.tourCategory = tourCategory;
    }

    public List<TourPair> getPairs() {
        if (pairs == null) {
            return new ArrayList<>();
        }
        return pairs;
    }

    public void setPairs(List<TourPair> pairs) {
        this.pairs = pairs;
    }

    public void addPairs(TourPair pair) {
        List<TourPair> tmp = getPairs();
        tmp.add(pair);
        setPairs(tmp);
    }

    public void deletePairs(TourPair pair) {
        List<TourPair> tmp = getPairs();
        tmp.remove(pair);
        setPairs(tmp);
    }

    public int getTourCount() {
        int pairCount = getPairs().size();
        int tourCount = 0;
//        if (pairCount <= 2) {
//            tourCount = pairCount;
//        }
//        if (pairCount % 4 == 0) {
//            tourCount = pairCount / 2;
//        }
        while (pairCount >= 1) {
            pairCount = pairCount / 2;
            tourCount ++;
        }

        return tourCount;
    }

    public int getStatus() {
        List<TourPair> fightList = getPairs().stream().filter(c -> c.getFight() != null).collect(Collectors.toList());
        if (fightList.size() == 0) {
            return  0;
        }
        if (fightList.size() >= getPairs().size()) {
            return 2;
        }
        return 1;
    }

    public SimpleStringProperty getStatusProperty() {
        String prop;
        switch (getStatus()) {
            case 0:
                prop = "not started";
                break;
            case 1:
                prop = "on process";
                break;
            case 2:
                prop = "finished";
                break;
            default:
                prop = "incorrect";
        }
        return new SimpleStringProperty(prop);
    }

    public SimpleStringProperty getTypeProperty() {
        if (getCompetitionCategory() == null) {
            return new SimpleStringProperty("");
        }
        return getCompetitionCategory().getTypeProperty();
    }

    public SimpleStringProperty getPairCountProperty() {
        return new SimpleStringProperty(String.valueOf(getPairs().size()));
    }
}
