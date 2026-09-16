package com.tatiana.planificador_tareas_backend.repository;

import com.tatiana.planificador_tareas_backend.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
}
