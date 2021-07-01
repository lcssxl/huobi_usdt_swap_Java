package com.huobi.linear.swap.my;

import com.alibaba.fastjson.JSON;
import com.huobi.api.request.account.*;
import com.huobi.api.response.account.*;
import com.huobi.api.service.account.AccountAPIServiceImpl;
import com.huobi.linear.swap.api.BaseTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;


@FixMethodOrder(MethodSorters.JVM)
public class AccountAPITest implements BaseTest {


    AccountAPIServiceImpl huobiAPIService = new AccountAPIServiceImpl();

    @Test
    public void getSwapBalanceValuation(){
        // valuation_asset	资产估值币种，即按该币种为单位进行估值，不填默认"BTC";还可以如："CNY"
        SwapBalanceValuationResponse response=huobiAPIService.
                getSwapBalanceValuation("USDT");
        logger.debug("[通用]获取账户总资产估值：{}", JSON.toJSONString(response));
    }


    @Test
    public void getSwapFeeResponse() {
        SwapFeeResponse response = huobiAPIService.getSwapFeeResponse("ETH-USDT");
        logger.debug("[通用]查询用户当前的手续费费率：{}", JSON.toJSONString(response));
        response = huobiAPIService.getSwapFeeResponse("");
        logger.debug("[通用]查询用户当前的手续费费率：{}", JSON.toJSONString(response));
    }


    @Test
    public void getSwapFinancialRecordExact() {
        /**
         * type	false	string
         * 不填查询全部类型,【查询多类型中间用，隔开】
         * 3:平多; 4:平空;
         *
         * 5:开仓手续费-吃单; 6:开仓手续费-挂单; 7:平仓手续费-吃单; 8:平仓手续费-挂单;
         * 9:交割平多; 10:交割平空; 11:交割手续费; 12:强制平多; 13:强制平空;
         *
         * 14:从币币转入; 15:转出至币币; 16:结算未实现盈亏-多仓; 17:结算未实现盈亏-空仓; 19:穿仓分摊;
         * 26:系统; 28:活动奖励; 29:返利; 30:资金费-收入; 31:资金费-支出; 34:转出到子账号合约账户;
         * 35:从子账号合约账户转入; 36:转出到母账号合约账户; 37:从母账号合约账户转入;38:从其他保证金账户转入 ;39:转出到其他保证金账户 ;
         */

        SwapFinancialRecordExactRequest request = SwapFinancialRecordExactRequest.builder()
                .marginAccount("USDT")
                .contractCode("ETH-USDT")
                .type("3,4")
                //.startTime(1l)
                //.endTime(1l)
                //.fromId(1l)
                //.size(20)
                //.direct("")
                .build();
        SwapFinancialRecordExactResponse response = huobiAPIService.getSwapFinancialRecordExact(request);
        logger.debug("[通用]组合查询用户财务记录：{}", JSON.toJSONString(response));
    }



    @Test
    public void getSwapApiTradingStatusResponse() {
        SwapApiTradingStatusResponse response = huobiAPIService.getSwapApiTradingStatusResponse();
        logger.debug("[通用]获取用户API指标禁用信息：{}", JSON.toJSONString(response));
    }
}
