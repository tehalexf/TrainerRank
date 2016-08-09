package org.tktong.datamodels;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Entity
@Table(name = "userstats")
public class UserStats
{

	public UserStats(String username, int user_id, int team, int skin, 
		int hair, int eyes, int hat, int shirt, int exp, int km_walked, 
		int pokemon_encountered, int pokedex_entries, int level, 
		int pokemons_captured, int evolutions, int poke_stop_visits, 
		int pokeballs_thrown, int eggs_hatched, int big_magikarp_caught, 
		int battle_attack_won, int battle_attack_total, 
		int battle_defended_won, int battle_training_won, 
		int battle_training_total, int prestige_raised_total, 
		int prestige_dropped_total, int pokemon_deployed, 
		int small_rattata_caught, String custom_icon, 
		int coins, int stardust) {

		this.username = username;
		this.user_id = user_id;
		this.team = team;
		this.skin = skin;
		this.hair = hair;
		this.eyes = eyes;
		this.hat = hat;
		this.shirt = shirt;
		this.exp = exp;
		this.km_walked = km_walked;
		this.pokemon_encountered = pokemon_encountered;
		this.pokedex_entries = pokedex_entries;
		this.level = level;
		this.pokemons_captured = pokemons_captured;
		this.evolutions = evolutions;
		this.poke_stop_visits = poke_stop_visits;
		this.pokeballs_thrown = pokeballs_thrown;
		this.eggs_hatched = eggs_hatched;
		this.big_magikarp_caught = big_magikarp_caught;
		this.battle_attack_won = battle_attack_won;
		this.battle_attack_total = battle_attack_total;
		this.battle_defended_won = battle_defended_won;
		this.battle_training_won = battle_training_won;
		this.battle_training_total = battle_training_total;
		this.prestige_raised_total = prestige_raised_total;
		this.prestige_dropped_total = prestige_dropped_total;
		this.pokemon_deployed = pokemon_deployed;
		this.small_rattata_caught = small_rattata_caught;
		this.custom_icon = custom_icon;
		this.coins = coins;
		this.stardust = stardust;
	} 

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Setter @Getter
	private Set<Pokemon> pokemon;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Setter @Getter
	private Set<Candies> candies;

	@Getter @Setter
    @Column(name = "username")
	private String username;

	@Id
    @Getter
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "user_id")
    private int user_id;

    @Getter @Setter
    @Column(name = "team")
    private int team;
 	
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
    private int exp;

    @Getter @Setter
    @Column(name = "km_walked")
    private int km_walked;

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
}