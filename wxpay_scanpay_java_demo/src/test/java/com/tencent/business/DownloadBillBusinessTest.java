package com.tencent.business;

import com.tencent.BeforeTest;
import com.tencent.bridge.IBridge;
import com.tencent.listener.DefaultDownloadBillBusinessResultListener;
import com.tencent.business.mockservice.MockDownloadBillService;
import com.tencent.protocol.downloadbill_protocol.DownloadBillReqData;
import com.tencent.service.DownloadBillService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import com.tencent.business.bridgefortest.BridgeForBillBusinessTest;

import static org.junit.Assert.*;

/**
 * BillBusinessDemo Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>ʮһ�� 10, 2014</pre>
 */
public class DownloadBillBusinessTest {

    private static DownloadBillBusiness downloadBillBusiness;
    private static BridgeForBillBusinessTest bridgeForBillBusinessTest;

    private static DefaultDownloadBillBusinessResultListener listener;

    @BeforeClass
    public static void beforeClass() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        BeforeTest.initSDK();
        downloadBillBusiness = new DownloadBillBusiness();
        bridgeForBillBusinessTest = new BridgeForBillBusinessTest();
        listener = new DefaultDownloadBillBusinessResultListener();
    }

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {
        downloadBillBusiness.setDownloadBillService(new DownloadBillService());
    }

    private void runDemo(IBridge bridge,DownloadBillBusiness.ResultListener listener) throws Exception {
        DownloadBillReqData downloadBillReqData = new DownloadBillReqData(
            bridge.getDeviceInfo(),
            bridge.getBillDate(),
            bridge.getBillType()
        );
        downloadBillBusiness.run(
            downloadBillReqData,
            listener
        );
    }

    /**
     * Method: run(IBridge iBridge)
     */
    @Test
    public void testRun() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * 测试Case1
     * 通过本地XML数据模拟API后台返回数据没内容的情况
     *
     * @throws Exception
     */
    @Test
    public void testCase1() throws Exception {
        downloadBillBusiness.setDownloadBillService(new MockDownloadBillService("/downloadbillserviceresponsedata/apierror.xml"));
        runDemo(bridgeForBillBusinessTest, listener);
        assertEquals(listener.getResult(),listener.ON_FAIL_BY_RETURN_CODE_ERROR);
    }

    /**
     * 测试Case2
     * 通过本地XML数据模拟API后台返回服务失败的情况
     *
     * @throws Exception
     */
    @Test
    public void testCase2() throws Exception {
        downloadBillBusiness.setDownloadBillService(new MockDownloadBillService("/downloadbillserviceresponsedata/apifail.xml"));
        runDemo(bridgeForBillBusinessTest,listener);
        assertEquals(listener.getResult(),listener.ON_FAIL_BY_RETURN_CODE_FAIL);
    }

    /**
     * 测试Case3
     * API成功返回对账单
     *
     * @throws Exception
     */
    @Test
    public void testCase3() throws Exception {
        downloadBillBusiness.setDownloadBillService(new MockDownloadBillService("/downloadbillserviceresponsedata/bill.txt"));
        runDemo(bridgeForBillBusinessTest,listener);
        assertEquals(listener.getResult(),listener.ON_DOWNLOAD_BILL_SUCCESS);
    }

    /**
     * 测试Case4
     * API系统返回数据为空
     *
     * @throws Exception
     */
    @Test
    public void testCase4() throws Exception {
        downloadBillBusiness.setDownloadBillService(new MockDownloadBillService("/downloadbillserviceresponsedata/nodata.xml"));
        runDemo(bridgeForBillBusinessTest,listener);
        assertEquals(listener.getResult(), listener.ON_DOWNLOAD_BILL_FAIL);
    }

} 
