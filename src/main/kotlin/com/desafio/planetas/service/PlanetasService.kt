package com.desafio.planetas.service

import com.desafio.planetas.model.Planetas
import com.desafio.planetas.model.PlanetasUpdateClima
import com.desafio.planetas.repository.PlanetasRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service
class PlanetasService {

    @Autowired
    lateinit var planetasRepository: PlanetasRepository

    @CacheEvict("planetas", allEntries = true)
    fun create(planeta: Planetas) {
        planetasRepository.save(planeta)
    }

    fun getById(id: Long): Planetas? {
        return planetasRepository.findById(id).orElse(null)
    }

    fun delete(id: Long) {
        planetasRepository.deleteById(id)
    }

    @CacheEvict("planetas", allEntries = true)
    fun update(planeta: Planetas) {
        create(planeta)
    }

    fun searchByLocal(local: String): List<Planetas> {
        return planetasRepository.findAll()
    }

    @Cacheable("planetas")
    fun findAll(pageable: Pageable): Page<Planetas> {
        return planetasRepository.findAll(pageable)
    }

    @CacheEvict("planetas")
    fun updateClima(updateClima: PlanetasUpdateClima) {
        return planetasRepository.updateClima(updateClima.clima, updateClima.id)
    }

}