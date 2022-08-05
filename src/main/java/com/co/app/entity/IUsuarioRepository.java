package com.co.app.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("SELECT t FROM Usuario t WHERE t.email = ?1")
    Usuario findByEmail(String email);
	
}
