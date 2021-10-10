package com.coachfinder.security.auth.user;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.coachfinder.security.auth.role.Role;

import lombok.Setter;



@Entity
@Table(name = "user")
@Setter
public class User{
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "email",unique = true,nullable=false)
	private String email;
	
//	@Column(name = "username",unique = true,nullable = false)
//	private String username;
	@Column(name = "password",nullable = false)
	private String password;
	
	@Column(name="isAccountNonExpired",nullable = false)
	private boolean isAccountNonExpired;
	
	@Column(name="isAccountNonLocked",nullable = false)
	private boolean isAccountNonLocked;
	
	@Column(name="isCredentialsNonExpired",nullable = false)
	private boolean isCredentialsNonExpired;
	
	@Column(name="isEnabled",nullable = false)
	private boolean isEnabled;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name  = "role_id")
	)
	private Set<Role> roles = new HashSet<Role>();
	
	
	
	public User( String password, String email, boolean isAccountNonExpired, boolean isAccountNonLocked,
			boolean isCredentialsNonExpired, boolean isEnabled, Set<Role> roles) {
		super();
		this.password = password;
		this.email = email;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
		this.roles = roles;
	}

	public User() {
		super();
	}

	public Integer getId() {
		return id;
	}


	public String getPassword() {
		return password;
	}
	
	

	public String getEmail() {
		return email;
	}

	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}
	
	
	
	

}
