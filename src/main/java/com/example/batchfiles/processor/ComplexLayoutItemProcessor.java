package com.example.batchfiles.processor;


import com.example.batchfiles.model.source.BaseLayout;
import com.example.batchfiles.model.source.complex.ComplexLayoutDetail;
import com.example.batchfiles.model.source.simple.SimpleLayoutHeader;
import com.example.batchfiles.model.source.simple.SimpleLayoutTrailer;
import com.example.batchfiles.model.target.UniqueLayout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class ComplexLayoutItemProcessor implements ItemProcessor<BaseLayout, UniqueLayout> {



    @Override
    public UniqueLayout process(BaseLayout item) {
        UniqueLayout transformedLayout = null;
        if(item instanceof SimpleLayoutHeader) {
            //Dados Header
            log.info("Header: {}", item.toString());
        } else if (item instanceof ComplexLayoutDetail) {
            transformedLayout = new UniqueLayout();
            ComplexLayoutDetail detail = (ComplexLayoutDetail) item;

            transformedLayout.setName(detail.getName());
            transformedLayout.setDate(detail.getDate());
            transformedLayout.setValue(detail.getValue());
            log.info("Detail: {}", item.toString());
            log.info("Converting (" + item + ") into (" + transformedLayout + ")");
        } else if (item instanceof SimpleLayoutTrailer) {
            //Dados Trailer
            log.info("Trailer: {}", item.toString());
        }
        return transformedLayout;
    }

}
