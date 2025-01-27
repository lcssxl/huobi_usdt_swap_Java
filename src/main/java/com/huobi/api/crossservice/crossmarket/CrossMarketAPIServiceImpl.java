package com.huobi.api.crossservice.crossmarket;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.huobi.api.crossresponse.market.SwapCrossAdjustfactorResponse;
import com.huobi.api.crossresponse.market.SwapCrossTradeStateResponse;
import com.huobi.api.crossresponse.market.SwapCrossTransferStateResponse;
import com.huobi.api.exception.ApiException;
import com.huobi.api.response.market.SwapLadderMarginResponse;
import com.huobi.api.swapcross.HuobiLinearSwapCrossAPIConstants;
import com.huobi.api.util.HbdmHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class CrossMarketAPIServiceImpl implements CrossMarketAPIService {

    String url_prex = "https://api.hbdm.com";
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public SwapCrossAdjustfactorResponse getSwapCrossAdjustfactor(String contractCode) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNoneEmpty(contractCode)) {
                params.put("contract_code", contractCode.toUpperCase());
            }
            body = HbdmHttpClient.getInstance().doGet(url_prex + HuobiLinearSwapCrossAPIConstants.SWAP_CROSS_ADJUSTFACTOR, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapCrossAdjustfactorResponse response = JSON.parseObject(body, SwapCrossAdjustfactorResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapCrossTransferStateResponse getSwapCrossTransferState(String marginAccount) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNoneEmpty(marginAccount)) {
                params.put("margin_account", marginAccount.toUpperCase());
            }
            body = HbdmHttpClient.getInstance().doGet(url_prex + HuobiLinearSwapCrossAPIConstants.SWAP_CROSS_TRANSFER_STATE, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapCrossTransferStateResponse response = JSON.parseObject(body, SwapCrossTransferStateResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapCrossTradeStateResponse getSwapCrossTradeState(String contractCode) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNoneEmpty(contractCode)) {
                params.put("contract_code", contractCode.toUpperCase());
            }
            body = HbdmHttpClient.getInstance().doGet(url_prex + HuobiLinearSwapCrossAPIConstants.SWAP_CROSS_TRADE_STATE, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapCrossTradeStateResponse response = JSON.parseObject(body, SwapCrossTradeStateResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapLadderMarginResponse getSwapCrossLadderMargin(String contractCode) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNoneEmpty(contractCode)) {
                params.put("contract_code", contractCode.toUpperCase());
            }
            body = HbdmHttpClient.getInstance().doGet(url_prex + HuobiLinearSwapCrossAPIConstants.SWAP_CROSS_LADDER_MARGIN, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapLadderMarginResponse response = JSON.parseObject(body, SwapLadderMarginResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }
}