package internetbanking.demo.controller;


import internetbanking.demo.SaqueDTO.TransacoesDTO;
import internetbanking.demo.model.Cliente;
import internetbanking.demo.model.TransacaoRepository;
import internetbanking.demo.model.Transacoes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoRepository repository;


    public TransacaoController(TransacaoRepository repository) {
        this.repository = repository;
    }



    @PostMapping
    public Transacoes salvartransacoes(@RequestBody Transacoes transacoes){
        transacoes.setData_hora(LocalDateTime.now(ZoneId.of("UTC")));
        return repository.save(transacoes);


    }


    @GetMapping
    public ResponseEntity<Object>listar(){
        List<Transacoes> transacoes = this.repository.findAll();
        return new ResponseEntity<>(transacoes, HttpStatus.OK);
    }
}
