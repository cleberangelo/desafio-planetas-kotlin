package com.desafio.planetas.model

import javax.persistence.*

@Entity
@Table(name = "planetas")
data class Planetas(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = -1,

    @Column(name = "nome")
    val nome: String? = null,

    @Column(name = "clima")
    val clima: String? = null,

    @Column(name = "terreno")
    val terreno: String? = null,
)