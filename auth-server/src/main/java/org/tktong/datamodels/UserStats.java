package org.tktong.datamodels;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.*;
import java.math.BigInteger;

@Entity
@Table(name = "userstats")
public class UserStats {

    public UserStats() {
    }

    public UserStats(String username) {
        this.username = username;
    }

    public UserStats(int user_id) {
        this.user_id = user_id;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Setter @Getter
    private Set<PokemonObj> pokemon;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Setter @Getter
    private Set<Candies> candies;

    @Getter @Setter
    @Column(name = "username")
    private String username;

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int user_id;

    @Getter @Setter
    @Column(name = "team")
    private int team;

    @Getter @Setter
    @Column(name = "gender")
    private int gender;

    @Getter @Setter
    @Column(name = "shoes")
    private int shoes;

    @Getter @Setter
    @Column(name = "backpack")
    private int backpack;

    @Getter @Setter
    @Column(name = "pants")
    private int pants;


    @Getter @Setter
    @Column(name = "skin")
    private int skin;

    @Getter @Setter
    @Column(name = "hair")
    private int hair;

    @Getter @Setter
    @Column(name = "eyes")
    private int eyes;

    @Getter @Setter
    @Column(name = "hat")
    private int hat;

    @Getter @Setter
    @Column(name = "shirt")
    private int shirt;

    @Getter @Setter
    @Column(name = "exp")
    private long exp;

    @Getter @Setter
    @Column(name = "km_walked")
    private float km_walked;

    @Getter @Setter
    @Column(name = "pokemon_encountered")
    private int pokemon_encountered;

    @Getter @Setter
    @Column(name = "pokedex_entries")
    private int pokedex_entries;

    @Getter @Setter
    @Column(name = "level")
    private int level;

    @Getter @Setter
    @Column(name = "pokemons_captured")
    private int pokemons_captured;

    @Getter @Setter
    @Column(name = "evolutions")
    private int evolutions;

    @Getter @Setter
    @Column(name = "poke_stop_visits")
    private int poke_stop_visits;


    @Getter @Setter
    @Column(name = "pokeballs_thrown")
    private int pokeballs_thrown;

    @Getter @Setter
    @Column(name = "eggs_hatched")
    private int eggs_hatched;

    @Getter @Setter
    @Column(name = "big_magikarp_caught")
    private int big_magikarp_caught;

    @Getter @Setter
    @Column(name = "battle_attack_won")
    private int battle_attack_won;

    @Getter @Setter
    @Column(name = "battle_attack_total")
    private int battle_attack_total;

    @Getter @Setter
    @Column(name = "battle_defended_won")
    private int battle_defended_won;


    @Getter @Setter
    @Column(name = "battle_training_won")
    private int battle_training_won;


    @Getter @Setter
    @Column(name = "battle_training_total")
    private int battle_training_total;


    @Getter @Setter
    @Column(name = "prestige_raised_total")
    private int prestige_raised_total;


    @Getter @Setter
    @Column(name = "prestige_dropped_total")
    private int prestige_dropped_total;


    @Getter @Setter
    @Column(name = "pokemon_deployed")
    private int pokemon_deployed;

    @Getter @Setter
    @Column(name = "small_rattata_caught")
    private int small_rattata_caught;


    @Getter @Setter
    @Column(name = "custom_icon")
    private String custom_icon;

    @Getter @Setter
    @Column(name = "coins")
    private int coins;

    @Getter @Setter
    @Column(name = "stardust")
    private int stardust;

    @Getter @Setter
    @Column(name = "next_collect_timestamp_ms")
    private long next_collect_timestamp_ms;

    @Getter @Setter
    @Column(name = "next_defender_bonus_collect_timestamp_ms")
    private long next_defender_bonus_collect_timestamp_ms;

    @Getter @Setter
    @Column(name = "poke_storage")
    private int poke_storage;

    @Getter @Setter
    @Column(name = "item_storage")
    private int item_storage;

    @Getter @Setter
    @Column(name = "creation_timestamp_ms")
    private long creation_timestamp_ms;

}