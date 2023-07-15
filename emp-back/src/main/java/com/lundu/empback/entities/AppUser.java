package com.lundu.empback.entities;

import com.lundu.empback.dto.request.AppUserRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    @Id
    private String id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER  )
    @ToString.Exclude
    private List<AppRole> roles;

    public static AppUser fromDTO(AppUserRequestDTO appUserRequestDTO) {
        AppUser appUser = new AppUser();
        appUser.setId(UUID.randomUUID().toString());
        appUser.setEmail(appUserRequestDTO.email());
        appUser.setName(appUserRequestDTO.name());
        appUser.setPassword(appUserRequestDTO.password());
        appUser.setRoles(appUserRequestDTO.roles().stream().map(e -> new AppRole(e)).collect(Collectors.toList()));
        return appUser;
    }

    public void update(AppUserRequestDTO appUserRequestDTO) {
        if(appUserRequestDTO.name() != null) this.name = appUserRequestDTO.name();
        if(appUserRequestDTO.email() != null) this.email = appUserRequestDTO.email();
        if(appUserRequestDTO.password() != null) this.password = appUserRequestDTO.password();
        if(appUserRequestDTO.roles() != null) this.roles = appUserRequestDTO.roles().stream().map(e -> new AppRole(e)).collect(Collectors.toList());
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
}
