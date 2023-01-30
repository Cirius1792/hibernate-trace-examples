package com.example.dbTraceExample.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="CONTACTS")
public class Contact {
    @GeneratedValue(generator = "CONTACTS_ID_SEQ")
    @SequenceGenerator(name = "CONTACTS_ID_SEQ", sequenceName = "CONTACTS_ID_SEQ", allocationSize = 1)
    @Id Integer id;
    String phoneNumber;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "OWNED_BY", nullable = false)
    User user;

}
