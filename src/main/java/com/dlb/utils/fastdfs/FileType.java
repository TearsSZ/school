package com.dlb.utils.fastdfs;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 文件类型
 */
@Component
public class FileType {
    /**
     * 图片类型
     */
    private static final List<String> TYPE_IMAGE = new ArrayList<>();
    /**
     * 文档类型
     */
    private static final List<String> TYPE_DOC = new ArrayList<>();
    /**
     * 音频类型
     */
    private static final List<String> TYPE_VIDEO = new ArrayList<>();
    /**
     * 压缩文件类型
     */
    private static final List<String> TYPE_COMPRESS = new ArrayList<>();
    static {
        TYPE_IMAGE.add("png");
        TYPE_IMAGE.add("gif");
        TYPE_IMAGE.add("jpeg");
        TYPE_IMAGE.add("jpg");

        TYPE_DOC.add("pdf");
        TYPE_DOC.add("ppt");
        TYPE_DOC.add("xls");
        TYPE_DOC.add("xlsx");
        TYPE_DOC.add("pptx");
        TYPE_DOC.add("doc");
        TYPE_DOC.add("docx");
        TYPE_DOC.add("md");

        TYPE_VIDEO.add("mp3");
        TYPE_VIDEO.add("mp4");
        TYPE_VIDEO.add("flv");
        TYPE_VIDEO.add("avi");

        TYPE_COMPRESS.add("zip");
        TYPE_COMPRESS.add("rar");
    }



    /**
     * 判断类型方法
     * @param checkTypes 可以设置为上面的集合的一种
     * @param filename 上传的文件名
     * @return
     */
    private static boolean checkType(List<String> checkTypes, String filename){
        return checkTypes.contains(getFilenameSuffix(filename));
    }

    /**
     * 截取文件的后缀，以'.'分割
     * @param filename   yingdaomayi.mp4
     * @return  mp4
     */
    public static String getFilenameSuffix(String filename) {
        String suffix = null;
        if (StringUtils.isNotBlank(filename) && filename.contains(FastDfsClient.POINT)) {
            suffix = filename.substring(filename.lastIndexOf(FastDfsClient.POINT) + 1).toLowerCase();
        }
        return suffix;
    }
}
