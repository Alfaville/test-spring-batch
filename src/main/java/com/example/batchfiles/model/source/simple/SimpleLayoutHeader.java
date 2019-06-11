package com.example.batchfiles.model.source.simple;

import com.example.batchfiles.model.source.BaseLayout;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Record(name="H",  minOccurs = 1, maxOccurs = 1)
@Fields({
    @Field(at = 0, name = "type", rid = true, literal = "H")
})
public class SimpleLayoutHeader extends BaseLayout {

    @Field(at=1, length=8)
    private String date;

    @Field(at=9, length=30)
    private String fileNumber;
}
