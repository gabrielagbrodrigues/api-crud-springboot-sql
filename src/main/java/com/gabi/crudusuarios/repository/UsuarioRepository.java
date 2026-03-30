package com.gabi.crudusuarios.repository;

import com.gabi.crudusuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
}

