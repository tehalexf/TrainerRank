package org.tktong.datamodels;

import org.springframework.context.annotation.Bean;
import lombok.Getter;
import lombok.Setter;


public class TripleInteger {

	public TripleInteger(int red, int blue, int yellow) {
		this.red= red;
		this.blue = blue;
		this.yellow = yellow;
	}

	@Setter @Getter
	private int red;

	@Setter @Getter
	private int blue;

	@Setter @Getter
	private int yellow;
}