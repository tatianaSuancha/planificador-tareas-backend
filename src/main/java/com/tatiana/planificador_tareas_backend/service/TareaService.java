package com.tatiana.planificador_tareas_backend.service;

import com.tatiana.planificador_tareas_backend.dto.TareaDTO;
import com.tatiana.planificador_tareas_backend.exception.RecursoNoEncontradoException;
import com.tatiana.planificador_tareas_backend.model.Tarea;
import com.tatiana.planificador_tareas_backend.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public List<TareaDTO> obtenerTodas() {
        return tareaRepository.findAll().stream()
                .map(this::aDTO)
                .toList();
    }

    public TareaDTO crear(TareaDTO tareaDTO) {
        Tarea tarea = aEntidad(tareaDTO);
        return aDTO(tareaRepository.save(tarea));
    }

    public TareaDTO actualizar(Long id, TareaDTO tareaDTO) {
        Tarea tareaExistente = tareaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró la tarea con id " + id));

        tareaExistente.setName(tareaDTO.getName());
        tareaExistente.setDescription(tareaDTO.getDescription());
        tareaExistente.setDueDate(tareaDTO.getDueDate());
        tareaExistente.setStatus(tareaDTO.getStatus());
        tareaExistente.setPriority(tareaDTO.getPriority());
        tareaExistente.setCompletada(tareaDTO.isCompletada());

        return aDTO(tareaRepository.save(tareaExistente));
    }

    public void eliminar(Long id) {
        if (!tareaRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No se encontró la tarea con id " + id);
        }
        tareaRepository.deleteById(id);
    }

    private TareaDTO aDTO(Tarea tarea) {
        TareaDTO dto = new TareaDTO();
        dto.setId(tarea.getId());
        dto.setName(tarea.getName());
        dto.setDescription(tarea.getDescription());
        dto.setDueDate(tarea.getDueDate());
        dto.setStatus(tarea.getStatus());
        dto.setPriority(tarea.getPriority());
        dto.setCompletada(tarea.isCompletada());
        return dto;
    }

    private Tarea aEntidad(TareaDTO dto) {
        Tarea tarea = new Tarea();
        tarea.setName(dto.getName());
        tarea.setDescription(dto.getDescription());
        tarea.setDueDate(dto.getDueDate());
        tarea.setStatus(dto.getStatus());
        tarea.setPriority(dto.getPriority());
        tarea.setCompletada(dto.isCompletada());
        return tarea;
    }
}
