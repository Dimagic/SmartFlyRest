package net.ddns.dimag.smartflyrest.models;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public Team() {
    }

    public Team(String name, byte[] emblem) {
        this.name = name;
        this.emblem = emblem;
    }

    private String name;

    private byte[] emblem;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getEmblem() {
        return emblem;
    }

    public void setEmblem(byte[] emblem) {
        this.emblem = emblem;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .toString();
    }
}
