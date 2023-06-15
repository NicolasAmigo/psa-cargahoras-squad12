package com.squad12.cargaHoras;

import com.squad12.cargaHoras.Model.Horas;
import com.squad12.cargaHoras.Service.HorasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@SpringBootApplication
@RestController
public class CargaHorasApplication {

	@Autowired
	private HorasService horasService;

	public static void main(String[] args) {
		SpringApplication.run(CargaHorasApplication.class, args);
	}

	@GetMapping("/horas")
	public Collection<Horas> getHoras() {
		return horasService.getHoras();
	}

	@GetMapping("/horas/recurso/{recurso}")
	public Collection<Horas> getHorasByRecurso(@PathVariable String recurso) {
		return horasService.getHorasByRecurso(recurso);
	}

	@PostMapping("/horas")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Horas createHoras(@RequestBody Horas horas) {
		return horasService.createHoras(horas);
	}

	@PostMapping("/horas/{id}")
	public Horas updateHoras(@PathVariable Long id,@RequestBody Horas horas) {
		horasService.save(horas);
		return horas;
	}

	@DeleteMapping("/horas/{id}")
	public void deleteHoras(@PathVariable Long id) {
		horasService.deleteById(id);
	}

}
