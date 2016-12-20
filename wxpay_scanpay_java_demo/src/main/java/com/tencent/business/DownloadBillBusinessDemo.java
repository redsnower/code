package com.tencent.business;

import com.tencent.WXPay;
import com.tencent.listener.DefaultDownloadBillBusinessResultListener;
import com.tencent.protocol.downloadbill_protocol.DownloadBillReqData;
import com.tencent.bridge.BridgeForBillBusinessTest;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 16:01
 * 下载对账单的Demo
 */
public class DownloadBillBusinessDemo {

    public static void run() throws Exception {
        //第一步：创建一个可以用来生成数据的bridge，商户需要自己实现这个Bridge来构建提交被扫支付API需要的数据对象
        BridgeForBillBusinessTest bridge = new BridgeForBillBusinessTest();
        DownloadBillReqData scanPayReqData = new DownloadBillReqData(
                bridge.getDeviceInfo(),
                bridge.getBillDate(),
                bridge.getBillType()
        );
        //第二步：创建一个用来处理被扫支付业务逻辑各种结果分支的监听器resultListener
        DefaultDownloadBillBusinessResultListener resultListener = new DefaultDownloadBillBusinessResultListener();
        //第三步：执行业务逻辑
        WXPay.doDownloadBillBusiness(scanPayReqData, resultListener);
    }

}