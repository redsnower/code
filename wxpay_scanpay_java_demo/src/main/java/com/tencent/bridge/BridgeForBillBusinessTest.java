package com.tencent.bridge;

import com.tencent.service.DownloadBillService;

/**
 * User: rizenguo
 * Date: 2014/11/10
 * Time: 11:09
 */
public class BridgeForBillBusinessTest implements IBridge{
    @Override
    public String getAuthCode() {
        return null;
    }

    @Override
    public String getOutTradeNo() {
        return null;
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
        return "20141108";
    }

    @Override
    public String getBillType() {
        return DownloadBillService.BILL_TYPE_ALL;
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
