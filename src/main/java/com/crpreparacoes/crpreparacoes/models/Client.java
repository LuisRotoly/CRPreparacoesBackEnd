package com.crpreparacoes.crpreparacoes.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="cpfcnpj")
    private String cpfcnpj;

    @Column(name="address")
    private String address;

    @Column(name="phone")
    private String phone;

    @Column(name="nickname")
    private String nickname;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;
}
