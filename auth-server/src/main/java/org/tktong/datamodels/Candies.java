package org.tktong.datamodels;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

// https://www.mkyong.com/spring/maven-spring-jdbc-example/
@Entity
@Table(name = "candies")
@IdClass(Candies.class)
public class Candies implements Serializable
{

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @Setter @Getter
    private UserStats user;
 	
    @Id
    @Getter @Setter
    @Column(name = "pokemon_family")
    private int pokemon_family;

    @Getter @Setter
    @Column(name = "candy")
    private int candy;
}