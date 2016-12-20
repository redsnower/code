package com.tencent.bridge;

import com.tencent.common.Configure;

/**
 * User: rizenguo
 * Date: 2014/11/7
 * Time: 15:54
 */
public class BridgeForRefundBusinessTest implements IBridge {
    @Override
    public String getAuthCode() {
        return null;
    }

    @Override
    public String getOutTradeNo() {
        return "2207";
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
        return 1;
    }

    @Override
    public String getDeviceInfo() {
        return "lvjianliang的测试机";
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
        return null;
    }

    @Override
    public String getOutRefundNo() {
        return "x1000000096";
    }

    @Override
    public int getRefundFee() {
        return 1;
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
        return Configure.getMchid();
    }

    @Override
    public String getRefundFeeType() {
        return "CNY";
    }
}
