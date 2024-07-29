package br.net.kato.crud_web_ii_be_exercise.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.kato.crud_web_ii_be_exercise.model.Login;
import br.net.kato.crud_web_ii_be_exercise.model.Usuario;
import br.net.kato.crud_web_ii_be_exercise.repository.UsuarioRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin
@RestController
public class UsuarioREST {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> obterTodosUsuarios() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obterUsuarioPorId(@PathVariable("id") int id) {
        Optional<Usuario> usuarioFound = usuarioRepository.findById(Integer.valueOf(id));
        if (usuarioFound.isPresent()) {
            return ResponseEntity.ok(usuarioFound.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> inserir(@RequestBody Usuario usuario) {
        Optional<Usuario> u = usuarioRepository.findByLogin(usuario.getLogin());
        if (u.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(u.get());
        } else {
            usuario.setId(-1);
            usuarioRepository.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        }
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> alterar(@PathVariable("id") int id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioFound = usuarioRepository.findById(Integer.valueOf(id));
        if (usuarioFound.isPresent()) {
            usuario.setId(id);
            usuarioRepository.save(usuario);
            return ResponseEntity.ok(usuario);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> remover(@PathVariable("id") int id) {
        Optional<Usuario> usuarioFound = usuarioRepository.findById(Integer.valueOf(id));
        if (usuarioFound.isPresent()) {
            usuarioRepository.delete(usuarioFound.get());
            return ResponseEntity.ok(usuarioFound.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Login login) {
        Optional<Usuario> usuarioFound = usuarioRepository.findByLoginAndSenha(login.getLogin(), login.getSenha());
        if (usuarioFound.isPresent()) {
            return ResponseEntity.ok(usuarioFound.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
