package com.tencent.business;

import com.tencent.BeforeTest;
import com.tencent.bridge.IBridge;
import com.tencent.business.mockservice.MockRefundQueryService;
import com.tencent.listener.DefaultRefundQueryBusinessResultListener;
import com.tencent.protocol.refund_query_protocol.RefundQueryReqData;
import com.tencent.service.RefundQueryService;
import org.junit.*;
import com.tencent.business.bridgefortest.BridgeForRefundQueryBusinessTest;

import static org.junit.Assert.assertEquals;

/**
 * RefundQueryBusinessDemo Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>ʮһ�� 7, 2014</pre>
 */
public class RefundQueryBusinessTest {

    private static RefundQueryBusiness refundQueryBusiness;
    //普通的Bridge，主要用来测试用本地xml数据模拟API服务器返回的情况
    private static BridgeForRefundQueryBusinessTest bridgeForRefundQueryBusinessTest;

    private static DefaultRefundQueryBusinessResultListener listener;

    @BeforeClass
    public static void beforeClass() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        BeforeTest.initSDK();
        refundQueryBusiness = new RefundQueryBusiness();
        bridgeForRefundQueryBusinessTest = new BridgeForRefundQueryBusinessTest();
        listener = new DefaultRefundQueryBusinessResultListener();
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
        refundQueryBusiness.setRefundQueryService(new RefundQueryService());
        refundQueryBusiness.setOrderListResult("");
        refundQueryBusiness.setResult("");
    }

    private void runDemo(IBridge bridge,RefundQueryBusiness.ResultListener listener) throws Exception {

        RefundQueryReqData refundQueryReqData = new RefundQueryReqData(bridge.getTransactionID(),
                bridge.getOutTradeNo(),
                bridge.getDeviceInfo(),
                bridge.getOutRefundNo(),
                bridge.getRefundID()
        );

        refundQueryBusiness.run(
                refundQueryReqData,
                listener
        );
    }

    /**
     * Method: run()
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
        refundQueryBusiness.setRefundQueryService(new MockRefundQueryService("/refundqueryserviceresponsedata/apierror.xml"));
        runDemo(bridgeForRefundQueryBusinessTest, listener);
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
        refundQueryBusiness.setRefundQueryService(new MockRefundQueryService("/refundqueryserviceresponsedata/apifail.xml"));
        runDemo(bridgeForRefundQueryBusinessTest, listener);
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
        refundQueryBusiness.setRefundQueryService(new MockRefundQueryService("/refundqueryserviceresponsedata/hackdata.xml"));
        runDemo(bridgeForRefundQueryBusinessTest, listener);
        assertEquals(listener.getResult(), listener.ON_FAIL_BY_SIGN_INVALID);
    }

    /**
     * 测试Case4
     * 通过本地XML数据模拟退款查询失败
     *
     * @throws Exception
     */
    @Test
    public void testCase4() throws Exception {
        refundQueryBusiness.setRefundQueryService(new MockRefundQueryService("/refundqueryserviceresponsedata/refundqueryfail.xml"));
        runDemo(bridgeForRefundQueryBusinessTest, listener);
        assertEquals(listener.getResult(), listener.ON_REFUND_QUERY_FAIL);
    }

    /**
     * 测试Case5
     * 通过本地XML数据模拟退款查询成功
     *
     * @throws Exception
     */
    @Test
    public void testCase5() throws Exception {
        refundQueryBusiness.setRefundQueryService(new MockRefundQueryService("/refundqueryserviceresponsedata/refundquerysuccess.xml"));
        runDemo(bridgeForRefundQueryBusinessTest, listener);
        assertEquals(listener.getResult(), listener.ON_REFUND_QUERY_SUCCESS);
    }


    /**
     * 测试能否成功解析到数据
     * throws Exception
     */
    @Test
    public void testRefundQueryResult() throws Exception {
        refundQueryBusiness.setRefundQueryService(new MockRefundQueryService("/refundqueryserviceresponsedata/refundquerysuccess.xml"));
        runDemo(bridgeForRefundQueryBusinessTest, listener);
        assertEquals(refundQueryBusiness.getOrderListResult(), "{ outRefundNo=100000001 refundID=2001750737201411010000174008 refundChannel= refundFee=1 couponRefundFee=0 refundStatus=SUCCESS}");
    }

    /**
     * 测试能否成功解析到数据，这里是比较多项的数据
     * throws Exception
     */
    @Test
    public void testRefundQueryResult2() throws Exception {
        refundQueryBusiness.setRefundQueryService(new MockRefundQueryService("/refundqueryserviceresponsedata/refundquerysuccess2.xml"));
        runDemo(bridgeForRefundQueryBusinessTest,listener);
        assertEquals(refundQueryBusiness.getOrderListResult(), "{ outRefundNo=100000001 refundID=2001750737201411010000174008本地测试数据 refundChannel= refundFee=1 couponRefundFee=0 refundStatus=PROCESSING本地测试数据}{ outRefundNo= refundID=2001750737201411010000174008本地测试数据 refundChannel= refundFee=1 couponRefundFee=0 refundStatus=PROCESSING本地测试数据}{ outRefundNo= refundID=2001750737201411010000174008本地测试数据 refundChannel= refundFee=1 couponRefundFee=0 refundStatus=PROCESSING本地测试数据}");
    }

} 
