package org.tktong;

import org.tktong.datamodels.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;


public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
	// List<Pokemon> findTop20ByOwnerIdOrderByIdDesc(int ownerId);
}