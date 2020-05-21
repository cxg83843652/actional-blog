package cn.actional.blog.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    String uploadImageList(MultipartFile file);

    void deleteImage(String imageUrl);
}
