package com.huobi.linear.swap.my;

import com.alibaba.fastjson.JSON;
import com.huobi.api.request.account.LinearSwapBasisRequest;
import com.huobi.api.request.account.SwapLiquidationOrdersRequest;
import com.huobi.api.request.account.SwapMarketHistoryKlineRequest;
import com.huobi.api.response.market.*;
import com.huobi.api.service.market.MarketAPIServiceImpl;
import com.huobi.linear.swap.api.BaseTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.JVM)
public class MarketAPITest implements BaseTest {

    MarketAPIServiceImpl huobiAPIService = new MarketAPIServiceImpl();


    @Test
    public void getSwapContractInfo() {
        SwapContractInfoResponse result = huobiAPIService.
                getSwapContractInfo("ETH-USDT", "all");
        logger.debug("获取合约信息：{}", JSON.toJSONString(result));
    }


    @Test
    public void getSwapMarketDepth() {
        SwapMarketDepthResponse result =
                huobiAPIService.getSwapMarketDepth("ETH-USDT", "step15");
        logger.debug("获取行情深度数据：{}", JSON.toJSONString(result));
    }

    @Test
    public void getLinearSwapMarkPriceKline() {
        LinearSwapMarkPriceKlineResponse response = huobiAPIService.
                getLinearSwapMarkPriceKline("ETH-USDT", "5min", 50);
        logger.debug("获取标记价格的K线数据:{}", JSON.toJSONString(response));
    }
    @Test
    public void getSwapMarketHistoryKline() {
        SwapMarketHistoryKlineRequest result = SwapMarketHistoryKlineRequest.builder()
                .contractCode("ETH-USDT")
                .period("5min")
                .size(100)
                //.from()
                //.to()
                .build();
        SwapMarketHistoryKlineResponse response = huobiAPIService.getSwapMarketHistoryKline(result);
        logger.debug("获取K线数据：{}", JSON.toJSONString(response));
    }


    @Test
    public void getSwapApiStateResponse() {
        SwapApiStateResponse response = huobiAPIService.getSwapApiState("");
        logger.debug("查询系统状态:{}", JSON.toJSONString(response));
    }






    @Test
    public void getLinearSwapBasisResponse() {
        LinearSwapBasisRequest request = LinearSwapBasisRequest.builder()
                .contractCode("ETH-USDT")
                .period("5min")
                .basisPriceType("open")
                .size(10)
                .build();
        LinearSwapBasisResponse response = huobiAPIService.getLinearSwapBasis(request);
        logger.debug("获取基差数据:{}", JSON.toJSONString(response));

    }


    @Test
    public void getMarketBbo(){
        MarketBboResponse response= huobiAPIService.getMarketBbo("ETH-USDT");
        logger.debug("获取市场最优挂单:{}",JSON.toJSONString(response));
    }
}
