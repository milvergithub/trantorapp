package com.trantor.trantorapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

/**
 * User
 *
 * @author Milver Flores Acevedo
 * @since 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "usr_uid")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    @Column(name = "usr_username")
    private String username;

    @Column(name = "usr_first_name")
    private String firstName;

    @Column(name = "usr_last_name")
    private String lastName;

    @Column(name = "usr_phone_number")
    private String phoneNumber;

    @Column(name = "usr_address")
    private String address;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "from", orphanRemoval = true)
    private List<Message> froms;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "to", orphanRemoval = true)
    private List<Message> tos;
}
