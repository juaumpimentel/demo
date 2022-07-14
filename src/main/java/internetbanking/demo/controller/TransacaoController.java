package internetbanking.demo.controller;


import internetbanking.demo.model.TransacaoRepository;
import internetbanking.demo.model.Transacoes;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoRepository repository;


    public TransacaoController(TransacaoRepository repository) {
        this.repository = repository;
    }



    @PostMapping
    public Transacoes salvarTransacao(@RequestBody Transacoes transacoes){
        transacoes.setData_hora(LocalDateTime.now(ZoneId.of("UTC")));
        transacoes.setData_inicio(LocalDateTime.now(ZoneId.of("UTC")));
        transacoes.setData_fim(LocalDateTime.now(ZoneId.of("UTC")));
        return repository.save(transacoes);


    }


    @GetMapping
    public List<Transacoes>listarTransacoes(){
        return repository.findAll();
    }

    @GetMapping("/historico")
    public List<Transacoes>listarData(@RequestParam("id_cliente")long id_cliente, @RequestParam("data_inicio")LocalDate data_inicio,@RequestParam("data_fim")LocalDate data_fim) {

        var x = repository.getData_between(data_inicio, data_fim, id_cliente);
        return x;
    }
}
