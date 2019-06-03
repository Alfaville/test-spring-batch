package com.example.batchfiles.processor;


import com.example.batchfiles.model.source.LeiauteA1;
import com.example.batchfiles.model.target.LeiauteUnico;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class LeiauteA1ItemProcessor implements ItemProcessor<LeiauteA1, LeiauteUnico> {

    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMyyyy");

    @Override
    public LeiauteUnico process(LeiauteA1 item) throws Exception {
        LeiauteUnico transformedLeiaute = LeiauteUnico.builder()
                .codigoTransacao(item.getCodigoTransacao())
                .dataLiquidacao(LocalDate.parse(item.getDataLiquidacao(), format))
                .valor(new BigDecimal(item.getValor()))
                .dataProcessamento(LocalDate.parse(item.getDataProcessamento(), format))
                .sistemaOrigem("SISTEMA_X")
                .build();
        log.info("Converting (" + item + ") into (" + transformedLeiaute + ")");
        return transformedLeiaute;
    }

}
