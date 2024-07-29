package br.net.kato.crud_web_ii_be_exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import br.net.kato.crud_web_ii_be_exercise.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    public Optional<Usuario> findByLogin(String login);

    public Optional<Usuario> findByLoginAndSenha(String login, String senha);
}