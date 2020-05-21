package cn.actional.blog.utils;


import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.region.Region;

public class COSTencentCloudUtils {



    public static COSClient getConsclient(String secretid,String secretKey,String bucket) {

        BasicCOSCredentials cosCredentials = new BasicCOSCredentials(secretid, secretKey);

        // 2 设置bucket的区域
        ClientConfig clientConfig = new ClientConfig(new Region(bucket));

        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cosCredentials, clientConfig);

        return cosclient;
    }
}
