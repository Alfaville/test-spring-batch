package com.example.batchfiles.model.source.simple;

import com.example.batchfiles.model.source.BaseLayout;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Record(name="D", minOccurs = 1, maxOccurs = Integer.MAX_VALUE, collection = List.class)
@Fields({
    @Field(at = 0, name = "type", rid = true, literal = "D")
})
public class SimpleLayoutDetail extends BaseLayout {

    @Field(at=1, length=10)
    private String recordCode;

    @Field(at=11, length=8)
    private String processDate;

    @Field(at=19, length=8)
    private String paymentDate;

    @Field(at=27, length=12)
    private String valor;

}
