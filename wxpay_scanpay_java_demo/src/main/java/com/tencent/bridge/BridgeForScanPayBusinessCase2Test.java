package com.tencent.bridge;

/**
 * User: rizenguo
 * Date: 2014/11/5
 * Time: 16:45
 * 这个Bridge用来模拟ScanPayBusinessDemo中的“Case2:支付API系统返回失败，请检测Post给API的数据是否规范合法”
 * 要模拟这种情况很简单，只需要把下面一些必须传的参数干掉一两个就可以达到目的了~该类故意把getOutTradeNo()返回空来达到目的，getAuthCode()返回空也一样效果
 */
public class BridgeForScanPayBusinessCase2Test extends BridgeForScanPayBusinessTest {

    /**
     * 获取out_trade_no，这个是商户系统内自己可以用来唯一标识该笔订单的字符串，可以包含字母和数字，不超过32位
     * @return 订单号
     */
    @Override
    public String getOutTradeNo(){
        //建议测试的时候自己手动写一个，多次测试可以慢慢累加
        return "";
    }

}
