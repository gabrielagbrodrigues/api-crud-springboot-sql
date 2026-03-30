package com.gabi.crudusuarios.controller;

import com.gabi.crudusuarios.model.Usuario;
import com.gabi.crudusuarios.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }


    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario){
        return usuarioService.salvar(usuario);
    }


    @GetMapping
    public List<Usuario> listar(){
        return usuarioService.lisarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscaPorUsuario(@PathVariable Long id){
        Usuario usuario = usuarioService.buscarPorId(id);
        if (usuario != null){
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario){
        return usuarioService.atualizar(id,usuario);
    }


    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        usuarioService.deletar(id);
    }
}
