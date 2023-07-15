package com.lundu.empback.entities;

import com.lundu.empback.dto.request.AppRoleRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AppRole {
    @Id
    private String role;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        AppRole appRole = (AppRole) o;
        return getRole() != null && Objects.equals(getRole(), appRole.getRole());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static AppRole fromDTO(AppRoleRequestDTO appRoleRequestDTO) {
        return new AppRole(appRoleRequestDTO.role());
    }
}
