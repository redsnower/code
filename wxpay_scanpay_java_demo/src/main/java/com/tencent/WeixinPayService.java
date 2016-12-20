package com.tencent;

 

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.tencent.WXPay;
import com.tencent.common.Util;
import com.tencent.protocol.pay_protocol.ScanPayReqData;
import com.tencent.protocol.pay_protocol.ScanPayResData;
 

 
public class WeixinPayService    {
	
	
	static{
		   WXPay.initSDKConfiguration(
	           		//签名算法需要用到的秘钥
	           		"ABCDEFGHIJKLMNOPQRSTUVWXYZ123456",
	           		//公众账号ID，成功申请公众账号后获得
	           		"wx40aa3cd79125e0e5",
	           		//商户ID，成功申请微信支付功能之后通过官方发出的邮件获得
	           		"1410780102",
	           		//子商户ID，受理模式下必填；
	           		"",
	           		//HTTP证书在服务器中的路径，用来加载证书用
	           		"C:/cert/apiclient_cert.p12",
	           		//HTTP证书的密码，默认等于MCHID
	           		"1410780102"
	           		);
	}
	/**
	 * 
	 * @param scanPayReqData
	 * @return
	 * @description  提交微信支付数据。完成支付请求。
	 * @version 1.0
	 * @author 吕建亮
	 * @throws Exception 
	 * @update Nov 28, 2016 4:49:58 PM
	 */
	public ScanPayResData sendScanPayReqData(ScanPayReqData scanPayReqData) throws Exception {
	       String resstr= WXPay.requestScanPayService(scanPayReqData);
	       ScanPayResData scanPayResData = (ScanPayResData) Util.getObjectFromXML(resstr, ScanPayResData.class);
	       //System.out.println("对象"+scanPayResData.toString()); 
		return scanPayResData;
	}
	public static void main(String[] args) {
	 
		WeixinPayService WXPay =new WeixinPayService();
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	        String time=simpleDateFormat.format(new Date());
	        Calendar c = Calendar.getInstance();
	        c.setTime(new Date());
	        c.add(Calendar.DAY_OF_MONTH,10);
	        String time2= simpleDateFormat.format(c.getTime());
		ScanPayReqData scanPayReqData = new ScanPayReqData(
				// 这个是扫码终端设备从用户手机上扫取到的支付授权号，有效期是1分钟
						"123",
						// 要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
						"产品描述",
						// 支付订单里面可以填的附加数据，API会将提交的这个附加数据原样返回
						"",
						// 商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
						"123456",
						// 订单总金额，单位为“分”，只能整数
						1,
						// 商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
						"123",
						// 订单生成的机器IP
						"127.0.0.1",
						// 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
						time,
						// 订单失效时间，格式同上
						time2,
						// 商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
						"");
	        //--------------------------------------------------------------------
	        //搞定以上三步后执行业务逻辑
	        //--------------------------------------------------------------------
		  try {
			ScanPayResData scanPayResData= WXPay.sendScanPayReqData(scanPayReqData);
			System.out.println(scanPayResData.getReturn_code());
			System.out.println(scanPayResData.getResult_code());
			System.out.println(scanPayResData.getErr_code_des());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	}

}
