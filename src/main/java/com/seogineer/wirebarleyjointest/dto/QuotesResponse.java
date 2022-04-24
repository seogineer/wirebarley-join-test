package com.seogineer.wirebarleyjointest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuotesResponse {
    @JsonProperty("USDKRW")
    private Double USDKRW;

    @JsonProperty("USDJPY")
    private Double USDJPY;

    @JsonProperty("USDPHP")
    private Double USDPHP;
}
