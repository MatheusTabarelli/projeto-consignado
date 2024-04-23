package br.com.consig.custody.port.outbound;

import br.com.consig.custody.application.domain.entity.Custody;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustodyRepository extends JpaRepository<Custody, Long> {

    List<Custody> findAll();

    Custody save(Custody custody);

}
