package com.tencent.business;

import com.tencent.WXPay;
import com.tencent.listener.DefaultRefundBusinessResultListener;
import com.tencent.protocol.pay_protocol.ScanPayResData;
import com.tencent.protocol.refund_protocol.RefundReqData;
import com.tencent.protocol.refund_protocol.RefundResData;
import com.tencent.protocol.reverse_protocol.ReverseReqData;
import com.tencent.bridge.BridgeForRefundBusinessTest;
import com.tencent.common.Util;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 16:00
 * 退款的业务逻辑比较简单，直接发起请求，看退款结果是否成功即可。失败的时候给相应提示引导收银员手动重试或是记下订单号走投诉流程
 */
public class RefundBusinessDemo {

	public static void main(String[] args) throws Exception {
		RefundBusinessDemo.run();
	}
    public static void run() throws Exception {

        WXPay.initSDKConfiguration(
        		//签名算法需要用到的秘钥
        		"sunrui8763C3360c4908659048569034",
        		//公众账号ID，成功申请公众账号后获得
        		"wx40aa3cd79125e0e5",
        		//商户ID，成功申请微信支付功能之后通过官方发出的邮件获得
        		"1410780102",
        		//子商户ID，受理模式下必填；
        		"",
        		//HTTP证书在服务器中的路径，用来加载证书用
        		"C:/cert/apiclient_cert.p12",
        		//HTTP证书的密码，默认等于MCHID
        		"1410780102"
        		);
        //第一步：创建一个可以用来生成数据的bridge，这里用的是一个专门用来测试用的Bridge，商户需要自己实现这个Bridge来准备好数据
        BridgeForRefundBusinessTest bridge = new BridgeForRefundBusinessTest();
        //第二步：从bridge里面拿到数据，构建提交被扫支付API需要的数据对象
        RefundReqData refundReqData = new RefundReqData(
                null,//是微信系统为每一笔支付交易分配的订单号，通过这个订单号可以标识这笔交易，它由支付订单API支付成功时返回的数据里面获取到。建议优先使用
                "2234",//商户系统内部的订单号,transaction_id 、out_trade_no 二选一，如果同时存在优先级：transaction_id>out_trade_no
                bridge.getDeviceInfo(),//微信支付分配的终端设备号，与下单一致
                "a2348",//商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
                1,//订单总金额，单位为分
                1,//退款总金额，单位为分,可以做部分退款
                bridge.getOpUserID(),//操作员帐号, 默认为商户号
                bridge.getRefundFeeType()//货币类型，符合ISO 4217标准的三位字母代码，默认为CNY（人民币）
        );
        //第三步：创建一个用来处理被扫支付业务逻辑各种结果分支的监听器resultListener
      //  DefaultRefundBusinessResultListener resultListener = new DefaultRefundBusinessResultListener();
        //第四步：执行业务逻辑
        
      
		
        String resstr= WXPay.requestRefundService(refundReqData);
        RefundResData refundResData = (RefundResData) Util.getObjectFromXML(resstr, RefundResData.class);
        System.out.println(refundResData.getReturn_code());
        System.out.println(refundResData.getResult_code());
        System.out.println(refundResData.getErr_code_des());
        // WXPay.doRefundBusiness(refundReqData, resultListener);
    }
}
