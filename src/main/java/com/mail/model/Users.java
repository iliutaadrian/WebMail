package com.mail.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String keyUser;
    private String addressAccount;
    private String passwordAccount;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String luckyNumber;
    private String picture;

    public Users(String addressAccount,
                 String passwordAccount,
                 String firstName,
                 String lastName,
                 String phoneNumber,
                 String luckyNumber,
                 String picture
                 ) {
        this.addressAccount = addressAccount;
        this.passwordAccount = passwordAccount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.luckyNumber = luckyNumber;
        this.picture = picture;
    }
}
