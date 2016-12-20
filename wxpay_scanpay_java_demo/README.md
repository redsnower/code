# 微信支付之“被扫支付JavaDemo”

欢迎大家使用这个Demo，希望这个Demo可以大大提升大家的效率，大家遇到问题可以第一时间发邮件给到以下地址（grz@grzcn.com）或是提交你宝贵的Pull Request。  
该Demo用到了“被扫支付SDK”，SDK的详细说明请看<a href="https://github.com/grz/wxpay_scanpay_java_sdk_proj" title="被扫支付SDK" target="_blank">这里</a>

## 快速上手，使用SDK只需三步即可接入“被扫支付”  
##### 第一步，初始化SDK  

```java
    //--------------------------------------------------------------------
    //第一步：初始化SDK（只需全局初始化一次即可）
    //--------------------------------------------------------------------
    WXPay.initSDKConfiguration(
                //签名算法需要用到的秘钥
                "40a8f8aa8ebe45a40bdc4e0f7307bc66",
                //公众账号ID，成功申请公众账号后获得
                "wxf5b5e87a6a0fde94",
                //商户ID，成功申请微信支付功能之后通过官方发出的邮件获得
                "10000097",
                //子商户ID，受理模式下必填；
                "",
                //HTTP证书在服务器中的路径，用来加载证书用
                "C:/wxpay_scanpay_java_cert/10000097.p12",
                //HTTP证书的密码，默认等于MCHID
                "10000097"
        );
```

##### 第二步，准备好提交给API的数据(scanPayReqData)  

```java
    //--------------------------------------------------------------------
    //第二步：准备好提交给API的数据(scanPayReqData)
    //--------------------------------------------------------------------
    //1）创建一个可以用来生成数据的bridge，这里用的是一个专门用来测试用的Bridge，商户需要自己实现
        BridgeForScanPayBusinessTest bridge = new BridgeForScanPayBusinessTest();
        //2）从bridge里面拿到数据，构建提交被扫支付API需要的数据对象
        ScanPayReqData scanPayReqData = new ScanPayReqData(
                //这个是扫码终端设备从用户手机上扫取到的支付授权号，有效期是1分钟
                bridge.getAuthCode(),
                //要支付的商品的描述信息，用户会在支付成功页面里看到这个信息
                bridge.getBody(),
                //支付订单里面可以填的附加数据，API会将提交的这个附加数据原样返回
                bridge.getAttach(),
                //商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
                bridge.getOutTradeNo(),
                //订单总金额，单位为“分”，只能整数
                bridge.getTotalFee(),
                //商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
                bridge.getDeviceInfo(),
                //订单生成的机器IP
                bridge.getSpBillCreateIP(),
                //订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
                bridge.getTimeStart(),
                //订单失效时间，格式同上
                bridge.getTimeExpire(),
                //商品标记，微信平台配置的商品标记，用于优惠券或者满减使用
                bridge.getGoodsTag()
        );
```

##### 第三步，准备好一个用来处理各种结果分支的监听器(resultListener)  

```java
    //--------------------------------------------------------------------
        //第三步：准备好一个用来处理各种结果分支的监听器(resultListener)
        //--------------------------------------------------------------------
        //这个是Demo里面写的一个默认行为，商户需要根据自身需求来进行完善
        DefaultScanPayBusinessResultListener resultListener = new DefaultScanPayBusinessResultListener();

        //--------------------------------------------------------------------
        //搞定以上三步后执行业务逻辑
        //--------------------------------------------------------------------
        WXPay.doScanPayBusiness(scanPayReqData,resultListener);
```

## Demo简单说明
1. [Demo中包含的内容](#user-content-demo包含的内容)
2. [Demo依赖的配置项](#user-content-demo依赖的配置项)
3. [Demo需要商户自己实现的IBridge](#user-content-demo需要商户自己实现的ibridge)

## 最佳实践
1. [被扫支付业务流程最佳实践](#user-content-被扫支付业务流程最佳实践)  
2. [支付业务逻辑分支处理最佳实践](#user-content-支付业务逻辑分支处理最佳实践)
2. [商户系统接入SDK最佳实践](#user-content-商户系统接入sdk最佳实践)
3. [商户系统部署最佳实践](#user-content-商户系统部署最佳实践)

## 高级自定义  
1. [自定义查询流程和撤销流程](#user-content-高级自定义1自定义查询流程和撤销流程) 
2. [使用自己的Https请求器](#user-content-高级自定义2使用自己的https请求器) 

## “被扫支付”高级知识  
1. [调用被扫支付API的协议规则](#user-content-调用被扫支付api的协议规则)
2. [支付验证密码规则](#user-content-支付验证密码规则)


## Demo包含的内容


Demo里面需要大家关注的主要有三个地方：  

1. 四个业务Demo，位于src/main/java/com/tencent/business/目录里面，这些demo将会教大家如何调用SDK里面封装好的业务逻辑。

2. 桥接器bridge，位于src/main/java/com/tencent/bridge/目录里面。里面目前有4个bridge，分别对应4个业务demo。这个东西是用来对接商户系统逻辑，产生SDK请求所需要的特定参数用的，请大家按照API文档的说明实现这些参数的产生逻辑。 
 
3. 监听器listener，位于src/main/java/com/tencent/listener/目录里面，这几个listener都是对业务逻辑各种可能返回事件的默认处理，商户需要自己实现更加具体的处理逻辑。

## Demo依赖的配置项  
打开demo工程里的wxpay.properties文件可以看到里面有6个配置项（该demo里面用的是一个mchid为10000097的测试号）  
这些关键配置项的作用分别为：

<table>
    <tbody>
        <tr>
            <td>名称</td>
            <td>用途</td>
            <td>来源</td>
        </tr>
        <tr>
            <td>KEY</td>
            <td>签名算法需要用到的秘钥</td><td>成功申请微信支付功能之后通过官方发出的邮件获得</td>
        </tr>
        <tr>
            <td>APPID</td>
            <td>公众账号ID</td>
            <td>成功申请公众账号后获得</td>
        </tr>
        <tr>
            <td>MCHID</td>
            <td>商户ID</td>
            <td>成功申请微信支付功能之后通过官方发出的邮件获得</td>
        </tr>
        <tr>
            <td>SUBMCHID</td>
            <td>子商户ID</td>
            <td>受理模式下必须要有的一个子商户ID</td>
        </tr>
        <tr>
            <td>CERT_LOCAL_PATH</td>
            <td>HTTP证书在服务器中的路径，用来加载证书用</td>
            <td>成功申请微信支付功能之后通过官方发出的邮件获得“HTTPS证书”，这个配置项就是“HTTP证书”在服务器上所部署的路径（demo中需要的证书文件就是docs/https_cert_for_test/文件夹中的10000097.cert）</td>
        </tr>
        <tr>
            <td>CERT_PASSWORD</td>
            <td>HTTP证书的密码，默认等于MCHID</td>
            <td>成功申请微信支付功能之后通过官方发出的邮件获得</td>
        </tr>
    </tbody>
</table>

这些配置项用来对SDK进行一次初始化的时候使用。初始化方法见上面的“[第一步，初始化SDK](#user-content-demo第一步，初始化SDK)”

## Demo需要商户自己实现的IBridge
![img](https://raw.githubusercontent.com/grz/wxpay_scanpay_java_demo_proj/master/docs/asset/ibridge.jpg "ibridge桥接器")  
从上图可见IBridge桥接器其实就是定义了请求API时需要提交的各种参数的产生接口，这些接口跟商户自己的系统是紧密结合的，商户自己需要根据具体业务系统的实际情况，按照API文档定义的格式来产生相应的参数给到调用API时使用。  
举个例子，IBridge里面定义了一个非常关键的接口，叫getAuthCode()，这个接口的作用就是用来返回一个合法的“授权码”供调用API时用。  
```java
/**
 * 获取auth_code，这个是扫码终端设备从用户手机上扫取到的支付授权号，这个号是跟用户用来支付的银行卡绑定的，有效期是1分钟
 * @return 授权码
 */
public String getAuthCode(){
    //由于这个authCode有效期只有1分钟，所以实际测试SDK的时候也可以手动将微信刷卡界面一维码下的那串数字输入进来
    return "120242957324236112";
}
```
以上只是简单的hardcode（用来先简单手动输入“授权码”调试API是否能正常返回数据时用），实际上商户自己在实现这个接口的时候就需要根据自己实际系统来进行设计了，例如需要去监听“扫码枪”等具备一维码/二维码扫描功能的外设，当成功扫描到这串“授权码”的时候，将其保存下来，然后触发提交支付的API调用，调用时让IBridge桥接器中的getAuthCode()接口取得刚刚扫描到的授权码，作为参数传给支付API。  

## 被扫支付业务流程最佳实践  
被扫支付整个完成流程将会涉及到“查询”和“撤销”等请求，这里给出建议实现的流程供大家参考，SDK里面的ScanPayBusiness就是按照这个流程来设计的：  
![img](https://raw.githubusercontent.com/grz/wxpay_scanpay_java_demo_proj/master/docs/asset/best_practice.png "被扫支付最佳实践")  

从上图可见主要流程分为四种情况：  
1. 直接扣款成功：直接返回成功
2. 扣款明确失败：走撤销流程，返回失败（建议提示具体的失败信息，指示用户进行下一步操作）
3. 需输入密码：走查单流程，如果查询了一定时间还是没有成功则当失败处理，直接走撤销
4. 扣款未知失败：先走查单流程，如果查询了一定时间还是没有成功则当失败处理，直接走撤销

两个关键流程解释：  
1. 有限次查询流程：这里会根据设定的“查询次数”（用户可以自定义）和“查询间隔”来进行轮询，超过了查询次数之后还是没有查询到“支付成功”的情况会自动转入“撤销流程”
2. 撤销流程：这里会根据设定的“查询间隔”进行不停地轮询撤销API，API会通过recall字段来告诉商户侧需不需要继续轮询，如果“recall=Y”或是“撤销结果成功”都表示不需要再轮询了，然后到达“支付失败”的最终状态。

（以上最佳实践已经在SDK的ScanPayBusiness里面封装好了）

## 支付业务逻辑分支处理最佳实践  
![img](https://raw.githubusercontent.com/grz/wxpay_scanpay_java_demo_proj/master/docs/asset/scanpaybusiness_resultlistener.png "被扫支付最佳实践")    

ScanPayBusiness里面的ResultListener接口定义了支付流程中可能走到的8个分支，分别是：  
```java
 public interface ResultListener {
        //API返回ReturnCode不合法，支付请求逻辑错误，请仔细检测传过去的每一个参数是否合法，或是看API能否被正常访问
        void onFailByReturnCodeError(ScanPayResData scanPayResData);
        //API返回ReturnCode为FAIL，支付API系统返回失败，请检测Post给API的数据是否规范合法
        void onFailByReturnCodeFail(ScanPayResData scanPayResData);
        //支付请求API返回的数据签名验证失败，有可能数据被篡改了
        void onFailBySignInvalid(ScanPayResData scanPayResData);
        //用户用来支付的二维码已经过期，提示收银员重新扫一下用户微信“刷卡”里面的二维码
        void onFailByAuthCodeExpire(ScanPayResData scanPayResData);
        //授权码无效，提示用户刷新一维码/二维码，之后重新扫码支付"
        void onFailByAuthCodeInvalid(ScanPayResData scanPayResData);
        //用户余额不足，换其他卡支付或是用现金支付
        void onFailByMoneyNotEnough(ScanPayResData scanPayResData);
        //支付失败
        void onFail(ScanPayResData scanPayResData);
        //支付成功
        void onSuccess(ScanPayResData scanPayResData);
    }
```

Demo里面用到的DefaultScanPayBusinessResultListener就是实现了以上这8个接口。
这里有几点处理建议：  
1. onFailByReturnCodeError、onFailByReturnCodeFail、onFailBySignInvalid这3种属于程序逻辑问题，建议商户自己做好日志监控，发现问题要及时让工程师进行定位处理；  
2. onFailByAuthCodeExpire、onFailByAuthCodeInvalid、onFailByMoneyNotEnough这三种属于用户自身的问题，建议商户把具体出错信息提示给用户，指导用户进行下一步操作。（具体出错信息可以通过scanPayResData.getErr_code_des()获取得到）

## 商户系统接入SDK最佳实践
1. 生成一个新的订单out_trade_no
2. 输入订单金额total_fee
3. 启动扫码枪功能供用户进行扫码
4. 扫码器获取授权码auth_code，并回传给SDK
5. SDK提交支付请求
6. SDK处理API返回的数据
![img](https://raw.githubusercontent.com/grz/wxpay_scanpay_java_demo_proj/master/docs/asset/best_cdraw.png "商户系统接入最佳实践")


## 商户系统部署最佳实践  
1. 由于整套系统必须采用HTTPS来保证安全性，所以这里的支付请求必须由商户的后台系统来发起；
2. 商户系统跟SDK的对接主要就是实现IBridge里面的接口；
3. 从本demo里面有JUnit单元测试用例，商户开发者可以参考下这个示例；
![img](https://raw.githubusercontent.com/grz/wxpay_scanpay_java_demo_proj/master/docs/asset/system_structure.png "商户系统部署最佳实践")

## 高级自定义：1）自定义查询流程和撤销流程 

商户可以根据自己的实际需要来配置[支付业务流程](#user-content-被扫支付业务流程最佳实践)中的“查询流程”的“查询次数”和“查询间隔”；“撤销流程”的“查询间隔”，例如：

```java
    //自定义调用查询接口的间隔
    scanPayBusiness.setWaitingTimeBeforePayQueryServiceInvoked(3000);
    //自定义调用查询接口的次数
    scanPayBusiness.setPayQueryLoopInvokedCount(1);
    //自定义调用撤销接口的间隔
    scanPayBusiness.setWaitingTimeBeforeReverseServiceInvoked(2000);
```
  

## 高级自定义：2）使用自己的Https请求器  
可能有些商户自己系统里面已经拥有自己封装得很完善的Https请求器了，想让SDK的服务统一都走自己的Https请求器来发起请求的话，这里提供了一个配置项可以实现这个功能：

```java
 //自定义底层的HttpsRequest
    Configure.setHttpsRequestClassName("com.tencent.httpsrequest.HttpsRequestForTest");
```

温馨提示：自己实现的Https请求器必须实现IServiceRequest这个接口，可以参考SDK里面的HttpRequest的实现。



## 调用被扫支付API的协议规则  

<table>
    <tbody>
        <tr>
            <td>传输方式</td>
            <td>为保证交易安全性，采用HTTPS传输</td>
        </tr>
        <tr>
            <td>提交方式</td>
            <td>采用POST方法提交</td>
        </tr>
        <tr>
            <td>数据格式</td>
            <td>提交和返回数据都为XML格式，根节点名为xml</td>
        </tr>
        <tr>
            <td>字符编码</td>
            <td>统一采用UTF-8字符编码</td>
        </tr>
        <tr>
            <td>签名算法</td>
            <td>MD5</td>
        </tr>
        <tr>
            <td>签名要求</td>
            <td>请求和接收数据均需要校验签名，签名的方法在SDK里面已经封装好了</td>
        </tr>
        <tr>
            <td>证书要求/td>
            <td>调用申请退款、撤销订单接口需要商户证书</td>
        </tr>
        <tr>
            <td>判断逻辑</td>
            <td>先判断协议字段返回，再判断业务返回，最后判断交易状态</td>
        </tr>
    </tbody>
</table>


## 支付验证密码规则  
1. 支付金额>300元的交易需要验证用户支付密码；
2. 用户账号每天最多有10笔交易可以免密，超过后需要验证密码；
3. 微信支付后台判断用户支付行为有异常情况，符合免密规则的交易也会要求验证密码；


###### ([返回目录](#user-content-demo快速上手指引))  