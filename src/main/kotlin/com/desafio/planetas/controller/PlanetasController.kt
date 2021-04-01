package com.desafio.planetas.controller

import com.desafio.planetas.exception.NotFoundException
import com.desafio.planetas.model.Planetas
import com.desafio.planetas.model.PlanetasUpdateClima
import com.desafio.planetas.model.Resposta
import com.desafio.planetas.service.PlanetasService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/planetas")
class PlanetasController {

    @Autowired
    lateinit var planetasService: PlanetasService

    @GetMapping("/{id}")
    fun read(@PathVariable id: Long): ResponseEntity<Planetas?> {
        var planeta = planetasService.getById(id) ?: throw NotFoundException("planeta ${id} nao encontrada")

        return ResponseEntity(planeta, HttpStatus.OK)
    }

    @PostMapping
    fun create(@RequestBody planetas: Planetas): ResponseEntity<Resposta> {
        planetasService.create(planetas)

        return ResponseEntity(Resposta("OK", Date()), HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (planetasService.getById(id) != null) {
            planetasService.delete(id)
            status = HttpStatus.ACCEPTED
        }

        return ResponseEntity(Unit, status)
    }

    @PutMapping
    fun update(@RequestBody planetas: Planetas): ResponseEntity<Unit> {
        planetasService.update(planetas)

        return ResponseEntity(Unit, HttpStatus.ACCEPTED)
    }

    @GetMapping
    fun search(@RequestParam(required = false, defaultValue = "") localFilter: String): ResponseEntity<List<Planetas>> {
        val lista = planetasService.searchByLocal(localFilter)
        val status = if (lista.isEmpty()) HttpStatus.NOT_FOUND else HttpStatus.OK

        return ResponseEntity(lista, status)
    }

    @GetMapping("/listar")
    fun findAll(
        @PageableDefault(size = 4, sort = ["id"], direction = Sort.Direction.DESC) pageable: Pageable
    ): ResponseEntity<Page<Planetas>> {
        return ResponseEntity.ok(planetasService.findAll(pageable))
    }

    @PutMapping("/update/clima")
    fun updateClima(@RequestBody updateClima: PlanetasUpdateClima): ResponseEntity<Unit> {
        return ResponseEntity.ok(planetasService.updateClima(updateClima))
    }

}