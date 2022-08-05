package com.co.app.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITelefonoRepository extends JpaRepository<Telefono, Long> {

}
