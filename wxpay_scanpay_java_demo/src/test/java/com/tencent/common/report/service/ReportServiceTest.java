package com.tencent.common.report.service;

import com.tencent.BeforeTest;
import com.tencent.common.Configure;
import com.tencent.common.Util;
import com.tencent.common.report.protocol.ReportReqData;
import com.tencent.common.report.service.ReportService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * ReportService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>ʮһ�� 12, 2014</pre>
 */
public class ReportServiceTest {

    @Before
    public void before() throws Exception {
        BeforeTest.initSDK();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: request(String subMchId, String deviceInfo, String interfaceUrl, int executeTime, String returnCode, String returnMsg, String resultCode, String errCode, String errCodeDes, String outTradeNo, String userIp, String time)
     */
    @Test
    public void testRequest() throws Exception {
//TODO: Test goes here...


        ReportReqData reportReqData = new ReportReqData(
                "设备信息",
                Configure.PAY_API,
                1200,
                "SUCCESS",
                "OK",
                "FAIL",
                "AUTH_CODE_INVALID",
                "sdk&demo junit test ",
                "1000000007",
                "8.8.8.8"
        );

        String responseString = ReportService.request(reportReqData);
        Util.log(responseString);

        Thread.sleep(3000);

         responseString = ReportService.request(reportReqData);
        Util.log(responseString);

        Thread.sleep(3000);

        responseString = ReportService.request(reportReqData);
        Util.log(responseString);
    }


} 
