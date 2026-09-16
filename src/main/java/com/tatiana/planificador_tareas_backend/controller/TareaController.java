package com.tatiana.planificador_tareas_backend.controller;

import com.tatiana.planificador_tareas_backend.dto.TareaDTO;
import com.tatiana.planificador_tareas_backend.service.TareaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public List<TareaDTO> obtenerTareas() {
        return tareaService.obtenerTodas();
    }

    @PostMapping
    public ResponseEntity<TareaDTO> crearTarea(@Valid @RequestBody TareaDTO tareaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tareaService.crear(tareaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareaDTO> actualizarTarea(@PathVariable Long id, @Valid @RequestBody TareaDTO tareaDTO) {
        return ResponseEntity.ok(tareaService.actualizar(id, tareaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id) {
        tareaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
