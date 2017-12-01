package com.tencent.business;

import com.tencent.WXPay;
import com.tencent.common.Util;
import com.tencent.protocol.refund_query_protocol.RefundQueryResData;
import com.tencent.protocol.reverse_protocol.ReverseReqData;
import com.tencent.protocol.reverse_protocol.ReverseResData;

public class ReverseBusinessDemo {
	public static void main(String[] args) throws Exception {
		ReverseBusinessDemo.run();
	}

    public static void run() throws Exception {

        WXPay.initSDKConfiguration(
        		//签名算法需要用到的秘钥
        		"1",
        		//公众账号ID，成功申请公众账号后获得
        		"1",
        		//商户ID，成功申请微信支付功能之后通过官方发出的邮件获得
        		"1",
        		//子商户ID，受理模式下必填；
        		"",
        		//HTTP证书在服务器中的路径，用来加载证书用
        		"C:/cert/apiclient_cert.p12",
        		//HTTP证书的密码，默认等于MCHID
        		"1"
        		);
        ReverseReqData reverseReqData=new ReverseReqData("","ke7us2wre");
      		String string=WXPay.requestReverseService(reverseReqData);
      		System.out.println(string);
      		ReverseResData refundQueryResData = (ReverseResData) Util.getObjectFromXML(string, ReverseResData.class);
      		
      		System.out.println(refundQueryResData.getReturn_code());
      		System.out.println(refundQueryResData.getReturn_msg());


      		System.out.println(refundQueryResData.getResult_code());
             
      		
      		
    }
	
	
}
