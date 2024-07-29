package br.net.kato.crud_web_ii_be_exercise.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import br.net.kato.crud_web_ii_be_exercise.model.Pessoa;
import br.net.kato.crud_web_ii_be_exercise.repository.PessoaRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin
@RestController
public class PessoaREST {
    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping("/pessoas")
    public ResponseEntity<List<Pessoa>> obterTodosPessoas() {
        return ResponseEntity.ok(pessoaRepository.findAll());
    }

    @GetMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> obterPessoaPorId(@PathVariable("id") int id) {
        Optional<Pessoa> pessoaFound = pessoaRepository.findById(Integer.valueOf(id));
        if (pessoaFound.isPresent()) {
            return ResponseEntity.ok(pessoaFound.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> inserir(@RequestBody Pessoa pessoa) {
        Optional<Pessoa> u = pessoaRepository.findByNome(pessoa.getNome());
        if (u.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(u.get());
        } else {
            pessoa.setId(-1);
            pessoaRepository.save(pessoa);
            return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
        }
    }

    @PutMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> alterar(@PathVariable("id") int id, @RequestBody Pessoa pessoa) {
        Optional<Pessoa> pessoaFound = pessoaRepository.findById(Integer.valueOf(id));
        if (pessoaFound.isPresent()) {
            pessoa.setId(id);
            pessoaRepository.save(pessoa);
            return ResponseEntity.ok(pessoa);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> remover(@PathVariable("id") int id) {
        Optional<Pessoa> pessoaFound = pessoaRepository.findById(Integer.valueOf(id));
        if (pessoaFound.isPresent()) {
            pessoaRepository.delete(pessoaFound.get());
            return ResponseEntity.ok(pessoaFound.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
