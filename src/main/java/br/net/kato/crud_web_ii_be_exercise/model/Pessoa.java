package br.net.kato.crud_web_ii_be_exercise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
    @Setter @Getter
    private int id;

    @Setter @Getter
    private String nome;

    @Setter @Getter
    private int idade;

    @Setter @Getter
    private String dataNascimento;
    
    @Setter @Getter
    private String motorista;
}