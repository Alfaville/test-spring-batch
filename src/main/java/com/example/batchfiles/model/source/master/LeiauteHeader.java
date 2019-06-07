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
@Record(name="H",  minOccurs = 1, maxOccurs = 1)
@Fields({
        @Field(at = 0, name = "recordType", rid = true, literal = "H")
})
public class LeiauteHeader extends LeiauteBase {

    @Field(at=1, length=1)
    private String tipo;

    @Field(at=2, length=37)
    private String codigoTransacao;
}
