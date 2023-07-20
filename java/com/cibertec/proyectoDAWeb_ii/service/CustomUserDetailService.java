package com.cibertec.proyectoDAWeb_ii.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cibertec.proyectoDAWeb_ii.model.Usuario;
import com.cibertec.proyectoDAWeb_ii.repository.UsuarioRepository;

//@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRespository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRespository.findByUsername(username);
		if (usuario != null) {
			return new User(usuario.getUsername(),
							usuario.getPassword(),
							usuario.getRoles().stream().map((rol) 
									-> new SimpleGrantedAuthority(rol.getDescRol())).collect(Collectors.toList()));
		}
		else {
			throw new UsernameNotFoundException("Invalid email or password");
		}

	}

}
