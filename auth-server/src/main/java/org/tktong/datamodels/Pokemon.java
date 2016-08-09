package org.tktong.datamodels;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @Getter
    @Column(name = "game_id")
    private String game_id;

    @Getter @Setter
    @Column(name = "id")
    private int id;

    @Getter @Setter
    @Column(name = "cp")
    private int cp;

    @Getter @Setter
    @Column(name = "owner_id")
    private int ownerId;
    
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
    @Column(name = "cpMultiplier")
    private double cpMultiplier;

    @Getter @Setter
    @Column(name = "updated")
    private boolean updated;

    @Getter @Setter
    @Column(name = "additionalCPMultiplier")
    private double additionalCPMultiplier;
    
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
    private boolean is_egg;
        
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
    private int captured_cell_id;

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
    private int creation_time_ms;

    @Getter @Setter
    @Column(name = "num_upgrades")
    private int num_upgrades;

    @Getter @Setter
    @Column(name = "nickname")
    private String nickname;

    @Getter @Setter
    @Column(name = "from_fort")
    private String from_fort;

}