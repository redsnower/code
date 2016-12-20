package com.tencent;

import com.tencent.business.DownloadBillBusinessDemo;
import com.tencent.business.RefundBusinessDemo;
import com.tencent.business.RefundQueryBusinessDemo;
import com.tencent.business.ScanPayBusinessDemo;
import com.tencent.business.ScanPayQueryBusinessDemo;
import com.tencent.common.Signature;
import com.tencent.common.Util;

public class Main {
	 

    public static void main(String[] args) {

        try {

            //--------------------------------------------------------------------
            //温馨提示，第一次使用该SDK时请到wxpay.properties里面进行配置
            //--------------------------------------------------------------------


            //--------------------------------------------------------------------
            //PART Three:业务Demo测试
            //--------------------------------------------------------------------

            //测试被扫支付业务逻辑
             // ScanPayBusinessDemo.run();
        	
        	 ScanPayQueryBusinessDemo.run();
        	 
            //测试退款业务逻辑
               RefundBusinessDemo.run();

            //测试退款查询业务逻辑
//              RefundQueryBusinessDemo.run();

            //测试下载对账单业务逻辑
           //DownloadBillBusinessDemo.run();

            //或是直接运行ScanPaySDKTestSuite 即可
             System.out.println("支付完成");
            //小提示：本地通过xml进行API数据模拟的时候，先按需手动修改xml各个节点的值，然后通过以下方法对这个新的xml数据进行签名得到一串合法的签名，最后把这串签名放到这个xml里面的sign字段里，这样进行模拟的时候就可以通过签名验证了
           //System.out.println(Signature.getSignFromResponseString(Util.getLocalXMLString("/payserviceresponsedata/paysuccesswithcoupondata.xml")));

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
