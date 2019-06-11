package com.example.batchfiles.model.source.simple;

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
public class SimpleLayout extends BaseLayout {

    @Segment
    private SimpleLayoutHeader header;

    @Segment
    private List<SimpleLayoutDetail> details = new ArrayList<>();

    @Segment
    private SimpleLayoutTrailer trailer;

}
