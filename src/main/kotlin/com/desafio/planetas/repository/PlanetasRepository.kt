package com.desafio.planetas.repository

import com.desafio.planetas.model.Planetas
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface PlanetasRepository : JpaRepository<Planetas, Long> {

    @Query("UPDATE Planetas p SET p.clima = :clima WHERE p.id = :id")
    @Transactional
    @Modifying
    fun updateClima(@Param("clima") clima: String, @Param("id") id: Long)
}