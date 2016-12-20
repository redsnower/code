package com.tencent.business.bridgefortest;

import com.tencent.bridge.IBridge;
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
        return "1000000005";
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
        return "1001750737201411070005670589";
    }

    @Override
    public String getOutRefundNo() {
        return "100000006";
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
