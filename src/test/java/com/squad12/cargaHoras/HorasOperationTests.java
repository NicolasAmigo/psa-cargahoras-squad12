package com.squad12.cargaHoras;

import com.squad12.cargaHoras.Model.Horas;
import com.squad12.cargaHoras.Service.HorasService;
import com.squad12.cargaHoras.Service.ProyectosService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = CargaHorasApplication.class)
@WebAppConfiguration
@TestPropertySource(locations = "src/test/resources:test.properties")
public class HorasOperationTests extends CucumberSpringConfiguration {
    @Autowired
    private HorasService horasService;

    private Horas horas;


    @When("se crea un nuevo registro de recurso {int}")
    public void seCreaUnNuevoRegistroDeRecurso(int arg0) {
        Horas t = new Horas();
        Long arg1 = (long) arg0;
        t.setRecurso(arg1);
        horasService.save(t);
    }

    @Then("se gurdara el nuevo registro de recurso {int}")
    public void seGurdaraElNuevoRegistroDeRecurso(int arg0) {
        Long arg1 = (long) arg0;
        assert(!horasService.getHorasByRecurso(arg1).isEmpty());
    }

    @Given("un registro de recurso {int} y tarea {int}")
    public void unRegistroDeRecursoYTarea(int arg0, int arg1) {
        Horas h = new Horas();
        h.setRecurso((long) arg0);
        h.setTarea((long) arg1);
        this.horas = horasService.createHoras(h);
    }

    @When("se modica la tarea a {int}")
    public void seModicaLaTareaA(int arg0) {
        horas.setTarea((long) arg0);
        horasService.save(horas);
    }

    @Then("se guardara la tarea como {int}")
    public void seGuardaraLaTareaComo(int arg0) {
        assert(horas.getTarea() == (long) arg0);
    }

    @Given("un registro de recurso")
    public void unRegistroDeRecurso() {
        this.horas = horasService.createHoras(new Horas());
    }

    @When("se elimina el registro")
    public void seEliminaElRegistro() {
        horasService.deleteById(horas.getId());
    }

    @Then("no existira el registro")
    public void noExistiraElRegistro() {
        assert(horasService.findById(horas.getId()).isEmpty());
    }
}
