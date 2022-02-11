package com.dlb.utils;

import com.dlb.config.QieMsgConfig;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

//导入可选配置类
// 导入对应SMS模块的client
// 导入要请求接口对应的request response类


//腾讯短信
@Component
@Data
@SuppressWarnings("all")
public class TencentMsg {
    @Autowired
    private QieMsgConfig qieMsgConfig;
    //秘钥
    private  String secretId=qieMsgConfig.getSecretId();
    private  String secretKey=qieMsgConfig.getSecretKey();
    /* 短信应用ID: 短信SdkAppId在 [应用管理列表] */
    private  String sdkAppId = qieMsgConfig.getSdkAppId();
    /* 短信签名内容 必须填写已审核通过的签名 */
    private  String signName = "一起走呗个人网";
    /* 国际/港澳台短信 SenderId: 国内短信填空，默认未开通 */
    private  String senderid = "";
    /* 短信号码扩展号: 默认未开通，如需开通请联系 [sms helper] */
    private String extendCode = "";
    /**
     * 1232428	普通短信
     * 大萝卜
     * 您正在申请手机注册，验证码为：{1}，{2}分钟内有效！
     * 已通过	2021-12-09 10:25:18
     * 1232303	普通短信
     * 大萝卜
     * 您正在修改注册手机号码，验证码为：{1}，5分钟有效，为保障帐户安全，请勿向任何人提供此验证码。
     * 已通过	2021-12-09 09:40:33
     * 1232003	普通短信
     * 大萝卜
     * 验证码为：{1}，您正在登录，若非本人操作，请勿泄露。
     */
    private  String templateId = "1232303";
    //初始化短信发送客户端
    public  SmsClient initClient(){
        //必要步骤：
        Credential cred = new Credential(secretId, secretKey);
        // 实例化一个http选项，可选，没有特殊需求可以跳过
        HttpProfile httpProfile = new HttpProfile();
        // 设置代理
        // httpProfile.setProxyHost("真实代理ip");
        // httpProfile.setProxyPort(真实代理端口);
        /* SDK默认使用POST方法。
         * 如果你一定要使用GET方法，可以在这里设置。GET方法无法处理一些较大的请求 */
        httpProfile.setReqMethod("POST");
        /* SDK有默认的超时时间，非必要请不要进行调整*/
        httpProfile.setConnTimeout(60);
        /* SDK会自动指定域名。通常是不需要特地指定域名的，但是如果你访问的是金融区的服务
         * 则必须手动指定域名，例如sms的上海金融区域名： sms.ap-shanghai-fsi.tencentcloudapi.com */
        httpProfile.setEndpoint("sms.tencentcloudapi.com");

        /* 非必要步骤:
         * 实例化一个客户端配置对象，可以指定超时时间等配置 */
        ClientProfile clientProfile = new ClientProfile();
        /* SDK默认用TC3-HMAC-SHA256进行签名
         * 非必要请不要修改这个字段 */
        clientProfile.setSignMethod("HmacSHA256");
        clientProfile.setHttpProfile(httpProfile);
        /* 实例化要请求产品(以sms为例)的client对象
         * 第二个参数是地域信息，可以直接填写字符串ap-guangzhou，或者引用预设的常量 */
        return new SmsClient(cred, "ap-guangzhou",clientProfile);
    }
    //使用短信登录
    public String sendLogin(String msgCode,String phoneNumber,String userInfo){
        SmsClient client = initClient();
        // 实例化一个请求对象
        SendSmsRequest req = new SendSmsRequest();
        //------组装短信内容
        req.setSmsSdkAppId(sdkAppId);//应用id
        req.setSignName(signName);//网站签名
        req.setSenderId(senderid);//国外短信
        req.setSessionContext(userInfo);//请求信息自定义，也可以为"xxx"
        req.setExtendCode(extendCode);//扩展号码
        req.setTemplateId(templateId);//申请的模板id
        String[] arr = {phoneNumber};
        req.setPhoneNumberSet(arr);//手机号，最大200个
        String[] code = {msgCode};
        req.setTemplateParamSet(code);//模板里的变量，按数组顺序放入
        try {
            //发送短信
            SendSmsResponse res = client.SendSms(req);
            //通过官网接口文档或跳转到response对象的定义处查看返回字段的定义
            System.out.println(res.getRequestId());
            //发送成功返回发送信息
            return SendSmsResponse.toJsonString(res);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public String getMsgCode(){
        Random random = new Random();
        String a= "";
        int j = random.nextInt(9);
        for (int i = 0; i < 6; i++) {
            a += String.valueOf(random.nextInt(9));
        }
        return a;
    }


    /**
     * 开放式发送短信
     * @param verifyInfo  模板里的变量: 验证码：code必须是数字类型的字符串 时间5分钟
     *     String[] templateParamSet = {"code","5"};
     * @param phoneNumberSet 手机号  前面要写+86 国内用
     *       String[] phoneNumberSet = {"+8617639563050"};
     * @param sessionContext
     *      用户的 session 内容: 不写就用xxx代替，server 会原样返回
     * @return  返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应
     * {"SendStatusSet":[
     *      {"SerialNo":"2640:246877675516390197684686305",
     *       "PhoneNumber":"+8617639563050",
     *       "Fee":1,
     *       "SessionContext":"xxx",
     *       "Code":"Ok",
     *       "Message":"send success",
     *       "IsoCode":"CN"}
     * ],
     * "RequestId":"d240c44b-a2e5-45ef-b3b5-00f23ac42b5f"}
     *
     *             d240c44b-a2e5-45ef-b3b5-00f23ac42b5f
     */
    public String sendMsg(String[] verifyInfo,String[] phoneNumberSet,String sessionContext){
        SmsClient client = initClient();
        // 实例化一个请求对象
        SendSmsRequest req = new SendSmsRequest();
        //------组装短信内容
        req.setSmsSdkAppId(sdkAppId);//应用id
        req.setSignName(signName);//网站签名
        req.setSenderId(senderid);//国外短信
        req.setSessionContext(sessionContext);//请求信息自定义，也可以为"xxx"
        req.setExtendCode(extendCode);//扩展号码
        req.setTemplateId(templateId);//申请的模板id
        req.setPhoneNumberSet(phoneNumberSet);//手机号，最大200个
        req.setTemplateParamSet(verifyInfo);//模板里的变量，按数组顺序放入
        try {
            //发送短信
            SendSmsResponse res = client.SendSms(req);
            //通过官网接口文档或跳转到response对象的定义处查看返回字段的定义
            System.out.println(res.getRequestId());
            //发送成功返回发送信息
            return SendSmsResponse.toJsonString(res);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 获取回执状态
     * @param limit 设置拉取最大条数，最多100条 Long类型 5L
     * @return {"PullSmsSendStatusSet":[],"RequestId":"65d63875-b902-45c2-81be-810f01ea0ee3"}
     */
    public String getReceipt(Long limit){
        SmsClient client = initClient();
        /* 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数*/
        PullSmsSendStatusRequest req = new PullSmsSendStatusRequest();
        req.setSmsSdkAppId(sdkAppId);//应用id
        req.setLimit(limit);
        /* 通过 client 对象调用 PullSmsSendStatus 方法发起请求。注意请求方法名与请求对象是对应的
         * 返回的 res 是一个 PullSmsSendStatusResponse 类的实例，与请求对象对应 */
        try {
            PullSmsSendStatusResponse res = client.PullSmsSendStatus(req);
            String s = PullSmsSendStatusResponse.toJsonString(res);
            // 输出json格式的字符串回包
            System.out.println(s);
            return s;
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 统计短信发送数据
     * @return {"SendStatusStatistics":{
     *          "FeeCount":1,"RequestCount":1,"RequestSuccessCount":1},
     *          "RequestId":"b5f388f0-bdf3-4c8c-8cc7-be8ca8bdda3f"}
     */
    public String statisticalSendData(){
        SmsClient client = initClient();
        /* 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数*/
        SendStatusStatisticsRequest req = new SendStatusStatisticsRequest();
        req.setSmsSdkAppId(sdkAppId);
        // 设置拉取最大条数，最多100条
        Long limit = 5L;
        req.setLimit(limit);
        /* 偏移量 注：目前固定设置为0 */
        Long offset = 0L;
        req.setOffset(offset);
        /* 开始时间，yyyymmddhh 需要拉取的起始时间，精确到小时 */
        String beginTime = "2021120908";
        req.setBeginTime(beginTime);
        /* 结束时间，yyyymmddhh 需要拉取的截止时间，精确到小时
         * 注：EndTime 必须大于 beginTime */
        String endTime = "2021120912";
        req.setEndTime(endTime);
        /* 通过 client 对象调用 SendStatusStatistics 方法发起请求。注意请求方法名与请求对象是对应的
         * 返回的 res 是一个 SendStatusStatisticsResponse 类的实例，与请求对象对应 */
        try {
            SendStatusStatisticsResponse res = client.SendStatusStatistics(req);
            String s = SendStatusStatisticsResponse.toJsonString(res);
            // 输出json格式的字符串回包
            System.out.println(s);
            return s;
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * //申请模板，需要企业认证类型账号！！！
     * @param templatename String templatename = "阿里云";
     * @param templatecontent 模板内容 "{1}为您的登录验证码，请于{2}分钟内填写";
     * @param smstype       短信类型：0表示普通短信, 1表示营销短信 建议：0
     * @param international 是否国际/港澳台短信：0：表示国内短信，1：表示国际/港澳台短信。建议：0
     * @param remark 模板备注：例如申请原因，使用场景等 "xxx"
     * @return json
     */
    public  String a(String templatename,
                           String templatecontent,
                           long smstype,
                           long international,
                           String remark){
        SmsClient client = initClient();
        /* 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数*/
        AddSmsTemplateRequest req = new AddSmsTemplateRequest();
        req.setTemplateName(templatename);
        req.setTemplateContent(templatecontent);
        req.setSmsType(smstype);
        req.setInternational(international);
        req.setRemark(remark);
        try {
            AddSmsTemplateResponse res = client.AddSmsTemplate(req);
            // 可以取出单个值，您可以通过官网接口文档或跳转到 response 对象的定义处查看返回字段的定义
            System.out.println(res.getRequestId());
            String s = AddSmsTemplateResponse.toJsonString(res);
            // 输出 JSON 格式的字符串回包
            System.out.println(s);
            return s;
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return null;
    }
}
