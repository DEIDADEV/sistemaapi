package br.com.cezardev.dto;

public class AcessDTO {
	
	//TODO implementar retornar o usuario e liberações (authotities)
	
	public AcessDTO(String token) {
		super();
		this.token = token;
	}
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
