package cn.actional.blog.service.impl;

import cn.actional.blog.config.COSTencentCloudProperties;
import cn.actional.blog.service.UploadService;
import cn.actional.blog.utils.COSTencentCloudUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;


@Service
@EnableConfigurationProperties(COSTencentCloudProperties.class)
public class UploadServiceImpl implements UploadService {

    private static final List<String> CONTENT_TYPES = Arrays.asList("image/gif", "image/jpeg", "image/png");
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    @Autowired
    private COSTencentCloudProperties cloudProperties;



    @Override
    public String uploadImageList(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
        if (!CONTENT_TYPES.contains(contentType)) {
            LOGGER.info("文件类型不合法:{}", originalFilename);
            return null;
        }
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                LOGGER.info("文件内容不合法:{}", originalFilename);
                return null;
            }
            String ext = StringUtils.substringAfterLast(originalFilename, ".");


            String newFileName = UUID.randomUUID() + "." + ext;
            Calendar cal = Calendar.getInstance();    //获取当前时间
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DATE);
            // 1 初始化用户身份信息(secretId, secretKey)



            //创建临时文件
            File localFile = File.createTempFile("temp", null);

            file.transferTo(localFile);

            // 指定要上传到 COS 上的路径
            String key = "/" + this.cloudProperties.getPrefix() + "/" + year + "/" + month + "/" + day + "/" + newFileName;

            PutObjectRequest putObjectRequest = new PutObjectRequest(this.cloudProperties.getBucketName(), key, localFile);
            PutObjectResult putObjectResult = COSTencentCloudUtils.getConsclient(
                    this.cloudProperties.getSecretid(),this.cloudProperties.getSecretKey(),this.cloudProperties.getBucket()).putObject(putObjectRequest);

            return this.cloudProperties.getPath() + putObjectRequest.getKey();

        } catch (IOException e) {
            LOGGER.info("服务器内部错误:{}", originalFilename);
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void deleteImage(String imageUrl) {
        if (StringUtils.isNoneBlank(imageUrl)) {
            //取前缀后面一段存储路径
            String imagePath = imageUrl.split(this.cloudProperties.getPrefix())[1];
            COSClient consclient = COSTencentCloudUtils.getConsclient(this.cloudProperties.getSecretid(), this.cloudProperties.getSecretKey(), this.cloudProperties.getBucket());
            consclient.deleteObject(this.cloudProperties.getBucketName(),this.cloudProperties.getPrefix() + imagePath);
        }
    }
}
