package org.tktong;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "superhero")
public class Superhero {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Getter @Setter
    @Column(name = "first_name")
    private String firstName;

    @Getter @Setter
    @Column(name = "last_name")
    private String lastName;

    @Getter @Setter
    @Column(name = "name")
    private String name;
    
    @Getter @Setter
    @Column(name = "good")
    private boolean good;
}