package br.gov.rn.pm.sigp.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user")
public class User implements Serializable {

   /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email")
    @Email(message = "*Por favor insira um e-mail válido.")
    @NotEmpty(message = "*Por favor insira seu e-mail.")
    private String email;

    @Column(name = "password")
    @Length(min = 5, message = "*Sua senha deve conter no mínimo 5 caracteres.")
    @NotEmpty(message = "*Por favor insira sua senha.")
    @Transient
    private String password;

    @Column(name = "name")
    @NotEmpty(message = "*Por favor insira o seu nome.")
    private String name;

    @Column(name = "matricula")
    @NotEmpty(message = "*Por favor insira sua matrícula.")
    private String matricula;

    @Column(name = "patente")
    @NotEmpty(message = "*Por favor insira sua patente.")
    private String patente;
    
    @Column(name = "active")
    private int active;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
    public void setNewRole(Role role) {
        
        if(roles == null) {
            roles = new HashSet<>(0);
        }
        
        if (!roles.contains(role)) {
            roles.add(role);
        }
    }
}
