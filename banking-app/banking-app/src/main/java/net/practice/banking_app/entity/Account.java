package net.practice.banking_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
@Entity
public class Account {
    @Id  //to make id a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //there are several types of strategies, identity is used for auto-incrementing unique values in primary key
    private Long id;

    @Column(name = "account_holder_name")
    private String accountHolderName;

    private double balance;
}
