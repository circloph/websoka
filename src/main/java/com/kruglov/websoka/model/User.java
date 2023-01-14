package com.kruglov.websoka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "users", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String login;
    private String lastname;
    private String status;
    private String biography;
    private String password;
    private String photoname;
    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false, referencedColumnName = "id")
    private Role role;

    public User(String firstname, String login, String password, Role role) {
        this.firstname = firstname;
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
