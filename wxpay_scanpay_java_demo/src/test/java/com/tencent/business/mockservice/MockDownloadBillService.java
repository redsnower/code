package com.tencent.business.mockservice;

import com.tencent.common.Util;
import com.tencent.protocol.downloadbill_protocol.DownloadBillReqData;
import com.tencent.service.DownloadBillService;

/**
 * User: rizenguo
 * Date: 2014/12/7
 * Time: 16:25
 */
public class MockDownloadBillService extends DownloadBillService{

    private String localResponseStringPath = "";

    public MockDownloadBillService(String localPath) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        super();
        localResponseStringPath = localPath;
    }

    @Override
    public String request(DownloadBillReqData reqData) throws Exception{
        return Util.getLocalXMLString(localResponseStringPath);
    }

}
