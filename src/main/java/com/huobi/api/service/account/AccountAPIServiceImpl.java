package com.huobi.api.service.account;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.huobi.api.request.account.*;
import com.huobi.api.swaps.HuobiLinearSwapAPIConstants;
import com.huobi.api.exception.ApiException;
import com.huobi.api.response.account.*;
import com.huobi.api.util.HbdmHttpClient;
import com.huobi.constant.HuobiApiConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AccountAPIServiceImpl implements AccountAPIService {

    String api_key = HuobiApiConstants.API_KEY; // huobi申请的apiKey
    String secret_key = HuobiApiConstants.SECRET_KEY; // huobi申请的secretKey
    String url_prex = HuobiApiConstants.URL_PREX;

    Logger logger = LoggerFactory.getLogger(getClass());

    public AccountAPIServiceImpl() {
    }
    public AccountAPIServiceImpl(String api_key, String secret_key) {
        this.api_key = api_key;
        this.secret_key = secret_key;
    }


    @Override
    public SwapAccountInfoResponse getSwapAccountInfo(String contractCode) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNotEmpty(contractCode)) {
                params.put("contract_code", contractCode.toUpperCase().toUpperCase());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.SWAP_ACCOUNT_INFO, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapAccountInfoResponse response = JSON.parseObject(body, SwapAccountInfoResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapPositionInfoResponse getSwapPositionInfo(String contractCode) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNotEmpty(contractCode)) {
                params.put("contract_code", contractCode.toUpperCase().toUpperCase());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.SWAP_POSITION_INFO, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapPositionInfoResponse response = JSON.parseObject(body, SwapPositionInfoResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapAccountPositionInfoResponse getSwapAccountPositionInfo(String contractCode) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("contract_code", contractCode.toUpperCase().toUpperCase());
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.SWAP_ACCOUNT_POSITION_INFO, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapAccountPositionInfoResponse response = JSON.parseObject(body, SwapAccountPositionInfoResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapSubAccountListResponse getSwapSubAccountList(String contractCode) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNotEmpty(contractCode)) {
                params.put("contract_code", contractCode.toUpperCase().toUpperCase());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.SWAP_SUB_ACCOUNT_LIST, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapSubAccountListResponse response = JSON.parseObject(body, SwapSubAccountListResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapSubAccountInfoResponse getSwapSubAccountInfo(String contractCode, Long subUid) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNotEmpty(contractCode)) {
                params.put("contract_code", contractCode.toUpperCase().toUpperCase());
            }
            params.put("sub_uid", subUid);
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.SWAP_SUB_ACCOUNT_INFO, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapSubAccountInfoResponse response = JSON.parseObject(body, SwapSubAccountInfoResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapSubPositionInfoResponse getSwapSubPositionInfo(String contractCode, Long subUid) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNotEmpty(contractCode)) {
                params.put("contract_code", contractCode.toUpperCase().toUpperCase());
            }
            params.put("sub_uid", subUid);
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.SWAP_SUB_POSITION_INFO, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapSubPositionInfoResponse response = JSON.parseObject(body, SwapSubPositionInfoResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapFinancialRecordResponse getSwapFinancialRecord(SwapFinancialRecordRequest request) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("margin_account", request.getMarginAccount().toUpperCase());
            if (StringUtils.isNoneEmpty(request.getType())) {
                params.put("type", request.getType());
            }
            if (request.getCreateDate() != null) {
                params.put("create_date", request.getCreateDate());
            }
            if (request.getPageIndex() != null) {
                params.put("page_index", request.getPageIndex());
            }
            if (request.getPageSize() != null) {
                params.put("page_size", request.getPageSize());
            }
            if (StringUtils.isNotEmpty(request.getContractCode())) {
                params.put("contract_code", request.getContractCode().toUpperCase());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.SWAP_FINANCIAL_RECORD, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapFinancialRecordResponse response = JSON.parseObject(body, SwapFinancialRecordResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapOrderLimitResponse getSwapOrderLimitResponse(String contractCode, String orderPriceType) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNoneEmpty(contractCode)) {
                params.put("contract_code", contractCode.toUpperCase());
            }
            params.put("order_price_type", orderPriceType);
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.SWAP_ORDER_LIMIT, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapOrderLimitResponse response = JSON.parseObject(body, SwapOrderLimitResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapFeeResponse getSwapFeeResponse(String contractCode) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNoneEmpty(contractCode)) {
                params.put("contract_code", contractCode.toUpperCase());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.SWAP_FEE, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapFeeResponse response = JSON.parseObject(body, SwapFeeResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapTransferLimitResponse getSwapTransferLimitResponse(String contractCode) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNoneEmpty(contractCode)) {
                params.put("contract_code", contractCode.toUpperCase());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.SWAP_TRANSFER_LIMIT, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapTransferLimitResponse response = JSON.parseObject(body, SwapTransferLimitResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapPositionLimitResponse getSwapPositionLimitResponse(String contractCode) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNoneEmpty(contractCode)) {
                params.put("contract_code", contractCode.toUpperCase());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.SWAP_POSITION_LIMIT, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapPositionLimitResponse response = JSON.parseObject(body, SwapPositionLimitResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapMasterSubTransferResponse getSwapMasterSubTransferResponse(SwapMasterSubTransferRequest request) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("sub_uid", request.getSubUid());
            params.put("asset", request.getAsset().toUpperCase());
            params.put("from_margin_account", request.getFromMarginAccount().toUpperCase());
            params.put("to_margin_account", request.getToMarginAccount().toUpperCase());
            params.put("amount", request.getAmount());
            params.put("type", request.getType());
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.SWAP_MASTER_SUB_TRANSFER, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapMasterSubTransferResponse response = JSON.parseObject(body, SwapMasterSubTransferResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapMasterSubTransferRecordResponse getSwapMasterSubTransferRecordResponse(SwapMasterSubTransferRecordRequest request) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("margin_account", request.getMarginAccount().toUpperCase());
            if (request.getTransferType() != null) {
                params.put("transfer_type", request.getTransferType());
            }
            params.put("create_date", request.getCreateDate());
            if (request.getPageIndex() != null) {
                params.put("page_index", request.getPageIndex());
            }
            if (request.getPageSize() != null) {
                params.put("page_size", request.getPageSize());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.SWAP_MASTER_SUB_TRANSFER_RECORD, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapMasterSubTransferRecordResponse response = JSON.parseObject(body, SwapMasterSubTransferRecordResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapTransferInnerResponse getSwapTransferInner(String asset, String fromMarginAccount, String toMarginAccount, BigDecimal amount) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("asset", asset);
            params.put("from_margin_account", fromMarginAccount);
            params.put("to_margin_account", toMarginAccount);
            params.put("amount", amount);
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.SWAP_TRANSFER_INNER, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapTransferInnerResponse response = JSON.parseObject(body, SwapTransferInnerResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapApiTradingStatusResponse getSwapApiTradingStatusResponse() {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            body = HbdmHttpClient.getInstance().doGetKey(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.SWAP_API_TRADING_STATUS, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapApiTradingStatusResponse response = JSON.parseObject(body, SwapApiTradingStatusResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapAvailableLevelRateResponse getSwapAvailableLevelRate(String contractCode) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            if (StringUtils.isNoneEmpty(contractCode)) {
                params.put("contract_code", contractCode.toUpperCase());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.SWAP_AVAILABLE_LEVEL_RATE, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapAvailableLevelRateResponse response = JSON.parseObject(body, SwapAvailableLevelRateResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapUserSettlementRecordsResponse getSwapUserSettlementRecords(SwapUserSettlementRecordsRequest request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("contract_code", request.getContractCode().toUpperCase());
            if (request.getStartTime() != null) {
                params.put("start_time", request.getStartTime());
            }
            if (request.getEndTime() != null) {
                params.put("end_time", request.getEndTime());
            }
            if (request.getPageIndex() != null) {
                params.put("page_index", request.getPageIndex());
            }
            if (request.getPageSize() != null) {
                params.put("page_size", request.getPageSize());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.SWAP_USER_SETTLEMENT_RECORDS, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapUserSettlementRecordsResponse response = JSON.parseObject(body, SwapUserSettlementRecordsResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapFinancialRecordExactResponse getSwapFinancialRecordExact(SwapFinancialRecordExactRequest request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("margin_account", request.getMarginAccount());
            if (request.getContractCode() != null) {
                params.put("contract_code", request.getContractCode());
            }
            if (request.getType() != null) {
                params.put("type", request.getType());
            }
            if (request.getStartTime() != null) {
                params.put("start_time", request.getStartTime());
            }
            if (request.getEndTime() != null) {
                params.put("end_time", request.getEndTime());
            }
            if (request.getSize() != null) {
                params.put("size", request.getSize());
            }
            if (request.getDirect() != null) {
                params.put("direct", request.getDirect());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.SWAP_FINANCIAL_RECORD_EXACT, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapFinancialRecordExactResponse response = JSON.parseObject(body, SwapFinancialRecordExactResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapSubAuthResponse getSwapSubAuth(String subUid, Integer subAuth) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("sub_uid", subUid);
            params.put("sub_auth", subAuth);
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.SWAP_SUB_AUTH, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapSubAuthResponse response = JSON.parseObject(body, SwapSubAuthResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapSubAccountInfoListResponse getSwapSubAccountInfoList(String contractCode, Integer pageIndex, Integer pageSize) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("contract_code", contractCode);
            if (pageIndex != null) {
                params.put("page_index", pageIndex);
            }
            if (pageSize != null) {
                params.put("page_size", pageSize);
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.SWAP_SUB_ACCOUNT_INFO_LIST, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapSubAccountInfoListResponse response = JSON.parseObject(body, SwapSubAccountInfoListResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapBalanceValuationResponse getSwapBalanceValuation(String ValuationAsset) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            if (StringUtils.isNotEmpty(ValuationAsset)){
                params.put("valuation_asset",ValuationAsset.toUpperCase());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapAPIConstants.SWAP_BALANCE_VALUATION, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapBalanceValuationResponse response = JSON.parseObject(body, SwapBalanceValuationResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }
}
