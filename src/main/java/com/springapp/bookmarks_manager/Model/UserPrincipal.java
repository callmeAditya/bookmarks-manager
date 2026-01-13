package com.springapp.bookmarks_manager.Model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {
private static final long serialVersionUID = 1L;

	
	private UserDTO user;

	public UserPrincipal(UserDTO user) {
    this.user=user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	public String getEmail() {
	
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;	}
	
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}
}
