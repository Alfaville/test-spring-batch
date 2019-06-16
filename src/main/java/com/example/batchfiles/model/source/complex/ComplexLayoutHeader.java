package com.example.batchfiles.model.source.complex;

import com.example.batchfiles.model.source.BaseLayout;
import lombok.Data;
import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;

@Data
@Record(name="H",  minOccurs = 1)
@Fields({
        @Field(at = 0, name = "recordType", rid = true, literal = "H")
})
public class ComplexLayoutHeader extends BaseLayout {

    @Field(at=1, length=38)
    private String anyThing;
}
