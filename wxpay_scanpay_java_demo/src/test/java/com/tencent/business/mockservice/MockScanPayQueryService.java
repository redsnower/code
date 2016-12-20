package com.tencent.business.mockservice;

import com.tencent.common.Util;
import com.tencent.protocol.pay_protocol.ScanPayReqData;
import com.tencent.protocol.pay_query_protocol.ScanPayQueryReqData;
import com.tencent.service.ScanPayQueryService;

/**
 * User: rizenguo
 * Date: 2014/12/7
 * Time: 16:04
 */
public class MockScanPayQueryService extends ScanPayQueryService{

    private String localResponseStringPath = "";

    public MockScanPayQueryService(String localPath) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        super();
        localResponseStringPath = localPath;
    }

    @Override
    public String request(ScanPayQueryReqData reqData) throws Exception{
        return Util.getLocalXMLString(localResponseStringPath);
    }
}
