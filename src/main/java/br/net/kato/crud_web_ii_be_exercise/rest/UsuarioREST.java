package br.net.kato.crud_web_ii_be_exercise.rest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.kato.crud_web_ii_be_exercise.model.Login;
import br.net.kato.crud_web_ii_be_exercise.model.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin
@RestController
public class UsuarioREST {
    public static List<Usuario> lista = new ArrayList<>();

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> obterTodosUsuarios() {
        return ResponseEntity.ok(lista);
    }
    
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obterUsuarioPorId(@PathVariable("id") int id) {
        Usuario usuarioFound = lista.stream().filter(u -> u.getId() == id).findAny().orElse(null);
        if (usuarioFound ==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(usuarioFound);
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> inserir(@RequestBody Usuario usuario) {
       Usuario u = lista.stream().filter(usu -> usu.getLogin().equals(usuario.getLogin())).findAny().orElse(null);
       if (u != null) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
       }
       u = lista.stream().max(Comparator.comparing(Usuario::getId)).orElse(null);
       if (u == null) {
        usuario.setId(1);
       } else {
        usuario.setId(u.getId() + 1);
       }
       lista.add(usuario);
       return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> alterar(@PathVariable("id") int id, @RequestBody Usuario usuario) {
        Usuario usuarioFound = lista.stream().filter(u -> u.getId() == id).findAny().orElse(null);
        if (usuarioFound != null) {
            usuarioFound.setNome(usuario.getNome());
            usuarioFound.setLogin(usuario.getLogin());
            usuarioFound.setSenha(usuario.getSenha());
            usuarioFound.setPerfil(usuario.getPerfil());
            return ResponseEntity.ok(usuarioFound);
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> remover(@PathVariable("id") int id) {
        Usuario usuarioFound = lista.stream().filter(u -> u.getId() == id).findAny().orElse(null);
        if (usuarioFound != null) {
            lista.removeIf(u -> u.getId() == id);
            return ResponseEntity.ok(usuarioFound);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Login login) {
        Usuario usuarioFound = lista.stream().filter(u -> 
            u.getLogin().equals(login.getLogin()) && 
            u.getSenha().equals(login.getSenha())
        )
        .findAny()
        .orElse(null);
        if (usuarioFound != null) {
            return ResponseEntity.ok(usuarioFound);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    
    

    static {
        lista.add(new Usuario(1, "administr", "admin", "admin", "ADMIN"));
        
        lista.add(new Usuario(2, "gerent", "gerente", "gerente", "GERENTE"));

        
        lista.add(new Usuario(3, "funcion", "func", "func", "FUNC"));
    }


}
