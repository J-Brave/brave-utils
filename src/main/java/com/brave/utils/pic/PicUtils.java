package com.brave.utils.pic;

import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author jbrave
 */
public class PicUtils {

    private static final Logger logger = LoggerFactory.getLogger(PicUtils.class);

    private static final Integer ZERO = 0;
    private static final Integer ONE_ZERO_TWO_FOUR = 1024;
    private static final Integer NINE_ZERO_ZERO = 900;
    private static final Integer THREE_TWO_SEVEN_FIVE = 3275;
    private static final Integer TWO_ZERO_FOUR_SEVEN = 2047;
    private static final Double ZERO_EIGHT_FIVE = 0.85;
    private static final Double ZERO_SIX = 0.6;
    private static final Double ZERO_FOUR_FOUR = 0.44;
    private static final Double ZERO_FOUR = 0.4;

    /**
     * 图片不失真压缩
     * @param imageBytes
     * @param desFileSize 压缩目标大小
     * @return
     */
    public static byte[] compressPicForScale(byte[] imageBytes, long desFileSize) {
        if (imageBytes == null || imageBytes.length <= ZERO || imageBytes.length < desFileSize * ONE_ZERO_TWO_FOUR) {
            return imageBytes;
        }
        long srcSize = imageBytes.length;
        double accuracy = getAccuracy(srcSize / ONE_ZERO_TWO_FOUR);
        try {
            while (imageBytes.length > desFileSize * ONE_ZERO_TWO_FOUR) {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imageBytes.length);
                Thumbnails.of(inputStream)
                        .scale(accuracy)
                        .outputQuality(accuracy)
                        .toOutputStream(outputStream);
                imageBytes = outputStream.toByteArray();
            }
            logger.info("图片原大小={}kb | 压缩后大小={}kb",
                    srcSize / ONE_ZERO_TWO_FOUR, imageBytes.length / ONE_ZERO_TWO_FOUR);
        } catch (Exception e) {
            logger.error("【图片压缩】msg=图片压缩失败!", e);
        }
        return imageBytes;
    }

    /**
     * 自动调节精度(经验数值)
     *
     * @param size 源图片大小
     * @return 图片压缩质量比
     */
    private static double getAccuracy(long size) {
        double accuracy;
        if (size < NINE_ZERO_ZERO) {
            accuracy = ZERO_EIGHT_FIVE;
        } else if (size < TWO_ZERO_FOUR_SEVEN) {
            accuracy = ZERO_SIX;
        } else if (size < THREE_TWO_SEVEN_FIVE) {
            accuracy = ZERO_FOUR_FOUR;
        } else {
            accuracy = ZERO_FOUR;
        }
        return accuracy;
    }

    /**
     * 获取图片Base64格式
     * @param file
     * @return
     */
    public static String getBase64FromFile(MultipartFile file) {
        try {
            if (file == null) {
                return null;
            }
            byte[] bytes = file.getBytes();
            String contentType = file.getContentType();
            String encode = new BASE64Encoder().encode(bytes);
            //组装Base64格式的图片数据
            StringBuilder base64 = new StringBuilder();
            if ("image/jpeg".equals(contentType)) {
                base64.append("data:image/jpeg;base64,");
            } else if ("image/png".equals(contentType)) {
                base64.append("data:image/png;base64,");
            } else if ("image/gif".equals(contentType)) {
                base64.append("data:image/gif;base64,");
            } else if ("image/x-icon".equals(contentType)) {
                base64.append("data:image/x-icon;base64,");
            } else {
                return null;
            }
            base64.append(encode);
            return base64.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getBase64FromOssResponse(byte[] bytes) {
        //获得文件类型
        String contentType = "image/jpeg";
        //对字节进行Base64编码
        String encode = new BASE64Encoder().encode(bytes);
        //组装Base64格式的图片数据
        StringBuilder base64 = new StringBuilder();
        if ("image/jpeg".equals(contentType)) {
            base64.append("data:image/jpeg;base64,");
        } else if ("image/png".equals(contentType)) {
            base64.append("data:image/png;base64,");
        } else if ("image/gif".equals(contentType)) {
            base64.append("data:image/gif;base64,");
        } else if ("image/x-icon".equals(contentType)) {
            base64.append("data:image/x-icon;base64,");
        } else {
            return null;
        }
        base64.append(encode);
        return base64.toString();
    }

    public static String getBase64FromOssResponse(byte[] bytes, String contentType) {
        //对字节进行Base64编码
        String encode = new BASE64Encoder().encode(bytes);
        //组装Base64格式的图片数据
        StringBuilder base64 = new StringBuilder();
        if ("image/jpeg".equals(contentType)) {
            base64.append("data:image/jpeg;base64,");
        } else if ("data:image/png".equals(contentType)) {
            base64.append("data:image/png;base64,");
        } else if ("data:image/gif".equals(contentType)) {
            base64.append("data:image/gif;base64,");
        } else if ("data:image/x-icon".equals(contentType)) {
            base64.append("data:image/x-icon;base64,");
        } else {
            return null;
        }
        base64.append(encode);
        return base64.toString();
    }

}
