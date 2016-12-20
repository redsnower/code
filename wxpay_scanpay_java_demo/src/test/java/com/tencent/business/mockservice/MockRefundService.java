package com.tencent.business.mockservice;

import com.tencent.common.Util;
import com.tencent.protocol.refund_protocol.RefundReqData;
import com.tencent.service.RefundService;

/**
 * User: rizenguo
 * Date: 2014/12/7
 * Time: 16:25
 */
public class MockRefundService extends RefundService {

    private String localResponseStringPath = "";

    public MockRefundService(String localPath) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        super();
        localResponseStringPath = localPath;
    }

    @Override
    public String request(RefundReqData reqData) throws Exception{
        return Util.getLocalXMLString(localResponseStringPath);
    }
}


