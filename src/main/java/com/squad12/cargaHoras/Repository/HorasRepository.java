package com.squad12.cargaHoras.Repository;

import com.squad12.cargaHoras.Model.Horas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorasRepository extends CrudRepository<Horas, Long> {

    Horas findHorasById(Long id);


    @Override
    List<Horas> findAll();
}
