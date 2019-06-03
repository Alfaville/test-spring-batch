package com.example.batchfiles.model.source;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

@Record
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LeiauteA1 {

    @Field(at=0, length=10)
    private String codigoTransacao;

    @Field(at=10, length=12)
    private String valor;

    @Field(at=22, length=8)
    private String dataProcessamento;

    @Field(at=30, length=8)
    private String dataLiquidacao;

}
