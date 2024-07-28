package br.net.kato.crud_web_ii_be_exercise.rest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import br.net.kato.crud_web_ii_be_exercise.model.Pessoa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin
@RestController
public class PessoaREST {
    public static List<Pessoa> lista = new ArrayList<>();

    @GetMapping("/pessoas")
    public ResponseEntity<List<Pessoa>> obterTodasPessoas() {
        return ResponseEntity.ok(lista);
    }
    
    @GetMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> obterPessoaPorId(@PathVariable("id") int id) {
        Pessoa pessoaFound = lista.stream().filter(p -> p.getId() == id).findAny().orElse(null);
        if (pessoaFound == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(pessoaFound);
    }

    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> inserir(@RequestBody Pessoa pessoa) {
       Pessoa p = lista.stream().filter(pes -> pes.getNome().equals(pessoa.getNome())).findAny().orElse(null);
       if (p != null) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
       }
       p = lista.stream().max(Comparator.comparing(Pessoa::getId)).orElse(null);
       if (p == null) {
        pessoa.setId(1);
       } else {
        pessoa.setId(p.getId() + 1);
       }
       lista.add(pessoa);
       return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
    }

    @PutMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> alterar(@PathVariable("id") int id, @RequestBody Pessoa pessoa) {
        Pessoa pessoaFound = lista.stream().filter(p -> p.getId() == id).findAny().orElse(null);
        if (pessoaFound != null) {
            pessoaFound.setNome(pessoa.getNome());
            pessoaFound.setIdade(pessoa.getIdade());
            pessoaFound.setDataNascimento(pessoa.getDataNascimento());
            pessoaFound.setMotorista(pessoa.getMotorista());
            return ResponseEntity.ok(pessoaFound);
        }
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> remover(@PathVariable("id") int id) {
        Pessoa pessoaFound = lista.stream().filter(p -> p.getId() == id).findAny().orElse(null);
        if (pessoaFound != null) {
            lista.removeIf(p -> p.getId() == id);
            return ResponseEntity.ok(pessoaFound);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    static {
        lista.add(new Pessoa(1, "Maria",25,"15-15-2000","AB"));
        lista.add(new Pessoa(2, "Fernanda",20,"15-15-2005","B"));
    }
}
