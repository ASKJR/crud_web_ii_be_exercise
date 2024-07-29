package br.net.kato.crud_web_ii_be_exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import br.net.kato.crud_web_ii_be_exercise.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    public Optional<Pessoa> findByNome(String nome);

}
