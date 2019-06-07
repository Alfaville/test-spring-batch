package com.example.batchfiles.model.source.master;

import com.example.batchfiles.model.source.LeiauteBase;
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
@Record(name="T", minOccurs = 1, maxOccurs = 1)
@Fields({
        @Field(at = 0, name = "recordType", rid = true, literal = "T")
})
public class LeiauteTrailer extends LeiauteBase {

    @Field(at=1, length=38)
    private String total;

}
