package com.example.batchfiles.model.source.simple;

import com.example.batchfiles.model.source.BaseLayout;
import lombok.Data;
import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;

@Data
@Record(name="D", minOccurs = 1)
@Fields({
    @Field(at = 0, name = "type", rid = true, literal = "D")
})
public class SimpleLayoutDetail extends BaseLayout {

    @Field(at=2, length=16)
    private String name;

    @Field(at=18, length=8)
    private String date;

    @Field(at=26, length=13)
    private String value;

}
