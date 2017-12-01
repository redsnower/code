package com.tencent.business;

import com.tencent.WXPay;
import com.tencent.listener.DefaultScanPayBusinessResultListener;
import com.tencent.protocol.pay_protocol.ScanPayReqData;
import com.tencent.protocol.pay_protocol.ScanPayResData;
import com.tencent.bridge.BridgeForScanPayBusinessTest;
import com.tencent.common.Util;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 16:00
 * 支付业务逻辑Demo
 */
public class ScanPayBusinessDemo{
 
	public static void main(String[] args) throws Exception {
		 ScanPayBusinessDemo.run();
	}
    public static void run() throws Exception {
        //--------------------------------------------------------------------
        //第一步：初始化SDK（只需全局初始化一次即可）
        //--------------------------------------------------------------------
    	   WXPay.initSDKConfiguration(
           		//签名算法需要用到的秘钥
    			 "111",
           		//公众账号ID，成功申请公众账号后获得
           		"111",
           		//商户ID，成功申请微信支付功能之后通过官方发出的邮件获得
           		"111",
           		//子商户ID，受理模式下必填；
           		"",
           		//HTTP证书在服务器中的路径，用来加载证书用
           		"C:/cert/apiclient_cert.p12",
           		//HTTP证书的密码，默认等于MCHID
           		"111"
           		);
//        WXPay.initSDKConfiguration(
//        		//签名算法需要用到的秘钥
//        		"sunrui8763C3360c4908659048569034",
//        		//公众账号ID，成功申请公众账号后获得
//        		"wx40aa3cd79125e0e5",
//        		//商户ID，成功申请微信支付功能之后通过官方发出的邮件获得
//        		"1410780102",
//        		//子商户ID，受理模式下必填；
//        		"",
//        		//HTTP证书在服务器中的路径，用来加载证书用
//        		"C:/cert/apiclient_cert.p12",
//        		//HTTP证书的密码，默认等于MCHID
//        		"1410780102"
//        		);

        //--------------------------------------------------------------------
        //第二步：准备好提交给API的数据(scanPayReqData)
        //--------------------------------------------------------------------
        //1）创建一个可以用来生成数据的bridge，这里用的是一个专门用来测试用的Bridge，商户需要自己实现
        BridgeForScanPayBusinessTest bridge = new BridgeForScanPayBusinessTest();
        //2）从bridge里面拿到数据，构建提交被扫支付API需要的数据对象
        ScanPayReqData scanPayReqData = new ScanPayReqData(
                //这个是扫码终端设备从用户手机上扫取到的支付授权号，有效期是1分钟
                "130184461720191423",
                //要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
                bridge.getBody(),
                //支付订单里面可以填的附加数据，API会将提交的这个附加数据原样返回
                bridge.getAttach(),
                //商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
                "002",
                //订单总金额，单位为“分”，只能整数
                1,
                //商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
                bridge.getDeviceInfo(),
                //订单生成的机器IP
                bridge.getSpBillCreateIP(),
                //订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
                bridge.getTimeStart(),
                //订单失效时间，格式同上
                bridge.getTimeExpire(),
                //商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
                bridge.getGoodsTag()
        );

        //--------------------------------------------------------------------
        //第三步：准备好一个用来处理各种结果分支的监听器(resultListener)
        //--------------------------------------------------------------------
        //这个是Demo里面写的一个默认行为，商户需要根据自身需求来进行完善
        DefaultScanPayBusinessResultListener resultListener = new DefaultScanPayBusinessResultListener();

        //--------------------------------------------------------------------
        //搞定以上三步后执行业务逻辑
        //--------------------------------------------------------------------
       String resstr= WXPay.requestScanPayService(scanPayReqData);
       ScanPayResData scanPayResData = (ScanPayResData) Util.getObjectFromXML(resstr, ScanPayResData.class);
       System.out.println("对象"+scanPayResData.toString());
       System.out.println("返回值："+resstr);
       // WXPay.doScanPayBusiness(scanPayReqData,resultListener);
    }

}
