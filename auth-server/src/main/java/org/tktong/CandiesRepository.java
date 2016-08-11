package org.tktong;

import org.tktong.datamodels.Candies;
import org.tktong.datamodels.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CandiesRepository extends JpaRepository<Candies, Long> {
	@Query("SELECT c FROM Candies c WHERE c.user = :user")
	public List<Candies> find(@Param("user") int user);
}