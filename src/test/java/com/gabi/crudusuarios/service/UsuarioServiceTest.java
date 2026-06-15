package com.gabi.crudusuarios.service;

import com.gabi.crudusuarios.model.Usuario;
import com.gabi.crudusuarios.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.gabi.crudusuarios.exception.CpfJaCadastradoException;
import static org.mockito.Mockito.never;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith({MockitoExtension.class})
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void deveBuscarUsuarioPorId(){
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Gabi");
        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuario));
        Usuario resultado = usuarioService.buscarPorId(1L);

        assertEquals("Gabi", resultado.getNome());

    }

    @Test
    void deveLancarErroQuandoUsuarioNaoForEncontrado(){
        when(usuarioRepository.findById(99L))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->{
            usuarioService.buscarPorId(99L);
        });

        assertEquals("Usuário com ID99 não encontrado", exception.getMessage());
    }

    @Test
    void deveSalvarUsuario(){

        Usuario usuario = new Usuario();
        usuario.setNome("Gabi");

        when(usuarioRepository.save(usuario))
                .thenReturn(usuario);

        Usuario resultado = usuarioService.salvar(usuario);

        assertEquals("Gabi", resultado.getNome());

        verify(usuarioRepository).save(usuario);
    }

    @Test
    void deveListarUsuarios(){

        Usuario usuario1 = new Usuario();
        usuario1.setNome("Gabi");

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Samuel");

        List<Usuario> usuarios = List.of(usuario1, usuario2);

        when(usuarioRepository.findAll())
                .thenReturn(usuarios);

        List<Usuario> resultado = usuarioService.listarTodos();

        assertEquals(2, resultado.size());

        assertEquals("Gabi", resultado.get(0).getNome());

        assertEquals("Samuel", resultado.get(1).getNome());

        verify(usuarioRepository).findAll();
    }

    @Test
    void deveAtualizarUsuario(){

        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setId(1L);
        usuarioExistente.setNome("Gabi");

        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setNome("Gabriela");

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuarioExistente));

        when(usuarioRepository.save(usuarioExistente))
                .thenReturn(usuarioExistente);

        Usuario resultado = usuarioService.atualizar(1L, usuarioAtualizado);

        assertEquals("Gabriela", resultado.getNome());

        verify(usuarioRepository).findById(1L);

        verify(usuarioRepository).save(usuarioExistente);

       // System.out.println("Nome: " + resultado.getNome());
    }

    @Test
    void deveDeletarUsuario(){

        Long id = 1L;

        usuarioService.deletar(id);

        verify(usuarioRepository).deleteById(id);
    }

    @Test
    void deveLancarErroAoDeletarUsuarioInexistente() {

        Long id = 99L;

        when(usuarioRepository.existsById(id))
                .thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            usuarioService.deletar(id);
        });
        assertEquals("Usuário com ID99 não encontrado", exception.getMessage());

        verify(usuarioRepository).existsById(id);

    }

    @Test
    void deveLancarExcecaoQuandoCpfJaEstiverCastrado(){
        Usuario usuario = new Usuario();
        usuario.setNome("Gabriela");
        usuario.setEmail("gabi@email.com");
        usuario.setCpf("REMOVED_PASSWORD78900");

        when(usuarioRepository.existsByCpf(usuario.getCpf())).thenReturn(true);

        assertThrows(CpfJaCadastradoException.class, () -> {
            usuarioService.salvar(usuario);
        });

        verify(usuarioRepository, never()).save(usuario);


        }



}
