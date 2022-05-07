package com.seogineer.wirebarleyjointest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class SubscribeResponse {
    private Boolean success;
    private String terms;
    private String privacy;
    private Date timestamp;
    private String source;
    private QuotesResponse quotes;
}
