package internetbanking.demo.controller;



import internetbanking.demo.SaqueDTO.ClienteSaqueDTO;
import internetbanking.demo.model.Cliente;
import internetbanking.demo.model.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private ClienteRepository repository;

    private BigDecimal bigDecimal;
    private long id;
    private java.math.BigDecimal BigDecimal;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }


//    @Autowired// Adiciona uma instância no repository e usar seu metodos como save, update
//    private ClienteRepository clienteRepository;


    @PostMapping
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        return repository.save(cliente);

    }


    @GetMapping
    public List<Cliente> listar() {
        return repository.findAll();
    }

    @PutMapping("/sacar")
    public ResponseEntity<Object> sacar(@RequestBody ClienteSaqueDTO clienteSaqueDTO) {
        Optional<Cliente> cliente = repository.findById(clienteSaqueDTO.getId());
        if (!cliente.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client não encontrado!");
        }
        Cliente clientenovo = cliente.get();
        var soma = clientenovo.getBalance().subtract(clienteSaqueDTO.getBalance());
        if (clienteSaqueDTO.getBalance().compareTo(java.math.BigDecimal.valueOf(0)) <= 0 || clienteSaqueDTO.getBalance().compareTo(clientenovo.getBalance()) == 1) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Digite valor correto de saque");
        } else if (clientenovo.getPlano_exclusive() == true) {
            clientenovo.setBalance(soma);
            return ResponseEntity.status(HttpStatus.OK).body(repository.save(clientenovo));
        } else if (clienteSaqueDTO.getBalance().compareTo(java.math.BigDecimal.valueOf(100)) == 1 && ((clienteSaqueDTO.getBalance().compareTo(java.math.BigDecimal.valueOf(300)) == -1 || clienteSaqueDTO.getBalance().compareTo(java.math.BigDecimal.valueOf(300)) == 0))) {
            var div = clienteSaqueDTO.getBalance().divide(java.math.BigDecimal.valueOf(100)).multiply(java.math.BigDecimal.valueOf(0.4));
            var cl = clientenovo.getBalance().subtract(clienteSaqueDTO.getBalance()).subtract(div);
            clientenovo.setBalance(cl);
            return ResponseEntity.status(HttpStatus.OK).body(repository.save(clientenovo));
        } else if (clienteSaqueDTO.getBalance().compareTo(java.math.BigDecimal.valueOf(300)) == 1) {
            var taxa = clienteSaqueDTO.getBalance().divide(java.math.BigDecimal.valueOf(100).multiply(java.math.BigDecimal.valueOf(1)));
            var resul = clientenovo.getBalance().subtract(clienteSaqueDTO.getBalance()).subtract(taxa);
            clientenovo.setBalance(resul);
            return ResponseEntity.status(HttpStatus.OK).body(repository.save(clientenovo));
        } else {
            clientenovo.setBalance(soma);
            return ResponseEntity.status(HttpStatus.OK).body(repository.save(clientenovo));
        }
    }
    @PutMapping("/depositar")
    public ResponseEntity<Object> depositar(@RequestBody ClienteSaqueDTO clienteSaqueDTO) {
        Optional<Cliente> cliente = repository.findById(clienteSaqueDTO.getId());
        if (!cliente.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        } else if (clienteSaqueDTO.getBalance().compareTo(java.math.BigDecimal.valueOf(0)) == -1 || clienteSaqueDTO.getBalance().compareTo(java.math.BigDecimal.valueOf(0)) == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Deposite valor correto para deposito");
        }
        Cliente clientenovo = cliente.get();
        var add = clientenovo.getBalance().add(clienteSaqueDTO.getBalance());
        clientenovo.setBalance(add);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(clientenovo));


    }
}