package com.dlb.utils;


import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.models.Config;

//阿里短信验证码
public class MessagesUtils {
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String endpoint = "dysmsapi.aliyuncs.com";
    //此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    private static String accessKeyId = "";
    private static String accessKeySecret = "";
    /**
     * 使用AK&SK初始化账号Client
     */
    public static void init(String keyId,String keySecret){
        accessKeyId = keyId;
        accessKeySecret = keySecret;
    }


    public static com.aliyun.dysmsapi20170525.Client createClient() throws Exception {
        if ("".equals(accessKeyId)){
            accessKeyId="";
            accessKeySecret="";
        }
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = endpoint;
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public static void sendMsg(String phone, String code, String templateCode)  {
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                //必填 接收短信的手机号
                .setPhoneNumbers(phone)
                //必填 短信签名名称【阿里云】、【滴滴】
                .setSignName("大萝卜")//
                //短信模板ID  模版CODE
                .setTemplateCode(templateCode)
                ////可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
                .setTemplateParam("{\"code\":\""+code+"\"}")//短信模板变量对应的实际值
                //您的验证码为：${code}，该验证码 5 分钟内有效，请勿泄漏于他人。
                //短信模板变量对应的实际值，JSON格式。
                // 支持传入多个参数，示例：{"name":"张三","number":"15038****76"}。
                //一般不用
                .setSmsUpExtendCode("哦哦哦")//上行短信扩展码
                //上行短信，指发送给通信服务提供商的短信，用于定制某种服务、完成查询，或是办理某种业务等，
                // 需要收费的，按运营商普通短信资费进行扣费。
                //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
                .setOutId("哎哎哎");//外部流水扩展字段  示例:123
        try{
            //发送
            Client client = createClient();
            SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
            AddShortUrlRequest request = new AddShortUrlRequest();
            System.out.println(request.sourceUrl);
            AddShortUrlResponse response = new AddShortUrlResponse();
            System.out.println(response.body);
            System.out.println(response.headers);
        }catch (Exception e) {
            System.err.println("发送失败！！！");
            e.printStackTrace();
        }
    }
    public static void main(String[] args_) throws Exception {
        sendMsg("121241","1234","asfd");
        java.util.List<String> args = java.util.Arrays.asList(args_);
        QuerySendDetailsRequest query = new QuerySendDetailsRequest()
                .setPhoneNumber("17639563050")
                .setBizId("211201")
                .setSendDate("20211201")
                .setPageSize(5L)
                .setCurrentPage(1L);
        // 复制代码运行请自行打印 API 的返回值
        Client client = createClient();
        client.querySendDetails(query);
        System.out.println(query.bizId);
        System.out.println(query.toString());
        System.out.println(query.toMap());
        System.out.println(query.sendDate);

    }
    //查看短信发送记录和发送状态
//    public static boolean sendReturn(String phone){
//        //导入1.1的包
//        //设置超时时间
//        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
//        try {
//            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, endpoint);
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
//        IAcsClient acsClient = new DefaultAcsClient(profile);
//        QuerySendDetailsRequest query = new QuerySendDetailsRequest();
//        query.setPhoneNumber(phone);
//        //可选-调用发送短信接口时返回的BizId
//        query.setBizId("1234567^8901234");
//        //必填-短信发送的日期 支持30天内记录查询（可查其中一天的发送数据），格式yyyyMMdd
//        query.setSendDate("20170513");
//        //必填-页大小
//        query.setPageSize(10L);
//        //必填-当前页码从1开始计数
//        query.setCurrentPage(1L);
//                //接收短信的手机号码。
//                //.setPhoneNumber(phone)
//                ///可选-调用发送短信接口时返回的BizId
//                //.setBizId("1987593")
//                //必填-短信发送的日期 支持30天内记录查询（可查其中一天的发送数据），格式yyyyMMdd
//                //.setSendDate("20211201")
//                //必填-分页查看发送记录，指定每页显示的短信记录数量。//取值范围为1~50。
//                //.setPageSize(15L)
//                //必填-当前页码从1开始计数
//                //.setCurrentPage(5L);
//        QuerySendDetailsResponse querySendDetailsResponse = null;
//        try {
//            querySendDetailsResponse = acsClient.getAcsResponse(query);
//            System.out.println(querySendDetailsResponse.getCode());
//            if(querySendDetailsResponse.getCode() != null && querySendDetailsResponse.getCode().equals("OK")){
//                //代表请求成功
//                return true;
//            }
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    //        {                                                       //     返回OK才正确
//            "RequestId": "7B443892-1CC6-522D-8EC2-8D51639945D9",//请求      id
//            "Message": "smsUpExtendCode must be numeric",       //状态码描述 OK
//            "Code": "isv.INVALID_PARAMETERS"                    //请求状态码 OK
//            "BizId": "900619746936498440^0                      //回执      id
//        }
}
