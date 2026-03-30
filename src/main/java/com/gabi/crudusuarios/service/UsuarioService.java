package com.gabi.crudusuarios.service;

import com.gabi.crudusuarios.exception.UsuarioNaoEncontradoException;
import com.gabi.crudusuarios.model.Usuario;
import com.gabi.crudusuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;


    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> lisarTodos(){
        return usuarioRepository.findAll();
    }


    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário com ID" + id + " não encontrado"));
    }


    public Usuario atualizar(Long id, Usuario usuarioAtualizado){
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);

        if (usuarioExistente !=null){
            usuarioExistente.setNome(usuarioAtualizado.getNome());
            usuarioExistente.setEmail(usuarioAtualizado.getEmail());
            return usuarioRepository.save(usuarioExistente);
        }

        return null;
    }



    public void deletar(Long id){
        usuarioRepository.deleteById(id);
    }

}
