package org.tktong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SandboxEntityLoader implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private SandboxRepository sandboxRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// Add entities to save into the repository here
		//
		// Sample:
		// SandboxEntity entity = new SandboxEntity();
		// sandboxRepository.save(entity);
	}
}