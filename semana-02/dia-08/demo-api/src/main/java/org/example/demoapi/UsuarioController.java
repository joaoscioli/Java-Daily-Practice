package org.example.demoapi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        // Usando record pattern (JEP 440) para validação
        if (usuario instanceof Usuario(Long id, String nome, String email)) {
            if (nome == null || nome.isBlank() || email == null || email.isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome e email são obrigatórios");
            }
        }
        return usuarioRepository.save(usuario);
    }

    @GetMapping
    public Iterable<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        // Verifica se o usuário existe
        usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        // Usando record pattern para extrair e validar dados
        if (usuario instanceof Usuario(Long _, String nome, String email)) {
            if (nome == null || nome.isBlank() || email == null || email.isBlank()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome e email são obrigatórios");
            }
            // Cria um novo record com o ID do path
            Usuario atualizado = new Usuario(id, nome, email);
            return usuarioRepository.save(atualizado);
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