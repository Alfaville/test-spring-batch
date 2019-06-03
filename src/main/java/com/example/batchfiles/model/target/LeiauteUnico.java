package com.example.batchfiles.model.target;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "TB_LEIAUTE_UNICO")
public class LeiauteUnico {

    @Id
    @Column(name = "COD_TRANSACAO")
    private String codigoTransacao;

    @Column(name = "VALOR")
    private BigDecimal valor;

    @Column(name = "DT_PROCESSAMENTO")
    private LocalDate dataProcessamento;

    @Column(name = "DT_LIQUIDACAO")
    private LocalDate dataLiquidacao;

    @Column(name = "TIPO_SISTEMA")
    private String sistemaOrigem;

}
