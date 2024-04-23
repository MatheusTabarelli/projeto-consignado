package br.com.consig.customer.register.port.outbound;

import br.com.consig.customer.register.application.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    List<Customer> findAll();

    Customer findByCpf(String cpf);

}
