package com.financeiro.patrimonio.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patrimonio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{campo.valor.obrigatorio}")
    @Column(nullable = false)
    private BigDecimal valor;

    @NotNull(message = "O campo 'mês' é obrigatório.")
    @Min(value = 1, message = "O campo 'mês' deve ser um número entre 1 e 12.")
    @Max(value = 12, message = "O campo 'mês' deve ser um número entre 1 e 12.")
    @Column
    private Integer mes;

    @NotNull(message = "O campo 'ano' é obrigatório.")
    @Min(value = 1900, message = "O campo 'ano' deve ser um número maior ou igual a 1900.")
    @Max(value = 2100, message = "O campo 'ano' deve ser um número menor ou igual a 2100.")
    @Column(nullable = false)
    private Integer ano;

    @Column(name = "nome_mes")
    private String nomeMes;

    @PrePersist
    @PreUpdate
    public void atualizarNomeMes() {
        switch (this.mes) {
            case 1:
                this.nomeMes = "Janeiro";
                break;
            case 2:
                this.nomeMes = "Fevereiro";
                break;
            case 3:
                this.nomeMes = "Março";
                break;
            case 4:
                this.nomeMes = "Abril";
                break;
            case 5:
                this.nomeMes = "Maio";
                break;
            case 6:
                this.nomeMes = "Junho";
                break;
            case 7:
                this.nomeMes = "Julho";
                break;
            case 8:
                this.nomeMes = "Agosto";
                break;
            case 9:
                this.nomeMes = "Setembro";
                break;
            case 10:
                this.nomeMes = "Outubro";
                break;
            case 11:
                this.nomeMes = "Novembro";
                break;
            case 12:
                this.nomeMes = "Dezembro";
                break;
            default:
                throw new IllegalArgumentException("Mês inválido: " + this.mes);
        }

        this.data = LocalDate.now();
    }

    @Column(name = "data", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;


    public void Patrimonio(Long id, BigDecimal valor, Integer mes, Integer ano, LocalDate data) {
        this.id = id;
        this.valor = valor;
        this.mes = mes;
        this.ano = ano;
        this.data = data;
    }


}
