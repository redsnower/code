package com.tencent.bridge;

/**
 * User: rizenguo
 * Date: 2014/11/5
 * Time: 15:50
 * 这个IBridge类专门用来模拟authCode不合法的情况，测试ScanPayBusinessDemo中的Case5
 */
public class BridgeForScanPayBusinessCase5Test extends BridgeForScanPayBusinessTest {

    /**
     * 获取auth_code，这个是扫码终端设备从用户手机上扫取到的支付授权号，这个号是跟用户用来支付的银行卡绑定的，有效期是1分钟
     * @return 授权码
     */
    @Override
    public String getAuthCode(){
        //由于这个authCode有效期只有1分钟，所以实际测试SDK的时候也可以手动将微信刷卡界面一维码下的那串数字输入进来
        return "dfgdfg";//随便乱写的，肯定不合法啦
    }
}
