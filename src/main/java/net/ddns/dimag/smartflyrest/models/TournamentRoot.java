package net.ddns.dimag.smartflyrest.models;

import javafx.beans.property.SimpleStringProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static net.ddns.dimag.smartflyrest.utils.Utils.getFormattedOnlyDate;

@Entity
public class TournamentRoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private long date;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
    @JoinColumn(name = "tour_root_id")
    @Fetch(FetchMode.SELECT)
    private List<Tournament> tournaments = new ArrayList<>();

    public TournamentRoot() {
    }

    public TournamentRoot(String name, long date, List<Tournament> tournaments) {
        this.name = name;
        this.date = date;
        this.tournaments = tournaments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public void addTournament(Tournament tournament) {
        List<Tournament> tmp = getTournaments();
        tmp.add(tournament);
        setTournaments(tmp);
    }

    public void deleteTournament(Tournament tournament) {
        List<Tournament> tmp = getTournaments();
        tmp.remove(tournament);
        setTournaments(tmp);
    }

    public SimpleStringProperty getDateProperty() {
        if (getDate() == 0) {
            return new SimpleStringProperty("");
        }
        return new SimpleStringProperty(getFormattedOnlyDate(getDate()));
    }

    public SimpleStringProperty getNameProperty() {
        return getName() == null ? new SimpleStringProperty(""): new SimpleStringProperty(getName());
    }

    @Override
    public String toString() {
        return String.format("%s: %s", getFormattedOnlyDate(getDate()), getName());
    }
}
