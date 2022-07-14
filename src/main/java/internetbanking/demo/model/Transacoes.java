package internetbanking.demo.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Entity
public class Transacoes {


    public Transacoes(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_transacao;




    @Column
    @JoinColumn(name = "id_cliente")
    private long id_cliente;


    @Column
    @JsonFormat(pattern = "dd/MM/yyyy", shape = STRING)
    private LocalDateTime data_hora;


    @Column
    @JsonFormat(pattern = "dd/MM/yyyy", shape = STRING)
    private LocalDateTime data_inicio;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy", shape = STRING)
    private LocalDateTime data_fim;
    @Column
    private String tipo_transacao;

    @Column
    private BigDecimal valor;

    public Transacoes(long id_transacao, long id_cliente, LocalDateTime data_hora, LocalDateTime data_inicio, LocalDateTime data_fim, String tipo_transacao, BigDecimal valor) {
        this.id_transacao = id_transacao;
        this.id_cliente = id_cliente;
        this.data_hora = data_hora;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.tipo_transacao = tipo_transacao;
        this.valor = valor;
    }

    public long getId_transacao() {
        return id_transacao;
    }

    public void setId_transacao(long id_transacao) {
        this.id_transacao = id_transacao;
    }

    public long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(long id_cliente) {
        this.id_cliente = id_cliente;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public LocalDateTime getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDateTime data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDateTime getData_fim() {
        return data_fim;
    }

    public void setData_fim(LocalDateTime data_fim) {
        this.data_fim = data_fim;
    }

    public String getTipo_transacao() {
        return tipo_transacao;
    }

    public void setTipo_transacao(String tipo_transacao) {
        this.tipo_transacao = tipo_transacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
