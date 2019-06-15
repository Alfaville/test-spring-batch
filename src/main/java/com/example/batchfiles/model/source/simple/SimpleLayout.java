package com.example.batchfiles.model.source.simple;

import com.example.batchfiles.model.source.BaseLayout;
import lombok.Data;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

import java.util.ArrayList;
import java.util.List;

@Record
@Data
public class SimpleLayout extends BaseLayout {

    @Segment
    private SimpleLayoutHeader header;

    @Segment
    private List<SimpleLayoutDetail> details = new ArrayList<>();

    @Segment
    private SimpleLayoutTrailer trailer;

}
