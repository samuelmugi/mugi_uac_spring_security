package com.mugi.uac.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table
public class Role {

    @Id
    @Column(name = "id")
    private BigDecimal id;

    @Column(name = "name")
    private String name;

}
