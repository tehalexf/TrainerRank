package org.tktong;

import org.tktong.datamodels.PokemonObj;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;


public interface PokemonRepository extends JpaRepository<PokemonObj, Long> {
	Set<PokemonObj> findByUser(int owner_id);
}