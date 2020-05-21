package cn.actional.blog.controller.admin;

import cn.actional.blog.service.UploadService;
import cn.actional.blog.utils.Result;
import cn.actional.blog.utils.ResultGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;


@Controller
@RequestMapping("/admin")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     *  文章图片 将图片上传到腾讯cos
     * @param file
     * @return
     */
    @PostMapping("uploadImage")
    @ResponseBody
    public Result uploadImage(
            HttpServletRequest request,
            @RequestParam("file") MultipartFile file
    ) {
        String url = this.uploadService.uploadImageList(file);
        if (StringUtils.isBlank(url)) {
            return ResultGenerator.genFailResult("文件上传失败");
        }
        Result resultSuccess = ResultGenerator.genSuccessResult();
        resultSuccess.setData(url);
        return resultSuccess;
    }

    @GetMapping("deleteImage")
    public ResponseEntity<Void> deleteImage(
            @RequestParam("imageUrl") String imageUrl
    ) {
        this.uploadService.deleteImage(imageUrl);
        return ResponseEntity.ok(null);
    }

}
