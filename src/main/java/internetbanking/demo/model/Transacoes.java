package internetbanking.demo.model;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;

import java.time.LocalDateTime;

@Entity
public class Transacoes {

    public Transacoes(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private long id_transacao;





    @Column
    private int id_cliente;



    @Column
    private LocalDateTime data_hora;


    @Column
    private String tipo_transacao;

    @Column
    private BigDecimal valor;

    public Transacoes(long id_transacao, int id_cliente, LocalDateTime data_hora, String tipo_transacao, BigDecimal valor) {
        this.id_transacao = id_transacao;
        this.id_cliente = id_cliente;
        this.data_hora = data_hora;
        this.tipo_transacao = tipo_transacao;
        this.valor = valor;
    }

    public long getId_transacao() {
        return id_transacao;
    }

    public void setId_transacao(long id_transacao) {
        this.id_transacao = id_transacao;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
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
