package com.tencent.business;

import com.tencent.BeforeTest;
import com.tencent.bridge.IBridge;
import com.tencent.business.mockservice.MockRefundService;
import com.tencent.listener.DefaultRefundBusinessResultListener;
import com.tencent.protocol.refund_protocol.RefundReqData;
import com.tencent.business.bridgefortest.BridgeForRefundBusinessTest;

import com.tencent.service.RefundService;
import org.junit.*;

import static org.junit.Assert.*;
/**
 * RefundBusinessDemo Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>ʮһ�� 7, 2014</pre>
 */
public class RefundBusinessTest {

    private static RefundBusiness refundBusiness;

    //普通的Bridge，主要用来测试用本地xml数据模拟API服务器返回的情况
    private static BridgeForRefundBusinessTest bridgeForRefundBusinessTest;

    private static DefaultRefundBusinessResultListener listener;

    @BeforeClass
    public static void beforeClass() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        BeforeTest.initSDK();

        refundBusiness = new RefundBusiness();
        bridgeForRefundBusinessTest = new BridgeForRefundBusinessTest();
        listener = new DefaultRefundBusinessResultListener();
    }

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {
        refundBusiness.setRefundService(new RefundService());
        listener.setResult("");
    }

    private void runDemo(IBridge bridge,RefundBusiness.ResultListener listener) throws Exception {

        RefundReqData refundReqData = new RefundReqData(bridge.getTransactionID(),
                bridge.getOutTradeNo(),
                bridge.getDeviceInfo(),
                bridge.getOutRefundNo(),
                bridge.getTotalFee(),
                bridge.getRefundFee(),
                bridge.getOpUserID(),
                bridge.getRefundFeeType()
        );

        refundBusiness.run(
                refundReqData,
                listener
        );
    }

    /**
     * Method: run(IBridge iBridge)
     */
    @Ignore
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
        refundBusiness.setRefundService(new MockRefundService("/refundserviceresponsedata/apierror.xml"));
        runDemo(bridgeForRefundBusinessTest, listener);
        assertEquals(listener.getResult(), listener.ON_FAIL_BY_RETURN_CODE_ERROR);
    }

    /**
     * 测试Case2
     * 通过本地XML数据模拟API后台返回服务失败的情况
     *
     * @throws Exception
     */
    @Test
    public void testCase2() throws Exception {
        refundBusiness.setRefundService(new MockRefundService("/refundserviceresponsedata/apifail.xml"));
        runDemo(bridgeForRefundBusinessTest, listener);
        assertEquals(listener.getResult(), listener.ON_FAIL_BY_RETURN_CODE_FAIL);
    }

    /**
     * 测试Case3
     * 通过本地XML数据模拟API后台返回数据被篡改
     *
     * @throws Exception
     */
    @Test
    public void testCase3() throws Exception {
        refundBusiness.setRefundService(new MockRefundService("/refundserviceresponsedata/hackdata.xml"));
        runDemo(bridgeForRefundBusinessTest, listener);
        assertEquals(listener.getResult(), listener.ON_FAIL_BY_SIGN_INVALID);
    }

    /**
     * 测试Case4
     * 通过本地XML数据模拟退款失败
     *
     * @throws Exception
     */
    @Test
    public void testCase4() throws Exception {
        refundBusiness.setRefundService(new MockRefundService("/refundserviceresponsedata/refundfail.xml"));
        runDemo(bridgeForRefundBusinessTest, listener);
        assertEquals(listener.getResult(),listener.ON_REFUND_FAIL);
    }

    /**
     * 测试Case5
     * 通过本地XML数据模拟退款成功
     *
     * @throws Exception
     */
    @Test
    public void testCase5() throws Exception {
        refundBusiness.setRefundService(new MockRefundService("/refundserviceresponsedata/refundsuccess.xml"));
        runDemo(bridgeForRefundBusinessTest, listener);
        assertEquals(listener.getResult(), listener.ON_REFUND_SUCCESS);
    }

} 
