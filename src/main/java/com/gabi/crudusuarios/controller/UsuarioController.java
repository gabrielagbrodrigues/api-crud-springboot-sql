package com.gabi.crudusuarios.controller;

import com.gabi.crudusuarios.model.Usuario;
import com.gabi.crudusuarios.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Cadastrar novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario){

        return usuarioService.salvar(usuario);
    }

    @Operation(summary = "Listar todos os usuários")
    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
    @GetMapping
    public List<Usuario> listar(){
        return usuarioService.listarTodos();
    }

    @Operation(summary = "Buscar usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscaPorUsuario(@PathVariable Long id){
        Usuario usuario = usuarioService.buscarPorId(id);
        if (usuario != null){
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Atualizar usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario){
        return usuarioService.atualizar(id,usuario);
    }

    @Operation(summary = "Excluir usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        usuarioService.deletar(id);
    }
}
