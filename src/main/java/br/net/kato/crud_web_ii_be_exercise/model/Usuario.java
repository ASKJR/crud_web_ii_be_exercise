package br.net.kato.crud_web_ii_be_exercise.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_usuario")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usu")
    @Setter
    @Getter
    private int id;

    @Column(name = "nome_usu")
    @Setter
    @Getter
    private String nome;

    @Column(name = "login_usu")
    @Setter
    @Getter
    private String login;

    @Column(name = "senha_usu")
    @Setter
    @Getter
    private String senha;

    @Column(name = "perfil_usu")
    @Setter
    @Getter
    private String perfil;
}
