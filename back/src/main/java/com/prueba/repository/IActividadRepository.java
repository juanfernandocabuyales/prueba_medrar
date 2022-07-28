package com.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.entity.Actividad;

@Repository
public interface IActividadRepository extends JpaRepository<Actividad, Long> {

}
