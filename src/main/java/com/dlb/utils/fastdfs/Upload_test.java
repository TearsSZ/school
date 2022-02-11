package com.dlb.utils.fastdfs;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.IOException;

public class Upload_test {
    private static final String CONFIG = Thread.currentThread()
            .getContextClassLoader().getResource("").getPath()+"";
    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageServer storageServer = null;
    private StorageClient1 storageClient = null;

    public static void main(String[] args) throws IOException, MyException {
        //upload();
        download();
    }
    //上传
    public static void upload() throws IOException, MyException {
        //上传文件大致分为三步：1.加载配置文件。2.创建上传服务的对象。3.组装上传信息进行上传
        //加载配置文件
        ClientGlobal.init("fastdfs-client.properties");
        //上传文件需要获得存储对象！需要一下三步
        //1. 创建管理端对象
        TrackerClient trackerClient = new TrackerClient();
        //2. 通过管理端对象获取连接
        TrackerServer connection =trackerClient.getTrackerServer();
        //3. 创建存储端对象
        StorageClient1 storageClient = new StorageClient1(connection, null);

        //创建文件属性信息对象数组
        NameValuePair[] meta_list = new NameValuePair[3];
        meta_list[0] = new NameValuePair("fileName","lp");
        meta_list[1] = new NameValuePair("ExtName","mp4");
        meta_list[2] = new NameValuePair("auth","dlb");
        /**
         * 参数1：数据源
         * 参数2：文件类型后缀
         * 参数3：文件属性信息
         */
        String path = storageClient.upload_file1("C:\\Users\\tears\\Desktop\\樱岛麻衣.mp4", "mp4", meta_list);
        System.out.println("返回地址" + path);
        //返回地址group1/M00/00/00/L2WlYGHKtgqABUFbADHmse0OfXM130.mp4
        //upload = group1/M00/00/00/L2WlYGHX32CAT_c9ADHmse0OfXM266.mp4
    }
    public static void download() throws IOException, MyException {
        //初始化配置文件
        ClientGlobal.init("config.properties");
        //tracker 客户端
        TrackerClient trackerClient = new TrackerClient();
        //获取trackerServer
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        //创建StorageClient 对象
        StorageClient storageClient = new StorageClient(trackerServer);
        //测试文件下载
        storageClient.download_file("group1", "M00/00/00/L2WlYGHKtgqABUFbADHmse0OfXM130.mp4","f:/a.mp4");
    }

}
