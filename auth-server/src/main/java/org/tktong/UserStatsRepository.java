package org.tktong;

import org.tktong.datamodels.TripleLong;
import org.tktong.datamodels.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.*;
import org.springframework.cache.annotation.Cacheable;


public interface UserStatsRepository extends JpaRepository<UserStats, Long> {
	UserStats findByUsername(String username);
	
	@Cacheable("summedPoints")	
	@Query(value = "select new org.tktong.datamodels.TripleLong(SUM(u.battle_attack_won), SUM(u.battle_attack_won), SUM(u.battle_attack_won)) from UserStats u")
	TripleLong findSummedPoints();

	// @Query(value = "SELECT SUM(battle_attack_won) red, SUM(battle_attack_won) yellow, SUM(battle_attack_won) blue FROM userstats", nativeQuery = true)
	// TripleInteger findSummedPoints();
}