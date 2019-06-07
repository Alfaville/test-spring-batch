package com.example.batchfiles.processor;


import com.example.batchfiles.model.source.LeiauteBase;
import com.example.batchfiles.model.source.master.LeiauteDetail;
import com.example.batchfiles.model.source.master.LeiauteHeader;
import com.example.batchfiles.model.source.master.LeiauteTrailer;
import com.example.batchfiles.model.target.LeiauteUnico;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class LeiauteA1ItemProcessorMaster implements ItemProcessor<LeiauteBase, LeiauteUnico> {

    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMyyyy");

    @Override
    public LeiauteUnico process(LeiauteBase item) {
        LeiauteUnico transformedLeiaute = null;
        if(item instanceof LeiauteHeader) {
            //Dados Header
            log.info("Header: {}", item.toString());
        } else if (item instanceof LeiauteDetail) {
            transformedLeiaute = LeiauteUnico.builder().build();
            transformedLeiaute.setCodigoTransacao(((LeiauteDetail) item).getCodigoTransacao());
            transformedLeiaute.setDataLiquidacao(LocalDate.parse(((LeiauteDetail) item).getDataLiquidacao(), format));
            transformedLeiaute.setValor(new BigDecimal(((LeiauteDetail) item).getValor()));
            transformedLeiaute.setDataProcessamento(LocalDate.parse(((LeiauteDetail) item).getDataProcessamento(), format));
            transformedLeiaute.setSistemaOrigem("SISTEMA_X");
            log.info("Detail: {}", item.toString());
            log.info("Converting (" + item + ") into (" + transformedLeiaute + ")");
        } else if (item instanceof LeiauteTrailer) {
            //Dados Trailer
            log.info("Trailer: {}", item.toString());
        }
        return transformedLeiaute;
    }

}
