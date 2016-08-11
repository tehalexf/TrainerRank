package org.tktong.datamodels;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigInteger;

@Entity
@Table(name = "pokemon")
public class PokemonObj {

    public PokemonObj() {

    }

    public PokemonObj(int user, long game_id) {
        this.user = user;
        this.game_id = game_id;

    }

    // @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "owner_id")
    @Setter @Getter
    private int user;

    @Id
    @Getter
    @Column(name = "game_id")
    private long game_id;

    @Getter @Setter
    @Column(name = "id")
    private int id;

    @Setter
    @Column(name = "updated")
    private boolean updated;

    public boolean getUpdated() {
        return this.updated;
    }

    @Getter @Setter
    @Column(name = "cp")
    private int cp;

    @Getter @Setter
    @Column(name = "favorite")
    private boolean favorite;

    @Getter @Setter
    @Column(name = "move_1")
    private int move_one;

    @Getter @Setter
    @Column(name = "move_2")
    private int move_two;

    @Getter @Setter
    @Column(name = "iv_atk")
    private int iv_atk;

    @Getter @Setter
    @Column(name = "iv_def")
    private int iv_def;

    @Getter @Setter
    @Column(name = "iv_sta")
    private int iv_sta;

    @Getter @Setter
    @Column(name = "cp_multiplier")
    private double cp_multiplier;

    @Getter @Setter
    @Column(name = "additional_cp_multiplier")
    private double additional_cp_multiplier;

    @Getter @Setter
    @Column(name = "stamina")
    private int stamina;

    @Getter @Setter
    @Column(name = "stamina_max")
    private int stamina_max;

    @Getter @Setter
    @Column(name = "deployed_fort_id")
    private String deployed_fort_id;

    @Getter @Setter
    @Column(name = "is_egg")
    private boolean egg;

    @Getter @Setter
    @Column(name = "origin")
    private int origin;

    @Getter @Setter
    @Column(name = "height_m")
    private float height_m;

    @Getter @Setter
    @Column(name = "weight_kg")
    private float weight_kg;

    @Getter @Setter
    @Column(name = "egg_km_walked_target")
    private double egg_km_walked_target;

    @Getter @Setter
    @Column(name = "egg_km_walked_start")
    private double egg_km_walked_start;

    @Getter @Setter
    @Column(name = "pokeball")
    private int pokeball;

    @Getter @Setter
    @Column(name = "captured_cell_id")
    private BigInteger captured_cell_id;

    @Getter @Setter
    @Column(name = "battles_attacked")
    private int battles_attacked;

    @Getter @Setter
    @Column(name = "battles_defended")
    private int battles_defended;

    @Getter @Setter
    @Column(name = "egg_incubator_id")
    private String egg_incubator_id;

    @Getter @Setter
    @Column(name = "creation_time_ms")
    private BigInteger creation_time_ms;

    @Getter @Setter
    @Column(name = "num_upgrades")
    private int num_upgrades;

    @Getter @Setter
    @Column(name = "nickname")
    private String nickname;

    @Getter @Setter
    @Column(name = "from_fort")
    private boolean from_fort;

}