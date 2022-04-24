package com.seogineer.wirebarleyjointest.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Currency {
    @Id @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String currencyCode;

    @Column(nullable = false)
    private String countryName;
}
