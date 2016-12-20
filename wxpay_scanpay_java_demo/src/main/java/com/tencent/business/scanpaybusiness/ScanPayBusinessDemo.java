package com.tencent.business.scanpaybusiness;

import com.tencent.WXPay;
import com.tencent.listener.DefaultScanPayBusinessResultListener;
import com.tencent.protocol.pay_protocol.ScanPayReqData;
import com.tencent.bridge.BridgeForScanPayBusinessTest;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 16:00
 * 支付业务逻辑Demo
 */
public class ScanPayBusinessDemo{

    public static void run() throws Exception {

        //第一步：构建提交被扫支付API需要的数据对象ScanPayReqData


        //第一步：创建一个可以用来生成数据的bridge，这里用的是一个专门用来测试用的Bridge，商户需要自己实现这个Bridge来准备好数据
        BridgeForScanPayBusinessTest bridge = new BridgeForScanPayBusinessTest();
        //第二步：从bridge里面拿到数据，构建提交被扫支付API需要的数据对象
        ScanPayReqData scanPayReqData = new ScanPayReqData(
                bridge.getAuthCode(),//这个是扫码终端设备从用户手机上扫取到的支付授权号，这个号是跟用户用来支付的银行卡绑定的，有效期是1分钟
                bridge.getBody(),//要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
                bridge.getAttach(),//支付订单里面可以填的附加数据，API会将提交的这个附加数据原样返回
                bridge.getOutTradeNo(),//商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
                bridge.getTotalFee(),//订单总金额，单位为“分”，只能整数
                bridge.getDeviceInfo(),//商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
                bridge.getSpBillCreateIP(),//订单生成的机器IP
                bridge.getTimeStart(),//订单生成时间， 格式为yyyyMMddHHmmss，如2009年12 月25 日9 点10 分10 秒表示为20091225091010。
                bridge.getTimeExpire(),//订单失效时间，格式同上
                bridge.getGoodsTag()//商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
        );
        //第三步：创建一个用来处理被扫支付业务逻辑各种结果分支的监听器resultListener
        DefaultScanPayBusinessResultListener resultListener = new DefaultScanPayBusinessResultListener();
        //第四步：执行业务逻辑
        WXPay.doScanPayBusiness(scanPayReqData,resultListener);
    }

}
