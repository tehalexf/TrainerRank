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
	String username;

    @Id
    @Getter @Setter
    @Column(name = "owner_id")
    private int owner_id;
 	
    @Id
    @Getter @Setter
    @Column(name = "pokemon_family")
    private int pokemon_family;

    @Getter @Setter
    @Column(name = "candy")
    private int candy;
}