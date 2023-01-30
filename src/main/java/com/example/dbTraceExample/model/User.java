package com.example.dbTraceExample.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="USERS")
public class User {
    @GeneratedValue(generator = "USERS_ID_SEQ")
    @SequenceGenerator(name = "USERS_ID_SEQ", sequenceName = "USERS_ID_SEQ", allocationSize = 1)
    @Id
    Integer id;
    String username;
    @Column(name="USER_TYPE")
    @Builder.Default
    String type = "STANDARD";
    
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    List<Contact> contacts;
}
