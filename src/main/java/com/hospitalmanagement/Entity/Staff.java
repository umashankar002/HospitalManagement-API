package com.hospitalmanagement.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "staffs", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"}),
                        @UniqueConstraint(columnNames = {"email"})})
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String name;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name= "staff_roles", joinColumns = @JoinColumn(name= "staff_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private Set<Roles> roles;
}
