package org.tktong;

import org.tktong.datamodels.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;


public interface UserStatsRepository extends JpaRepository<UserStats, Long> {
	UserStats findByUsername(String username);
}