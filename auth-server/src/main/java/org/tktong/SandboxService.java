package org.tktong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SandboxService {
	@Autowired
	private SandboxRepository sandboxRepository;

	@Transactional(readOnly = true)
	public Optional<SandboxEntity> getSandboxEntityById(int id) {
		return sandboxRepository.findSandboxEntityById(id);
	}
}