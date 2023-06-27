package com.squad12.cargaHoras.Service;

import com.squad12.cargaHoras.Model.Horas;
import com.squad12.cargaHoras.Repository.HorasRepository;
import com.squad12.cargaHoras.exceptions.TooManyHoursException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.*;

@Service
public class HorasService {

    private Double maxHoras = 10.0;
    private String proyectosURL = "";

    @Autowired
    private HorasRepository horasRepository;

    public Horas createHoras(Horas horas) {
        try {
            if (this.asignacionEsValida(horas)) {
                return horasRepository.save(horas);
            } else {
                throw new TooManyHoursException("Demasiadas horas cargadas");
            }
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }

    }


    public Collection<Horas> getHoras() {
        return horasRepository.findAll();
    }

    public Optional<Horas> findById(Long id) {
        return horasRepository.findById(id);
    }


    public void save(Horas horas) {
        try {
            if (asignacionEsValida(horas)) {
                horasRepository.save(horas);
            } else {
                throw new TooManyHoursException("Demasiadas horas cargadas");
            }
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Collection<Horas> getHorasByRecurso(Long recurso) {
        Collection<Horas> horas = horasRepository.findAll();
        ArrayList<Horas> horasRecurso = new ArrayList<Horas>();

        for (Horas x : horas) {
            if (Objects.equals(x.getRecurso(), recurso)) {
                horasRecurso.add(x);
            }
        }
        return horasRecurso;
    }

    public Double getHorasByProyecto(Long proyecto) {
        Collection<Horas> horas = horasRepository.findAll();

        Double total;
        total = 0.0;
        for (Horas x : horas) {
            if (Objects.equals(x.getProyecto(), proyecto)) {
                total += x.getHoras();
            }
        }
        return total;
    }

    public void deleteById(Long id) {
        horasRepository.deleteById(id);
    }


    private boolean asignacionEsValida(Horas horas) throws IOException {
        return true;
        /*
        boolean valido = true;
        String endpoint = String.format("%s/projects/id", proyectosURL);
        URL url = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
            }
        in.close();
        con.disconnect();

        valido = valido && horas.getHoras() < maxHoras;

        return valido;
        */
    }

    public Double getHorasByTarea(Long proyecto) {
        Collection<Horas> horas = horasRepository.findAll();

        Double total;
        total = 0.0;
        for (Horas x : horas) {
            if (Objects.equals(x.getTarea(), proyecto)) {
                total += x.getHoras();
            }
        }
        return total;
    }
}
