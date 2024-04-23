package br.com.consig.customer.register.application.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "tb_customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "name")
    private String name;

    @Column(name = "account_holder")
    private Boolean account_holder;

    @Column(name = "segment")
    private String segment;

    @Column(name = "covenant")
    private String covenant;

}
