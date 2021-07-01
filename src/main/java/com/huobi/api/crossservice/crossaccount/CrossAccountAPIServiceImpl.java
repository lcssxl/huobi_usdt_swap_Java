package com.huobi.api.crossservice.crossaccount;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.huobi.api.crossrequest.trade.SwapCrossUserSettlementRecordsRequest;
import com.huobi.api.crossresponse.account.*;
import com.huobi.api.exception.ApiException;
import com.huobi.api.response.account.SwapSubAccountInfoListResponse;
import com.huobi.api.swapcross.HuobiLinearSwapCrossAPIConstants;
import com.huobi.api.util.HbdmHttpClient;
import com.huobi.constant.HuobiApiConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CrossAccountAPIServiceImpl implements CrossAccountAPIService {

    String api_key = HuobiApiConstants.API_KEY; // huobi申请的apiKey
    String secret_key = HuobiApiConstants.SECRET_KEY; // huobi申请的secretKey
    String url_prex = HuobiApiConstants.URL_PREX;

    Logger logger = LoggerFactory.getLogger(getClass());

    public CrossAccountAPIServiceImpl() {
    }
    public CrossAccountAPIServiceImpl(String api_key, String secret_key) {
        this.api_key = api_key;
        this.secret_key = secret_key;
    }

    @Override
    public SwapCrossAccountInfoResponse getSwapCrossAccountInfo(String marginAccount) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNotEmpty(marginAccount)) {
                params.put("margin_account", marginAccount.toUpperCase().toUpperCase());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapCrossAPIConstants.SWAP_CROSS_ACCOUNT_INFO, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapCrossAccountInfoResponse response = JSON.parseObject(body, SwapCrossAccountInfoResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                //使用迭代器的删除方法删除空资金账户
                List<SwapCrossAccountInfoResponse.DataBean.ContractDetailBean> data = response.getData().get(0).getContractDetail();
                Iterator<SwapCrossAccountInfoResponse.DataBean.ContractDetailBean> iterator = data.iterator();
                while (iterator.hasNext()) {
                    SwapCrossAccountInfoResponse.DataBean.ContractDetailBean dateBean = iterator.next();
                    if (dateBean.getMarginAvailable().compareTo(new BigDecimal(0.00001)) <=0) {
                        iterator.remove();
                    }
                }
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapCrossPositionInfoResponse getSwapCrossPositionInfo(String contractCode) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNotEmpty(contractCode)) {
                params.put("contract_code", contractCode.toUpperCase().toUpperCase());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapCrossAPIConstants.SWAP_CROSS_POSITION_INFO, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapCrossPositionInfoResponse response = JSON.parseObject(body, SwapCrossPositionInfoResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapCrossAccountPositionInfoResponse getSwapCrossAccountPositionInfo(String marginAccount) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("margin_account", marginAccount.toUpperCase().toUpperCase());
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapCrossAPIConstants.SWAP_CROSS_ACCOUNT_POSITION_INFO, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapCrossAccountPositionInfoResponse response = JSON.parseObject(body, SwapCrossAccountPositionInfoResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapCrossSubAccountListResponse getSwapCrossSubAccountList(String marginAccount) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNotEmpty(marginAccount)) {
                params.put("margin_account", marginAccount.toUpperCase().toUpperCase());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapCrossAPIConstants.SWAP_CROSS_SUB_ACCOUNT_LIST, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapCrossSubAccountListResponse response = JSON.parseObject(body, SwapCrossSubAccountListResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapCrossSubAccountInfoResponse getSwapCrossSubAccountInfo(String marginAccount, Long subUid) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNotEmpty(marginAccount)) {
                params.put("margin_account", marginAccount.toUpperCase().toUpperCase());
            }
            params.put("sub_uid", subUid);
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapCrossAPIConstants.SWAP_CROSS_SUB_ACCOUNT_INFO, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapCrossSubAccountInfoResponse response = JSON.parseObject(body, SwapCrossSubAccountInfoResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapCrossSubPositionInfoResponse getSwapCrossSubPositionInfo(String contractCode, Long subUid) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNotEmpty(contractCode)) {
                params.put("contract_code", contractCode.toUpperCase().toUpperCase());
            }
            params.put("sub_uid", subUid);
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapCrossAPIConstants.SWAP_CROSS_SUB_POSITION_INFO, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapCrossSubPositionInfoResponse response = JSON.parseObject(body, SwapCrossSubPositionInfoResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }


    @Override
    public SwapCrossTransferLimitResponse getSwapCrossTransferLimitResponse(String marginAccount) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNoneEmpty(marginAccount)) {
                params.put("margin_account", marginAccount.toUpperCase());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapCrossAPIConstants.SWAP_CROSS_TRANSFER_LIMIT, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapCrossTransferLimitResponse response = JSON.parseObject(body, SwapCrossTransferLimitResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapCrossPositionLimitResponse getSwapCrossPositionLimitResponse(String contractCode) {
        String body;
        try {
            Map<String, Object> params = new HashMap<>();
            if (StringUtils.isNoneEmpty(contractCode)) {
                params.put("contract_code", contractCode.toUpperCase());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapCrossAPIConstants.SWAP_CROSS_POSITION_LIMIT, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapCrossPositionLimitResponse response = JSON.parseObject(body, SwapCrossPositionLimitResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }

        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }


    @Override
    public SwapCrossAvailableLevelRateResponse getSwapCrossAvailableLevelRate(String contractCode) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            if (StringUtils.isNoneEmpty(contractCode)) {
                params.put("contract_code", contractCode.toUpperCase());
            }
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapCrossAPIConstants.SWAP_CROSS_AVAILABLE_LEVEL_RATE, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapCrossAvailableLevelRateResponse response = JSON.parseObject(body, SwapCrossAvailableLevelRateResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapCrossUserSettlementRecordsResponse getSwapCrossUserSettlementRecords(SwapCrossUserSettlementRecordsRequest request) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("margin_account", request.getMarginAccount().toUpperCase());
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
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapCrossAPIConstants.SWAP_CROSS_USER_SETTLEMENT_RECORDS, params);
            logger.debug("body:{}", JSONUtil.toJsonPrettyStr(body));
            SwapCrossUserSettlementRecordsResponse response = JSON.parseObject(body, SwapCrossUserSettlementRecordsResponse.class);
            if ("ok".equalsIgnoreCase(response.getStatus())) {
                return response;
            }
        } catch (Exception e) {
            throw new ApiException(e);
        }
        throw new ApiException(body);
    }

    @Override
    public SwapSubAccountInfoListResponse getSwapCrossSubAccountInfoList(String marginAccount, Integer pageIndex, Integer pagesize) {
        String body;
        Map<String, Object> params = new HashMap<>();
        try {
            params.put("margin_account", marginAccount);
            params.put("page_index", pageIndex);
            params.put("page_size", pagesize);
            body = HbdmHttpClient.getInstance().doPost(api_key, secret_key, url_prex + HuobiLinearSwapCrossAPIConstants.SWAP_CROSS_SUB_ACCOUNT_INFO_LIST, params);
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

}
