package com.example.batchfiles.model.source.complex;

import com.example.batchfiles.model.source.BaseLayout;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;

@Data
@EqualsAndHashCode(callSuper = false)
@Record(name="T", minOccurs = 1)
@Fields({
        @Field(at = 0, name = "recordType", rid = true, literal = "T")
})
public class ComplexLayoutTrailer extends BaseLayout {

    @Field(at=1, length=38)
    private String total;

}
