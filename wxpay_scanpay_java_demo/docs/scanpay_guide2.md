# 微信支付入门到精通

## 第一章：欲练此功，必先自“攻”   

程序员天生骄傲，讨厌各种文档，但是这趟事情没办法，要接入微信支付的“被扫支付”功能的话，还真得补一点基础知识，它们分别是：  
1. 攻读<a href="https://github.com/grz/wxpay_scanpay_java_sdk#user-content-%E4%BB%80%E4%B9%88%E6%98%AF%E8%A2%AB%E6%89%AB%E6%94%AF%E4%BB%98" target="_blank">《什么是“被扫支付”》</a>  
2. 攻读<a href="https://mp.weixin.qq.com/paymch/readtemplate?t=mp/business/faq_tmpl" target="_blank">《商户申请“被扫支付”指引》</a>  

有了以上的基础知识之后，你总算知道微信支付的“被扫支付”到底是个什么东西，但是要想做好接入这件事情，这只是刚刚开始而已。

## 第二章：苦修武林秘籍  

上天总是会为每一个跌下山崖命不该绝的程序员配备一本“武林秘籍”作为奖励，这不，马上迎接你就有好几本秘籍。

#### 初级篇：《API文档》修炼  

API是微信支付后台提供的最基础的最原始的形式，API定义了一个具体的原子功能，例如“支付”API、“查询API”、“撤销API”、“退款API”、“退款查询API”、“下载对账单API”等，每一个API都是一个具体的功能。  
同时API也会定义调用这个API时需要传过去的每一个参数的含义和具体格式，同时还会定义每一个返回参数的含义。

而这一切都在<a href="http://mch.weixin.qq.com/wiki/doc/api/" target="_blank">《微信支付API文档》</a>里面做了非常详细的说明。
但是该文档里面内容非常多，在这里建议先重点阅读里面的<a href="http://mch.weixin.qq.com/wiki/doc/api/index.php?chapter=5_1" target="_blank">“被扫支付”</a>。其他章节简单过一下，后面遇到问题再查一查即可。


#### 中级篇：《SDK文档》修炼  

在初级篇里面的API文档其实大家可以发现里面定义了很多条件，例如“参数必须以XML的格式post给API”、“API返回的数据格式也是XML”、“数据需要签名”、“需要加随机数”、“需要发HTTPS请求”等。如果条件都是开发者需要自己去折腾的话，那真的有多几条命都不够花，于是SDK的诞生就显得非常有必要了。  

##### 当你学会使用SDK之后，意味着什么？  
1. 意味着XML数据组装和解析不需要自己动手了,SDK帮你搞定；
2. 签名生成、签名验证通通不需要开发者去注意，SDK自动帮忙搞定；
3. 随机数生成算法SDK自动帮你搞定，自动帮你调用，让你几乎完全没有感知到它的存在；
4. HTTPS请求器的封装SDK帮你搞定，你只需要轻松调用即可；  

如何用好微信支付SDK，请详细修炼<a href="https://github.com/grz/wxpay_scanpay_java_sdk_proj" target="_blank">《微信支付SDK说明文档》</a>


#### 高级篇：《Demo文档》修炼  

中级篇里面介绍的是如何使用官方提供的SDK帮你省去很多繁琐的算法、数据解析等工作，但是要想更加地省事，当然还得好好修炼Demo文档。 

##### Demo里面帮你搞定了什么？  
1. 完善的经过自测用例测试过的代码，直接拷贝去用，绿色又环保；
2. 经过其他商户帮忙验证过的最佳实践（什么时候该调用查询接口？什么时候该调用撤销接口？），如果懒得自己折腾又想图个保险，请直接拿去用，确保安全可靠、系统稳定；
3. 商户可以把精力聚焦于如何产生“授权码”、“订单号”、“订单描述信息”等跟商户自己息息相关的接口，供SDK使用，微信支付侧的逻辑完全不需要商户过多操心； 

如何用好微信支付Demo，请详细修炼<a href="https://github.com/grz/wxpay_scanpay_java_demo_proj" target="_blank">《微信支付Demo说明文档》</a> 

## 第三章：融会贯通，出神入化  

当你学会如何使用Demo的时候，相信会觉得好方便，但是Demo终究还是只是Demo而已，跟商户自己相关的工作还是得商户开发自己实现。Demo中是用一个Bridge的模式来连接商户系统和被扫支付SDK，用一个ResultListener来处理业务逻辑可能走到的分支。
在这里你还需要仔细研究以下几个“最佳实践”，有助你更好地了解SDK中业务逻辑层的封装，和高级使用方法：  
1. 精通“<a href="https://github.com/grz/wxpay_scanpay_java_demo_proj#user-content-%E8%A2%AB%E6%89%AB%E6%94%AF%E4%BB%98%E4%B8%9A%E5%8A%A1%E6%B5%81%E7%A8%8B%E6%9C%80%E4%BD%B3%E5%AE%9E%E8%B7%B5" target="_blank">被扫支付业务流程最佳实践</a>”  
2. 精通“<a href="https://github.com/grz/wxpay_scanpay_java_demo_proj#user-content-%E6%94%AF%E4%BB%98%E4%B8%9A%E5%8A%A1%E9%80%BB%E8%BE%91%E5%88%86%E6%94%AF%E5%A4%84%E7%90%86%E6%9C%80%E4%BD%B3%E5%AE%9E%E8%B7%B5" target="_blank">支付业务逻辑分支处理最佳实践</a>”  
3. 精通“<a href="https://github.com/grz/wxpay_scanpay_java_demo_proj#user-content-%E5%95%86%E6%88%B7%E7%B3%BB%E7%BB%9F%E6%8E%A5%E5%85%A5sdk%E6%9C%80%E4%BD%B3%E5%AE%9E%E8%B7%B5" target="_blank">商户系统接入SDK最佳实践</a>”  
4. 精通“<a href="https://github.com/grz/wxpay_scanpay_java_demo_proj#user-content-%E5%95%86%E6%88%B7%E7%B3%BB%E7%BB%9F%E9%83%A8%E7%BD%B2%E6%9C%80%E4%BD%B3%E5%AE%9E%E8%B7%B5" target="_blank">商户系统部署最佳实践</a>”    

除了精通以上的最佳实践外，还需要掌握Demo里面的高级自定义功能：  
1. <a href="https://github.com/grz/wxpay_scanpay_java_demo_proj#user-content-%E9%AB%98%E7%BA%A7%E8%87%AA%E5%AE%9A%E4%B9%891%E8%87%AA%E5%AE%9A%E4%B9%89%E6%9F%A5%E8%AF%A2%E6%B5%81%E7%A8%8B%E5%92%8C%E6%92%A4%E9%94%80%E6%B5%81%E7%A8%8B" target="_blank">自定义查询流程和撤销流程</a>  
2. <a href="https://github.com/grz/wxpay_scanpay_java_demo_proj#user-content-%E9%AB%98%E7%BA%A7%E8%87%AA%E5%AE%9A%E4%B9%892%E4%BD%BF%E7%94%A8%E8%87%AA%E5%B7%B1%E7%9A%84https%E8%AF%B7%E6%B1%82%E5%99%A8" target="_blank">使用自己的Https请求器</a>

##### 读完Demo文档之后基本上可以很快完成接入，但还有一些相关知识需要研读，它们是：
1. <a href="http://mch.weixin.qq.com/wiki/doc/api/index.php?chapter=0_1" target="_blank">《HTTPS服务器配置指南》</a>
2. 《商户系统部署建议》
3. 《商户系统开发设计建议》
4. 《商户接入自测验收手册》
5. <a href="http://mch.weixin.qq.com/wiki/doc/api/index.php?chapter=10_1" target="_blank">《代金券或立减优惠》</a>

读完以上知识基本就差不多了。



  



