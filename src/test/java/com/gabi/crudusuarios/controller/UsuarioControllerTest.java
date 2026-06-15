package com.gabi.crudusuarios.controller;

import com.gabi.crudusuarios.model.Usuario;
import com.gabi.crudusuarios.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.List;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UsuarioController.class)


public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveListarTodosOsUsuarios() throws Exception{

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Gabriela");
        usuario.setEmail("gabi@email.com");

        when(usuarioService.listarTodos())
                .thenReturn(List.of(usuario));

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Gabriela"))
                .andExpect(jsonPath("$[0].email").value("gabi@email.com"));

    }

    @Test
    void deveBuscarUsuarioPorIdComSucesso() throws Exception{

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Gabriela");
        usuario.setEmail("gabi@email.com");

        when(usuarioService.buscarPorId(1L))
                .thenReturn(usuario);

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Gabriela"))
                .andExpect(jsonPath("$.email").value("gabi@email.com"));

    }

    @Test
    void deveRetornar404QuandoUsuarioNaoExistir() throws Exception{

        when(usuarioService.buscarPorId(1L))
                .thenReturn(null);

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveCriarUsuarioComSucesso() throws Exception{

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Gabriela");
        usuario.setEmail("gabi@email.com");

        when(usuarioService.salvar(Mockito.any(Usuario.class)))
                .thenReturn(usuario);

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Gabriela"))
                .andExpect(jsonPath("$.email").value("gabi@email.com"));



    }
    @Test
    void deveAtualizarUsuarioComSucesso() throws Exception{

        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setId(1L);
        usuarioAtualizado.setNome("Gabriela Atualizada");
        usuarioAtualizado.setEmail("gabi.atualizada@email.com");

        when(usuarioService.atualizar(Mockito.eq(1L), Mockito.any(Usuario.class)))
                .thenReturn(usuarioAtualizado);

        mockMvc.perform(put("/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioAtualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Gabriela Atualizada"))
                .andExpect(jsonPath("$.email").value("gabi.atualizada@email.com"));

    }

    @Test
    void deveDeletarUsuarioComSucesso() throws Exception{

        Mockito.doNothing()
                .when(usuarioService)
                .deletar(1L);

        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isOk());
    }



}
