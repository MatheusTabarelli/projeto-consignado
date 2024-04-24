package br.com.consig.custody.application.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_custody")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Custody {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "contract_date")
    private LocalDateTime contractDate;

    @Column(name = "simulation_id")
    private String simulationId;

}
