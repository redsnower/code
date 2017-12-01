package com.tencent.business;

import com.tencent.WXPay;
import com.tencent.bridge.BridgeForScanPayBusinessTest;
import com.tencent.common.Util;
import com.tencent.listener.DefaultScanPayBusinessResultListener;
import com.tencent.protocol.pay_protocol.ScanPayReqData;
import com.tencent.protocol.pay_protocol.ScanPayResData;
import com.tencent.protocol.pay_query_protocol.ScanPayQueryReqData;
import com.tencent.protocol.pay_query_protocol.ScanPayQueryResData;

public class ScanPayQueryBusinessDemo {
	public static void main(String []args) throws Exception{
		ScanPayQueryBusinessDemo.run();
	}
	
	 public static void run() throws Exception {
	        //--------------------------------------------------------------------
	        //第一步：初始化SDK（只需全局初始化一次即可）
	        //--------------------------------------------------------------------
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
 

	        //--------------------------------------------------------------------
	        //第二步：准备好提交给API的数据(scanPayReqData)
	        //--------------------------------------------------------------------
	        
	        ScanPayQueryReqData scanPayQueryReqData=new ScanPayQueryReqData(null, "2229") ;

	        //--------------------------------------------------------------------
	        //第三步：准备好一个用来处理各种结果分支的监听器(resultListener)
	        //--------------------------------------------------------------------
	        //这个是Demo里面写的一个默认行为，商户需要根据自身需求来进行完善

	       
		//--------------------------------------------------------------------
	        //搞定以上三步后执行业务逻辑
	        //--------------------------------------------------------------------
	       String resstr= WXPay.requestScanPayQueryService(scanPayQueryReqData);
	       ScanPayQueryResData scanPayResData = (ScanPayQueryResData) Util.getObjectFromXML(resstr, ScanPayQueryResData.class);
	       System.out.println("对象"+scanPayResData.toString());
	       System.out.println("返回值："+resstr);
	       // WXPay.doScanPayBusiness(scanPayReqData,resultListener);
	    }
}
