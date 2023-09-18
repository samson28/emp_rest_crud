package com.lundu.empback.entities;

import com.lundu.empback.dto.request.AppUserRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUser implements UserDetails {
    @Id
    private String id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role roles;

/*    @ManyToMany(fetch = FetchType.EAGER  )
    @ToString.Exclude
    private List<AppRole> roles;*/

    public static AppUser fromDTO(AppUserRequestDTO appUserRequestDTO) {
        AppUser appUser = new AppUser();
        appUser.setId(UUID.randomUUID().toString());
        appUser.setEmail(appUserRequestDTO.email());
        appUser.setName(appUserRequestDTO.name());
        appUser.setPassword(appUserRequestDTO.password());
        appUser.setRoles(appUserRequestDTO.roles());
        return appUser;
    }

    public void update(AppUserRequestDTO appUserRequestDTO) {
        if(appUserRequestDTO.name() != null) this.name = appUserRequestDTO.name();
        if(appUserRequestDTO.email() != null) this.email = appUserRequestDTO.email();
        if(appUserRequestDTO.password() != null) this.password = appUserRequestDTO.password();
        if(appUserRequestDTO.roles() != null) this.roles = appUserRequestDTO.roles();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        AppUser appUser = (AppUser) o;
        return getId() != null && Objects.equals(getId(), appUser.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         return List.of(new SimpleGrantedAuthority(roles.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
