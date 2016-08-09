package org.tktong;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SandboxRepository extends JpaRepository<SandboxEntity, Integer> {
	Optional<SandboxEntity> findSandboxEntityById(int id);
}