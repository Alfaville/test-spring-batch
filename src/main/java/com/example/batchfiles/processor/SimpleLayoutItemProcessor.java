package com.example.batchfiles.processor;


import com.example.batchfiles.model.source.BaseLayout;
import com.example.batchfiles.model.source.complex.ComplexLayoutDetail;
import com.example.batchfiles.model.source.complex.ComplexLayoutHeader;
import com.example.batchfiles.model.source.complex.ComplexLayoutTrailer;
import com.example.batchfiles.model.target.UniqueLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class SimpleLayoutItemProcessor implements ItemProcessor<BaseLayout, UniqueLayout> {

    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMyyyy");

    @Override
    public UniqueLayout process(BaseLayout item) {
        UniqueLayout transformedLayout = null;
        if(item instanceof ComplexLayoutHeader) {
            //Dados Header
            log.info("Header: {}", item.toString());
        } else if (item instanceof ComplexLayoutDetail) {
            transformedLayout = UniqueLayout.builder().build();
            ComplexLayoutDetail detail = (ComplexLayoutDetail) item;

            transformedLayout.setCodigoTransacao(detail.getCodigoTransacao());
            transformedLayout.setDataLiquidacao(LocalDate.parse(detail.getDataLiquidacao(), format));
            transformedLayout.setValor(new BigDecimal(detail.getValor()));
            transformedLayout.setDataProcessamento(LocalDate.parse(detail.getDataProcessamento(), format));
            transformedLayout.setSistemaOrigem("SISTEMA_X");
            log.info("Detail: {}", item.toString());
            log.info("Converting (" + item + ") into (" + transformedLayout + ")");
        } else if (item instanceof ComplexLayoutTrailer) {
            //Dados Trailer
            log.info("Trailer: {}", item.toString());
        }
        return transformedLayout;
    }

}
