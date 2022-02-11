package com.dlb.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.dlb.config.OSSConfig;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

//图片上传
@Data
@Component
public class OSSClientUtil {

    @Autowired
    private OSSConfig ossConfig;
    private  String endpoint = ossConfig.getEndpoint();
    private String accessKeyId = ossConfig.getAccessKeyId();
    private String accessKeySecret = ossConfig.getAccessKeySecret();
    private String bucketName = ossConfig.getBucketName();
    private String fileDir = ossConfig.getFileDir();

    private OSS ossClient = null;

    public OSSClientUtil(){
        ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 初始化
     */
    public void init() {
        ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 销毁
     */
    public void destory() {
        ossClient.shutdown();
    }
    /*
    * 文件源
    * 文件名唯一否则上传失效！例如：asd.jpg
    * */
    public String uploadOss(MultipartFile file, String fileName) {
        //输入流
        java.io.InputStream is = null;
        try{
            is = file.getInputStream();
            //文件类型
            ObjectMetadata metadata = getMetadata(is, fileName);
            //上传
            ossClient.putObject(bucketName,fileName,is,metadata);

            String url = getUrl(fileDir + "");
            System.err.println("url:****" + url);
            //拼接路径
            String h = "https://"+bucketName+"."+endpoint+"/"+fileName;
            System.err.println("拼接：%%%%%%"+h);
            destory();
            return h;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 设置过期时间  设置上传的目录key
     */
    public String getUrl(String path) {
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        // 生成URL
        java.net.URL url = ossClient.generatePresignedUrl(bucketName, path, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }
    /**
     * 要上传的文件类型
     * ---->需要判断文件后缀名
     */
    public ObjectMetadata getMetadata(java.io.InputStream fis, String fileName) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        //这里需要判断文件后缀名
        metadata.setContentType(getContentType(fileName));
        metadata.setContentLength(fis.available());
        metadata.setCacheControl("no-cache");
        metadata.setHeader("Pragma", "no-cache");
        metadata.setContentDisposition("inline;filename=" + fileName);
        return metadata;
    }
    /**
    * 判断要上传文件的后缀名
    */
    public static String getContentType(String fileName) {
        String filenameExtension = fileName.substring(fileName.lastIndexOf("."));
        if (filenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (filenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase(".jpeg") ||
                filenameExtension.equalsIgnoreCase(".jpg") ||
                filenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpeg";
        }
        if (filenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (filenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (filenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (filenameExtension.equalsIgnoreCase(".pptx") ||
                filenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (filenameExtension.equalsIgnoreCase(".docx") ||
                filenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (filenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }
    /*
    //       // 将图片缩放为固定宽高100 px后，再旋转90°。
//        //String style = "image/resize,m_fixed,w_100,h_100/rotate,90";
//        // 指定签名URL过期时间为10分钟。
//        Date expiration = new Date(new Date().getTime() + 1000 * 60 * 10 );
//        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(
//                bucketName, objectName, HttpMethod.GET);
//        req.setExpiration(expiration);
//       // req.setProcess(style);
//        URL signedUrl = ossClient.generatePresignedUrl(req);
//        System.out.println(signedUrl);

        // 关闭OSSClient。
        ossClient.shutdown();
    * */

}
