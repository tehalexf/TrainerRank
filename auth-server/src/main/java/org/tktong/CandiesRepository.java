package org.tktong;

import org.tktong.datamodels.Candies;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;


public interface CandiesRepository extends JpaRepository<Candies, Long> {
}