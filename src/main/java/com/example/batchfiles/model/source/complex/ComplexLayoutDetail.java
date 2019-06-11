package com.example.batchfiles.model.source.complex;

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
        @Field(at = 0, name = "recordType", rid = true, literal = "D")
})
public class ComplexLayoutDetail extends BaseLayout {

    @Field(at=1, length=10)
    private String codigoTransacao;

    @Field(at=11, length=12)
    private String valor;

    @Field(at=23, length=8)
    private String dataProcessamento;

    @Field(at=31, length=8)
    private String dataLiquidacao;

}
