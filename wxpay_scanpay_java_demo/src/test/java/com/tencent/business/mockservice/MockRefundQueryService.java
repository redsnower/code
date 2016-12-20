package com.tencent.business.mockservice;

import com.tencent.common.Util;
import com.tencent.protocol.refund_query_protocol.RefundQueryReqData;
import com.tencent.service.RefundQueryService;

/**
 * User: rizenguo
 * Date: 2014/12/7
 * Time: 16:25
 */
public class MockRefundQueryService extends RefundQueryService{

    private String localResponseStringPath = "";

    public MockRefundQueryService(String localPath) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        super();
        localResponseStringPath = localPath;
    }

    @Override
    public String request(RefundQueryReqData reqData) throws Exception{
        return Util.getLocalXMLString(localResponseStringPath);
    }

}
