package com.example.batchfiles.model.target;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "TB_UNIQUE_LAYOUT")
public class UniqueLayout {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUE")
    private BigDecimal value;

    @Column(name = "DT_DATE")
    private LocalDate date;

    @Column(name = "DT_CREATED_AT")
    private LocalDate createdAt;

}
