package org.tktong.datamodels;

import org.springframework.context.annotation.Bean;
import lombok.Getter;
import lombok.Setter;


public class TripleLong {

	public TripleLong(long red, long blue, long yellow) {
		this.redScore= red;
		this.blueScore = blue;
		this.yellowScore = yellow;
	}

	@Setter @Getter
	private long redScore;

	@Setter @Getter
	private long blueScore;

	@Setter @Getter
	private long yellowScore;
}