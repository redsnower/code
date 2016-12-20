package com.tencent.business.mockservice;

import com.tencent.common.Util;
import com.tencent.protocol.reverse_protocol.ReverseReqData;
import com.tencent.service.ReverseService;

/**
 * User: rizenguo
 * Date: 2014/12/7
 * Time: 16:05
 */
public class MockReverseService extends ReverseService{

    private String localResponseStringPath = "";

    public MockReverseService(String localPath) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        super();
        localResponseStringPath = localPath;
    }

    @Override
    public String request(ReverseReqData reqData) throws Exception{
        return Util.getLocalXMLString(localResponseStringPath);
    }
}
