package com.seogineer.wirebarleyjointest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuotesResponse {
    @JsonProperty("USDKRW")
    private String USDKRW;

    @JsonProperty("USDJPY")
    private String USDJPY;

    @JsonProperty("USDPHP")
    private String USDPHP;
}
