package com.huobi.api.request.trade;

import com.huobi.api.enums.DirectionEnum;
import com.huobi.api.enums.OffsetEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
public class SwapTrackOrderRequest {
    private String contractCode;
    private DirectionEnum direction;//buy:买 sell:卖
    private OffsetEnum offset;//open:开 close:平
    private Integer leverRate;
    private BigDecimal volume;
    private BigDecimal callbackRate;
    private BigDecimal activePrice;
    private String orderPriceType;
}
