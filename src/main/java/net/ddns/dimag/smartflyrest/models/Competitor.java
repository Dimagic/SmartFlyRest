package net.ddns.dimag.smartflyrest.models;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Competitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    public Competitor() {
    }

    public Competitor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private String firstName;

    private String lastName;

    private Date birthday;

    private int weight;

    private Boolean sex;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private ForceSensor sensor;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public ForceSensor getSensor() {
        return sensor;
    }

    public void setSensor(ForceSensor sensor) {
        this.sensor = sensor;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("birthday", birthday)
                .append("weight", weight)
                .append("sex", sex)
                .append("sensor", sensor)
                .append("team", team)
                .toString();
    }
}
