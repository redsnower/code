package com.tencent.business.bridgefortest;

import com.tencent.bridge.IBridge;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * User: rizenguo
 * Date: 2014/11/4
 * Time: 14:35
 * 这个桥接器产生一些数据来测试ScanPayBusinessDemo，把整个支付的流程跑通
 */
public class BridgeForScanPayBusinessTest implements IBridge {

    /**
     * 获取auth_code，这个是扫码终端设备从用户手机上扫取到的支付授权号，这个号是跟用户用来支付的银行卡绑定的，有效期是1分钟
     * @return 授权码
     */
    public String getAuthCode(){
        //由于这个authCode有效期只有1分钟，所以实际测试SDK的时候也可以手动将微信刷卡界面一维码下的那串数字输入进来
        return "130070451334348700";
    }

    /**
     * 获取out_trade_no，这个是商户系统内自己可以用来唯一标识该笔订单的字符串，可以包含字母和数字，不超过32位
     * @return 订单号
     */
    public String getOutTradeNo(){
        //建议测试的时候自己手动写一个，多次测试可以慢慢累加
        return getTimeStart();
    }

    /**
     * 获取body:要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
     * @return 描述信息
     */
    public String getBody(){
        return "微信支付JavaSDK测试：ScanPayBusinessDemo";
    }


    /**
     * 获取attach:支付订单里面可以填的附加数据，API会将提交的这个附加数据原样返回，有助于商户自己可以注明该笔消费的具体内容，方便后续的运营和记录
     * @return 附加数据
     */
    public String getAttach(){
        return "微信支付JavaSDK测试：ScanPayBusinessDemo，为了跑通支付全流程";
    }

    /**
     * 获取订单总额
     * @return 订单总额
     */
    public int getTotalFee(){
        //测试的时候就用1分钱吧，这可是真金白银啊。。。
        return 1;
    }

    /**
     * 获取device_info:商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
     * @return 支付终端设备号
     */
    public String getDeviceInfo(){
        //建议商户自己尽量实现这个获取具体支付终端设备号的功能
        return "GRZ的测试机";
    }

    /**
     * 获取userip:终端设备的ip地址
     * @return 终端设备的ip地址
     */
    @Override
    public String getUserIp() {
        return "8.8.8.8";
    }

    /**
     * 获取spBillCreateIP:订单生成的机器IP
     * @return 订单生成的机器IP
     */
    public String getSpBillCreateIP(){
        //这个就是生成订单机器的IP地址了，请商户尽量获取到传过来，作用跟上面的device_info类似，在后续定位问题的时候可以派上用场
        return "127.0.0.1";
    }

    /**
     * 获取time_start:订单生成时间，格式为yyyyMMddHHmmss，如2009年12 月25 日9 点10 分10 秒表示为20091225091010。时区为GMT+8 beijing。该时间取自商户服务器
     * @return 订单生成时间
     */
    public String getTimeStart(){
        //订单生成时间自然就是当前服务器系统时间咯
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 获取time_end:订单生成时间
     * @return 订单失效时间
     */
    public String getTimeExpire(){
        //订单失效时间，这个每个商户有自己的过期原则，我这里设置的是10天后过期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH,10);
        return simpleDateFormat.format(c.getTime());
    }

    /**
     * 获取goods_tag:商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
     * @return 商品标记
     */
    public String getGoodsTag(){
        //这个功能要参考“优惠券”功能才知道怎么弄，当然有文档了
        return "";
    }

    @Override
    public String getTransactionID() {
        return null;
    }

    @Override
    public String getOutRefundNo() {
        return null;
    }

    @Override
    public int getRefundFee() {
        return 0;
    }

    @Override
    public String getRefundID() {
        return null;
    }

    @Override
    public String getBillDate() {
        return null;
    }

    @Override
    public String getBillType() {
        return null;
    }

    @Override
    public String getOpUserID() {
        return null;
    }

    @Override
    public String getRefundFeeType() {
        return null;
    }


}
