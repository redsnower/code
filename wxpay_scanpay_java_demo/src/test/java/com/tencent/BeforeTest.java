package com.tencent;

/**
 * User: rizenguo
 * Date: 2014/12/6
 * Time: 14:39
 */
public class BeforeTest {

    public static void initSDK(){
        WXPay.initSDKConfiguration(
                "wechatpaysdkkey20141231161452000",
                "wx3ce7a2f3b32885dd",
                "1226736202",
                "",
                "C:/wxpay_scanpay_java_cert/apiclient_cert.p12",
                "1226736202"
        );
    }

}
