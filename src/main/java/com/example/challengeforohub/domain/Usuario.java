package com.example.challengeforohub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Table(name = "usuarios")
@Entity(name = "Usuario")

@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String clave;

    public Usuario(Long id, String login, String clave) {
        this.login = login;
        this.clave = clave;
    }

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getClave() {
        return clave;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Asumiendo que todos los usuarios tienen el mismo rol
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.clave;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Puedes implementar tu lógica aquí para determinar si la cuenta está expirada
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Implementar lógica para bloquear la cuenta si es necesario
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Implementar lógica para las credenciales (como si la contraseña está vencida)
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Puedes implementar la lógica aquí para determinar si el usuario está habilitado
        return true;
    }
}
