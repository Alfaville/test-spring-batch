package com.example.batchfiles.model.source.complex;

import com.example.batchfiles.model.source.BaseLayout;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

import java.util.ArrayList;
import java.util.List;

@Record(minOccurs = 1, maxOccurs = 1)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ComplexLayout extends BaseLayout {

    @Segment
    private ComplexLayoutHeader header;

    @Segment
    private List<ComplexLayoutDetail> details = new ArrayList<>();

    @Segment
    private ComplexLayoutTrailer trailer;

}
