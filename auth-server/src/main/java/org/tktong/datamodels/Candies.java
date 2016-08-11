package org.tktong.datamodels;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

// https://www.mkyong.com/spring/maven-spring-jdbc-example/
@Entity
@Table(name = "candies")
@IdClass(Candies.class)
public class Candies implements Serializable {
    public Candies() {

    }

    // public Candies(UserStats user, int pokemon_family) {
    //     this.user = user;
    //     this.pokemon_family = pokemon_family;
    // }

    public Candies(int user, int pokemon_family, int count) {
        this.user = user;
        this.pokemon_family = pokemon_family;
        this.candy = count;
    }

    @Id
    // @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "owner_id")
    @Setter @Getter
    private int user;

    @Id
    @Getter @Setter
    @Column(name = "pokemon_family")
    private int pokemon_family;

    @Getter @Setter
    @Column(name = "candy")
    private int candy;

    @Override public boolean equals(Object other) {
        Candies casted = (Candies) other;
        //Might need to add owner id, but not at this moment.
        return other instanceof Candies && this.pokemon_family == casted.pokemon_family;
    }
}