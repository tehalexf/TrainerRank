package org.tktong;

import javax.persistence.*;

@Entity
@Table(name = "sandbox_entities")
public class SandboxEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private int id;

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}