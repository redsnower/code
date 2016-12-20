package com.tencent.bridge;


/**
 * User: rizenguo
 * Date: 2014/11/7
 * Time: 16:07
 */
public class BridgeForRefundQueryBusinessTest implements IBridge{
    @Override
    public String getAuthCode() {
        return null;
    }

    @Override
    public String getOutTradeNo() {
        return "1000000007";
    }

    @Override
    public String getBody() {
        return null;
    }

    @Override
    public String getAttach() {
        return null;
    }

    @Override
    public int getTotalFee() {
        return 0;
    }

    @Override
    public String getDeviceInfo() {
        return "123";
    }

    @Override
    public String getUserIp() {
        return "8.8.8.8";
    }

    @Override
    public String getSpBillCreateIP() {
        return null;
    }

    @Override
    public String getTimeStart() {
        return null;
    }

    @Override
    public String getTimeExpire() {
        return null;
    }

    @Override
    public String getGoodsTag() {
        return null;
    }

    @Override
    public String getTransactionID() {
        return "4003852001201611240677820024";
    }

    @Override
    public String getOutRefundNo() {
        return "100000008";
    }

    @Override
    public int getRefundFee() {
        return 0;
    }

    @Override
    public String getRefundID() {
        return "2001750737201411010000174008";
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
