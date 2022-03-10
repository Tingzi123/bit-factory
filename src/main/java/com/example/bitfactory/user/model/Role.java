package com.example.bitfactory.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Builder
@Setter
@Entity
@AllArgsConstructor
public class Role implements GrantedAuthority, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    private String roleName;
    private String roleDesc;

    public Role(String roleName, String roleDesc) {
        this.roleName = roleName;
        this.roleDesc = roleDesc;
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return roleName;
    }
}
