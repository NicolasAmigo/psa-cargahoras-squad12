package com.squad12.cargaHoras;

import com.squad12.cargaHoras.Model.Horas;
import com.squad12.cargaHoras.Model.ProyectoSimple;
import com.squad12.cargaHoras.Service.HorasService;
import com.squad12.cargaHoras.Service.ProyectosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@SpringBootApplication
@RestController
public class CargaHorasApplication {

	@Autowired
	private HorasService horasService;

	@Autowired
	private ProyectosService proyectosService;

	public static void main(String[] args) {
		SpringApplication.run(CargaHorasApplication.class, args);
	}

	@GetMapping("/horas")
	public Collection<Horas> getHoras() {
		return horasService.getHoras();
	}

	@GetMapping("/horas/recurso/{recurso}")
	public Collection<Horas> getHorasByRecurso(@PathVariable Long recurso) {
		return horasService.getHorasByRecurso(recurso);
	}

	@GetMapping("/proyectos/recurso/{recurso}")
	public Collection<ProyectoSimple> getProyectosByRecurso(@PathVariable Long recurso) {
		return proyectosService.getProyectoByRecurso(recurso);
	}

    @GetMapping("/horas/proyecto/{proyecto}")
    public Double getHorasByProyecto(@PathVariable Long proyecto) {
        return horasService.getHorasByProyecto(proyecto);
    }

    @GetMapping("/horas/tarea/{tarea}")
    public Double getHorasByTarea(@PathVariable Long tarea) {
        return horasService.getHorasByTarea(tarea);
    }

	@PostMapping("/horas")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Horas createHoras(@RequestBody Horas horas) {
		return horasService.createHoras(horas);
	}

	@PostMapping("/horas/{id}")
	public Horas updateHoras(@PathVariable Long id,@RequestBody Horas horas) {
		horas.setId(id);
		horasService.save(horas);
		return horas;
	}

	@DeleteMapping("/horas/{id}")
	public void deleteHoras(@PathVariable Long id) {
		horasService.deleteById(id);
	}

}
