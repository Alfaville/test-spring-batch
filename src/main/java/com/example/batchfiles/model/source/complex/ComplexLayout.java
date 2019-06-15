package com.example.batchfiles.model.source.complex;

import com.example.batchfiles.model.source.BaseLayout;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

import java.util.ArrayList;
import java.util.List;

@Record
@Data
@EqualsAndHashCode(callSuper = false)
public class ComplexLayout extends BaseLayout {

    @Segment
    private ComplexLayoutHeader header;

    @Segment
    private List<ComplexLayoutDetail> details = new ArrayList<>();

    @Segment
    private ComplexLayoutTrailer trailer;

}
