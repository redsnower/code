package com.tencent.business;

import com.tencent.WXPay;
import com.tencent.listener.DefaultRefundQueryBusinessResultListener;
import com.tencent.protocol.refund_query_protocol.RefundQueryReqData;
import com.tencent.protocol.refund_query_protocol.RefundQueryResData;
import com.tencent.bridge.BridgeForRefundQueryBusinessTest;
import com.tencent.common.Util;

/**
 * User: rizenguo
 * Date: 2014/11/7
 * Time: 15:39
 * 退款查询的业务逻辑主要就是解析API返回的xml数据
 */
public class RefundQueryBusinessDemo {

	public static void main(String[] args) throws Exception {
		RefundQueryBusinessDemo.run();
	}
    public static void run() throws Exception {
    	 WXPay.initSDKConfiguration(
         		//签名算法需要用到的秘钥
         		"1",
         		//公众账号ID，成功申请公众账号后获得
         		"1",
         		//商户ID，成功申请微信支付功能之后通过官方发出的邮件获得
         		"1",
         		//子商户ID，受理模式下必填；
         		"",
         		//HTTP证书在服务器中的路径，用来加载证书用
         		"C:/cert/apiclient_cert.p12",
         		//HTTP证书的密码，默认等于MCHID
         		"1"
         		);
    	
        //第一步：创建一个可以用来生成数据的bridge，这里用的是一个专门用来测试用的Bridge，商户需要自己实现这个Bridge来准备好数据
        BridgeForRefundQueryBusinessTest bridge = new BridgeForRefundQueryBusinessTest();
        //第二步：从bridge里面拿到数据，构建提交被扫支付API需要的数据对象
        RefundQueryReqData refundQueryReqData = new RefundQueryReqData(
                bridge.getTransactionID(),//是微信系统为每一笔支付交易分配的订单号，通过这个订单号可以标识这笔交易，它由支付订单API支付成功时返回的数据里面获取到。建议优先使用
                "2235",//商户系统内部的订单号,transaction_id 、out_trade_no 二选一，如果同时存在优先级：transaction_id>out_trade_no
                bridge.getDeviceInfo(),//商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
                bridge.getOutRefundNo(),//商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
                null//来自退款API的成功返回，微信退款单号refund_id、out_refund_no、out_trade_no 、transaction_id 四个参数必填一个，如果同事存在优先级为：refund_id>out_refund_no>transaction_id>out_trade_no
        );
        //第三步：创建一个用来处理被扫支付业务逻辑各种结果分支的监听器resultListener
        DefaultRefundQueryBusinessResultListener resultListener = new DefaultRefundQueryBusinessResultListener();
        //第四步：执行业务逻辑
        String resstring= WXPay.requestRefundQueryService(refundQueryReqData);
        System.out.println(resstring);
        RefundQueryResData refundQueryResData = (RefundQueryResData) Util.getObjectFromXML(resstring, RefundQueryResData.class);
        System.out.println(refundQueryResData.getReturn_code());
        System.out.println(refundQueryResData.getResult_code());
       // WXPay.doRefundQueryBusiness(refundQueryReqData,resultListener);
    }

}
