package com.example.batchfiles.model.source.complex;

import com.example.batchfiles.model.source.BaseLayout;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;

@Data
@EqualsAndHashCode(callSuper = false)
@Record(minOccurs = 1)
@Fields({
    @Field(at = 0, length = 1, name = "recordType", rid = true, regex = "^(D|G)"),
    @Field(at = 1, length = 1, name = "code", rid = true, regex = "^(0|1|5)")
})
public class ComplexLayoutDetail extends BaseLayout {

    @Field(at=2, length=16)
    private String name;

    @Field(at=18, length=8)
    private String date;

    @Field(at=26, length=13)
    private String value;

}
