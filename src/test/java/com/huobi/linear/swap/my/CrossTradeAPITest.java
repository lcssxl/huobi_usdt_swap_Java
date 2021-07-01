package com.huobi.linear.swap.my;

import com.alibaba.fastjson.JSON;
import com.huobi.api.crossrequest.trade.*;
import com.huobi.api.crossresponse.trade.*;
import com.huobi.api.crossservice.crosstrade.CrossTradeAPIServiceImpl;
import com.huobi.api.enums.DirectionEnum;
import com.huobi.api.enums.OffsetEnum;
import com.huobi.api.request.trade.*;
import com.huobi.api.response.trade.*;
import com.huobi.linear.swap.api.BaseTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.JVM)
public class CrossTradeAPITest implements BaseTest {

    CrossTradeAPIServiceImpl huobiCrossAPIService = new CrossTradeAPIServiceImpl();

    @Test
    public void getSwapCrossSwitchLeverRateResponse() {
        SwapCrossSwitchLeverRateResponse response = huobiCrossAPIService.
                getSwapCrossSwitchLeverRate("ETH-USDT", 5);
        logger.debug("[全仓]切换杠杆：{}", JSON.toJSONString(response));
    }

    @Test
    public void swapCrossOrderOpen() {
        SwapCrossOrderRequest request = SwapCrossOrderRequest.builder()
                .contractCode("ETH-USDT")
                .direction(DirectionEnum.BUY)
                .offset(OffsetEnum.OPEN)
                .volume(1l)
                .leverRate(5)

                // "opponent":对手价，
                // "optimal_5"：最优5档，"optimal_10"：最优10档，"optimal_20"：最优20档，
                // "opponent_ioc": 对手价-IOC下单，"optimal_5_ioc": 最优5档-IOC下单，"optimal_10_ioc": 最优10档-IOC下单，"optimal_20_ioc"：最优20档-IOC下单，
                // "opponent_fok"： 对手价-FOK下单，"optimal_5_fok"：最优5档-FOK下单，"optimal_10_fok"：最优10档-FOK下单，"optimal_20_fok"：最优20档-FOK下单
                .orderPriceType("optimal_5_ioc")

                // "limit":限价，"post_only":只做maker单，ioc:IOC订单，fok：FOK订单  这四种报价类型需要传价格，其他都不需要
                /*.orderPriceType("limit")
                .price(BigDecimal.valueOf(455))*/
                .build();
        SwapCrossOrderResponse response =
                huobiCrossAPIService.swapCrossOrderRequest(request);
        logger.debug("[全仓]合约下单 - 开仓：{}", JSON.toJSONString(response));
    }

    @Test
    public void swapCrossOrderClose() {
        SwapCrossOrderRequest request = SwapCrossOrderRequest.builder()
                .contractCode("ETH-USDT")
                .direction(DirectionEnum.SELL)
                .offset(OffsetEnum.CLOSE)
                .volume(1l)
                .leverRate(5)

                // "opponent":对手价，
                // "optimal_5"：最优5档，"optimal_10"：最优10档，"optimal_20"：最优20档，
                // "opponent_ioc": 对手价-IOC下单，"optimal_5_ioc": 最优5档-IOC下单，"optimal_10_ioc": 最优10档-IOC下单，"optimal_20_ioc"：最优20档-IOC下单，
                // "opponent_fok"： 对手价-FOK下单，"optimal_5_fok"：最优5档-FOK下单，"optimal_10_fok"：最优10档-FOK下单，"optimal_20_fok"：最优20档-FOK下单
                .orderPriceType("optimal_5_ioc")

                // "limit":限价，"post_only":只做maker单，ioc:IOC订单，fok：FOK订单  这四种报价类型需要传价格，其他都不需要
                /*.orderPriceType("limit")
                .price(BigDecimal.valueOf(455))*/
                .build();
        SwapCrossOrderResponse response =
                huobiCrossAPIService.swapCrossOrderRequest(request);
        logger.debug("[全仓]合约下单 - 平仓：{}", JSON.toJSONString(response));
    }




    @Test
    public void swapCrossBatchorderRequest() {
        List<SwapCrossOrderRequest> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            SwapCrossOrderRequest request = SwapCrossOrderRequest.builder()
                    .contractCode("ETH-USDT")
                    .direction(DirectionEnum.SELL)
                    .offset(OffsetEnum.OPEN)
                    .volume(1l)
                    .leverRate(3)
                    .orderPriceType("limit")
                    .price(BigDecimal.valueOf(2200))
                    .build();
            list.add(request);
            request = SwapCrossOrderRequest.builder()
                    .contractCode("BTC-USDT")
                    .direction(DirectionEnum.SELL)
                    .offset(OffsetEnum.OPEN)
                    .volume(1l)
                    .leverRate(5)
                    .orderPriceType("limit")
                    .price(BigDecimal.valueOf(35000))
                    .build();
            list.add(request);
        }
        SwapCrossBatchorderRequest request = SwapCrossBatchorderRequest.builder()
                .list(list)
                .build();
        SwapCrossBatchorderResponse response =
                huobiCrossAPIService.swapCrossBatchorderRequest(request);
        logger.debug("[全仓]合约批量下单：{}", JSON.toJSONString(response));
    }


    @Test
    public void swapCrossCancelRequest() {
        SwapCrossCancelRequest request = SwapCrossCancelRequest.builder()
                .contractCode("ETH-USDT")

                // order_id和client_order_id都可以用来撤单，至少要填写一个
                .orderId("786547455839219712")
                //.clientOrderId("")
                .build();
        SwapCrossCancelResponse response =
                huobiCrossAPIService.swapCrossCancelRequest(request);
        logger.debug("[全仓]撤销订单：{}", JSON.toJSONString(response));
    }


    @Test
    public void swapCrossCancelallRequest() {
        SwapCrossCancelallRequest request = SwapCrossCancelallRequest.builder()
                .contractCode("ETH-USDT")
                .build();
        SwapCrossCancelallResponse response =
                huobiCrossAPIService.swapCrossCancelallRequest(request);
        logger.debug("[全仓]全部撤单：{}", JSON.toJSONString(response));
    }


    @Test
    public void swapCrossOrderInfoRequest() {
        SwapCrossOrderInfoRequest request = SwapCrossOrderInfoRequest.builder()
                .contractCode("ETH-USDT")
                .orderId("786547455839219712")
                //.clientOrderId("")
                .build();
        SwapCrossOrderInfoResponse response =
                huobiCrossAPIService.swapCrossOrderInfoRequest(request);
        logger.debug("[全仓]获取合约订单信息：{}", JSON.toJSONString(response));
    }


    @Test
    public void swapOrderDetailRequest() {
        SwapCrossOrderDetailRequest request = SwapCrossOrderDetailRequest.builder()
                .contractCode("ETH-USDT")
                .orderId(786550830160982016l)
                .createdAt(System.currentTimeMillis())
                //.orderType(1)
                //.pageIndex(1)
                //.pageSize(20)
                .build();
        SwapCrossOrderDetailResponse response =
                huobiCrossAPIService.swapCrossOrderDetailRequest(request);
        logger.debug("[全仓]获取订单明细信息：{}", JSON.toJSONString(response));
    }


    @Test
    public void swapCrossOpenordersRequest() {
        SwapCrossOpenordersRequest request = SwapCrossOpenordersRequest.builder()
                .contractCode("ETH-USDT")
                .pageIndex(1)
                .pageSize(20)
                .build();
        SwapCrossOpenordersResponse response =
                huobiCrossAPIService.swapCrossOpenordersRequest(request);
        logger.debug("[全仓]获取合约当前未成交委托：{}", JSON.toJSONString(response));
    }


    @Test
    public void swapCrossMatchresultsRequest() {
        SwapCrossMatchresultsRequest request = SwapCrossMatchresultsRequest.builder()
                .contractCode("ETH-USDT")
                .tradeType(0)
                .createDate(90)
                .pageIndex(1)
                .pageSize(50)
                .build();
        SwapCrossMatchresultsResponse response =
                huobiCrossAPIService.swapCrossMatchresultsRequest(request);
        logger.debug("[全仓]获取历史成交记录：{}", JSON.toJSONString(response));
    }


    @Test
    public void swapCrossLightningClosePositionRequest() {
        SwapCrossLightningClosePositionRequest request = SwapCrossLightningClosePositionRequest.builder()
                .contractCode("ETH-USDT")
                .direction("buy")
                .volume(1)
                .build();
        SwapCrossLightningClosePositionResponse response =
                huobiCrossAPIService.swapCrossLightningClosePositionRequest(request);
        logger.debug("[全仓]闪电平仓下单：{}", JSON.toJSONString(response));
    }






    /**
     * 合约策略订单
     */
    @Test
    public void swapCrossTriggerOrderRequest() {
        SwapCrossTriggerOrderRequest request = SwapCrossTriggerOrderRequest.builder()
                .contractCode("ETH-USDT")
                .direction(DirectionEnum.BUY)
                .offset(OffsetEnum.OPEN)
                .volume(BigDecimal.valueOf(1))
                .leverRate(30)
                .triggerType("ge")
                .triggerPrice(BigDecimal.valueOf(377))
                .orderPriceType("limit")
                .orderPrice(BigDecimal.valueOf(377))
                .build();
        SwapCrossTriggerOrderResponse response = huobiCrossAPIService.swapCrossTriggerOrderResponse(request);
        logger.debug("[全仓]计划委托下单：{}", JSON.toJSONString(response));
    }
    @Test
    public void swapCrossTriggerCancelallRequest() {
        SwapCrossTriggerCancelallRequest request = SwapCrossTriggerCancelallRequest.builder()
                .contractCode("ETH-USDT")
                .build();
        SwapCrossTriggerCancelallResponse response = huobiCrossAPIService.swapCrossTriggerCancelallResponse(request);
        logger.debug("[全仓]计划委托全部撤单：{}", JSON.toJSONString(response));
    }



    @Test
    public void swapTpslOrderRequest() {
        SwapTpslOrderRequest request = SwapTpslOrderRequest.builder()
                .contractCode("XRP-USDT")
                .direction("buy")
                .volume(BigDecimal.valueOf(1))

                // 止盈触发
                .tpTriggerPrice(BigDecimal.valueOf(0.2))
                .tpOrderPriceType("limit")
                .tpOrderPrice(BigDecimal.valueOf(0.2))

                // 止损触发
                .slTriggerPrice(BigDecimal.valueOf(0.5))
                .slOrderPriceType("limit")
                .slOrderPrice(BigDecimal.valueOf(0.5))
                .build();
        SwapTpslOrderResponse response = huobiCrossAPIService.swapCrossTpslOrderResponse(request);
        logger.debug("[全仓]对仓位设置止盈止损订单：{}", JSON.toJSONString(response));
    }
    @Test
    public void swapTpslCancelallRequest() {
        SwapTpslCancelallRequest request = SwapTpslCancelallRequest.builder()
                .contractCode("XRP-USDT")
                .build();
        SwapTpslCancelallResponse response = huobiCrossAPIService.swapCrossTpslCancelallResponse(request);
        logger.debug("[全仓]止盈止损订单全部撤单：{}", JSON.toJSONString(response));
    }



    @Test
    public void swapCrossTrackOrders(){
        SwapTrackOrderRequest request = SwapTrackOrderRequest.builder()
                .contractCode("ETH-USDT")
                .direction(DirectionEnum.BUY)
                .offset(OffsetEnum.OPEN)
                .leverRate(5)
                .volume(BigDecimal.valueOf(1))

                .activePrice(BigDecimal.valueOf(2100))
                .callbackRate(BigDecimal.valueOf(0.02))
                .orderPriceType("optimal_5")
                .build();
        SwapTrackOrderResponse response=huobiCrossAPIService.swapCrossTrackOrderResponse(request);
        logger.debug("[全仓]跟踪委托订单下单：{}", JSON.toJSONString(response));
    }
    @Test
    public void swapCrossTrackCancelall(){
        SwapTrackCancelallRequest request= SwapTrackCancelallRequest.builder()
                .contractCode("ETH-USDT")
                .direction("")
                .offset("")
                .build();
        SwapTrackCancelallResponse response=huobiCrossAPIService.swapCrossTrackCancelallResponse(request);
        logger.debug("[全仓]跟踪委托订单全部撤单：{}", JSON.toJSONString(response));
    }
}
