package com.tencent.business.mockservice;

import com.tencent.common.Util;
import com.tencent.protocol.pay_protocol.ScanPayReqData;
import com.tencent.service.ScanPayService;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

/**
 * User: rizenguo
 * Date: 2014/12/7
 * Time: 15:29
 */
public class MockScanPayService extends ScanPayService{

    private String localResponseStringPath = "";

    public MockScanPayService(String localPath) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        super();
        localResponseStringPath = localPath;
    }

    @Override
    public String request(ScanPayReqData reqData) throws Exception{
        return Util.getLocalXMLString(localResponseStringPath);
    }
}
