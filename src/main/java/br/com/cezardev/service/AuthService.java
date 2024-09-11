package br.com.cezardev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.cezardev.dto.AcessDTO;
import br.com.cezardev.dto.AuthenticationDTO;
import br.com.cezardev.security.jwt.JwtUtils;

@Service
public class AuthService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	public AcessDTO login(AuthenticationDTO authDto) {
		
		try {
		//Cria o mecanismo de credencial para o spring
		UsernamePasswordAuthenticationToken userAuth = 
				new UsernamePasswordAuthenticationToken(authDto.getUsername(), 
						authDto.getPassword());
		
		//Prepara mecanismo para autenticação
		Authentication authentication = authenticationManager.authenticate(userAuth);
		
		//Busca usuario logado
		UserDetailsImpl userAuthentication = (UserDetailsImpl)authentication.getPrincipal();
		
		String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthentication);
		
		AcessDTO accessDto = new AcessDTO(token);
		
		return accessDto;
		
		}catch (BadCredentialsException e) {
			//TODO LOGIN OU SENHA INVALIDO
		}
		return null;
	}
	
}
