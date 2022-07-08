package internetbanking.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TransacaoRepository extends JpaRepository <Transacoes, Long>{
}
