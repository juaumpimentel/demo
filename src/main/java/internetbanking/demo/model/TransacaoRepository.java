package internetbanking.demo.model;

import org.hibernate.criterion.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public interface TransacaoRepository extends JpaRepository <Transacoes, Long> {
    @Modifying
    @Query(nativeQuery = true, value="SELECT t.id_cliente, t.data_inicio, t.data_fim FROM transacoes  WHERE id_cliente =:id_cliente between :data_inicio and :data_fim")
    List<Transacoes> getData_between(@Param("data_inicio") LocalDate date, @Param("data_fim") LocalDate date2, @Param("id_cliente") long id);
}
