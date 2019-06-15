package com.example.batchfiles.model.source.complex;

import com.example.batchfiles.model.source.BaseLayout;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;
import org.beanio.types.BigDecimalTypeHandler;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@Record(minOccurs = 1)
@Fields({
    @Field(at = 0, length = 1, name = "recordType", rid = true, regex = "^(D|G)", required = true),
    @Field(at = 1, length = 1, name = "code", rid = true, regex = "^(0|1|5)", required = true)
})
public class ComplexLayoutDetail extends BaseLayout {

    @Field(at=2, length=16)
    private String name;

    @Field(at=18, length=8, required = true, handlerName = "dateHandler")
    private LocalDate date;

    @Field(at=26, length=13, required = true, handlerName = "currencyHandler")
    private BigDecimal value;

}
