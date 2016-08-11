package org.tktong.responses;
import lombok.Getter;
import lombok.Setter;


public class LoginResponse {

	public LoginResponse(int status) {
		this.status = status;
	}

	public LoginResponse(int status, String username) {
		this.status = status;
		this.username = username;
	}

	@Getter @Setter
	int status;

	@Getter @Setter
	String username;

}