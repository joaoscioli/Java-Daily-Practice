package org.example.demoapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;


    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        // Usando record pattern (JEP 440) para validação
        if (usuario instanceof Usuario(Long id, String nome, String email)) {
            if (nome == null || nome.isBlank() || email == null || email.isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome e Email são obrigatórios");
            }
        }
        return usuarioRepository.save(usuario);
    }

    @GetMapping
    public Iterable<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        // Verifica se o usuario existe
        usuarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        // Usando record pattern para extrair e validar dados
        if (usuario instanceof Usuario(Long id, String nome, String email)) {
            if (nome == null || nome.isBlank() || email == null || email.isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome e email são obrigatórios");
            }
            // Cria um novo record com ID do path
            Usuario novoUsuario = new Usuario(id, nome, email);
            return usuarioRepository.save(novoUsuario);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos");
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        usuarioRepository.deleteById(id);
    }
}
