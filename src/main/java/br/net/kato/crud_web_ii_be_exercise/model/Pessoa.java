package br.net.kato.crud_web_ii_be_exercise.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_pessoa")
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pes")
    @Setter
    @Getter
    private int id;

    @Column(name = "nome_pes")
    @Setter
    @Getter
    private String nome;

    @Column(name = "idade_pes")
    @Setter
    @Getter
    private int idade;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_pes")
    @Setter
    @Getter
    private LocalDate dataNascimento;

    @Column(name = "motorista_pes")
    @Setter
    @Getter
    private String motorista;
}
