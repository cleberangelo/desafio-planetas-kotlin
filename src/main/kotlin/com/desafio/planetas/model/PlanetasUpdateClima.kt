package com.desafio.planetas.model

import javax.persistence.*

@Entity
@Table(name = "promocao")
data class PlanetasUpdateClima(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = -1,

    @Column(name = "clima")
    val clima: String = ""
)