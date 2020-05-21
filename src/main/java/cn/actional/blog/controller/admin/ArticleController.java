package cn.actional.blog.controller.admin;

import cn.actional.blog.common.dto.ArticleExtension;
import cn.actional.blog.domain.ActionArticles;
import cn.actional.blog.domain.ActionArticlesParam;
import cn.actional.blog.domain.ActionCategories;
import cn.actional.blog.domain.ActionLabels;
import cn.actional.blog.service.ActionArticlesService;
import cn.actional.blog.service.ActionCategoriesService;
import cn.actional.blog.utils.PageQueryUtil;
import cn.actional.blog.utils.Result;
import cn.actional.blog.utils.ResultGenerator;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 13
 * @qq交流群 796794009
 * @email 2449207463@qq.com
 * @link http://13blog.site
 */
@Controller
@RequestMapping("/admin")
public class ArticleController {

    @Resource
    private ActionArticlesService articlesService;
    @Resource
    private ActionCategoriesService categoryService;

    @GetMapping("/blogs/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(articlesService.selectArticleByPage(pageUtil));
    }


    @GetMapping("/blogs")
    public String list(HttpServletRequest request) {
        request.setAttribute("path", "blogs");
        return "admin/blog";
    }

    @GetMapping("/blogs/edit")
    public String edit(HttpServletRequest request) {
        request.setAttribute("path", "edit");
        request.setAttribute("categories", categoryService.selectAll());
        return "admin/edit";
    }

    @GetMapping("/blogs/edit/{blogId}")
    public String edit(HttpServletRequest request, @PathVariable("blogId") Long blogId) {
        request.setAttribute("path", "edit");
        ActionArticles articles1 = articlesService.selectByArticleId(blogId);
        ArticleExtension articles = JSONObject.parseObject(JSONObject.toJSONString(articles1), ArticleExtension.class);
        if (articles == null) {
            return "error/error_400";
        }
        request.setAttribute("articles", articles);
        request.setAttribute("categories", categoryService.selectAll());
        return "admin/edit";
    }

    @PostMapping("/blogs/save")
    @ResponseBody
    public Result save(@RequestParam("blogTitle") String blogTitle,
                       @RequestParam(name = "blogCategoryId",required = true) Integer blogCategoryId,
                       @RequestParam("blogTags") String blogTags,

                       @RequestParam("blogContent") String blogContent,
                       @RequestParam(value = "recommend", required = true) Boolean recommend,
                       @RequestParam(value = "articleFlag", required = true) String articleFlag,
                       @RequestParam("blogStatus") Boolean blogStatus) {
        if (StringUtils.isEmpty(blogTitle)) {
            return ResultGenerator.genFailResult("请输入文章标题");
        }
        if (blogTitle.trim().length() > 150) {
            return ResultGenerator.genFailResult("标题过长");
        }
        if (StringUtils.isEmpty(blogTags)) {
            return ResultGenerator.genFailResult("请输入文章标签");
        }
        if (blogTags.trim().length() > 150) {
            return ResultGenerator.genFailResult("标签过长");
        }
        if (StringUtils.isEmpty(blogContent)) {
            return ResultGenerator.genFailResult("请输入文章内容");
        }
        if (blogTags.trim().length() > 100000) {
            return ResultGenerator.genFailResult("文章内容过长");
        }

        ActionArticles articles = new ActionArticles();
        articles.setArticleTitle(blogTitle);
        articles.setCategory(new ActionCategories(blogCategoryId.longValue(),null));
        String[] labels = blogTags.split(",");
        if (labels.length > 6) {
            return ResultGenerator.genFailResult("标签数量限制为6");
        }
        List<ActionLabels> labelList = new ArrayList<>();
        for (int i = 0; i < labels.length; i++) {
            ActionLabels label = new ActionLabels(null, labels[i]);
            labelList.add(label);
        }
        articles.setLabelList(labelList);
        ActionArticlesParam articlesParam = new ActionArticlesParam();
        articlesParam.setArticleContent(blogContent);
        articles.setArticlesParam(articlesParam);
        articles.setArticleStatus(blogStatus);
        articles.setRecommend(recommend);
        articles.setArticleFlag(articleFlag);
        String saveBlogResult = articlesService.insertArticle(articles);
        if ("success".equals(saveBlogResult)) {
            return ResultGenerator.genSuccessResult("添加成功");
        } else {
            return ResultGenerator.genFailResult(saveBlogResult);
        }
    }

    @PostMapping("/blogs/update")
    @ResponseBody
    public Result update(@RequestParam("blogId") Long blogId,
                         @RequestParam("blogTitle") String blogTitle,
                         @RequestParam("blogCategoryId") Integer blogCategoryId,
                         @RequestParam("blogTags") String blogTags,
                         @RequestParam("blogContent") String blogContent,
                         @RequestParam("blogStatus") Boolean blogStatus,
                         @RequestParam(value = "recommend", required = true) Boolean recommend,
                         @RequestParam(value = "articleFlag", required = true) String articleFlag
    ) {
        System.out.println("111111111");
        if (StringUtils.isEmpty(blogTitle)) {
            return ResultGenerator.genFailResult("请输入文章标题");
        }
        if (blogTitle.trim().length() > 150) {
            return ResultGenerator.genFailResult("标题过长");
        }
        if (StringUtils.isEmpty(blogTags)) {
            return ResultGenerator.genFailResult("请输入文章标签");
        }
        if (blogTags.trim().length() > 150) {
            return ResultGenerator.genFailResult("标签过长");
        }
        if (StringUtils.isEmpty(blogContent)) {
            return ResultGenerator.genFailResult("请输入文章内容");
        }
        if (blogTags.trim().length() > 100000) {
            return ResultGenerator.genFailResult("文章内容过长");
        }
        ActionArticles articles = new ActionArticles();
        articles.setArticleId(blogId);
        articles.setArticleTitle(blogTitle);
        articles.setCategory(new ActionCategories(blogCategoryId.longValue(),null));
        String[] labels = blogTags.split(",");
        if (labels.length > 6) {
            return ResultGenerator.genFailResult("标签数量限制为6");
        }
        List<ActionLabels> labelList = new ArrayList<>();
        for (int i = 0; i < labels.length; i++) {
            ActionLabels label = new ActionLabels(null, labels[i]);
            labelList.add(label);
        }
        articles.setLabelList(labelList);
        ActionArticlesParam articlesParam = new ActionArticlesParam();
        articlesParam.setArticleContent(blogContent);
        articles.setArticlesParam(articlesParam);
        articles.setArticleStatus(blogStatus);
        articles.setRecommend(recommend);
        articles.setArticleFlag(articleFlag);
        articles.setArticlesParam(articlesParam);
        articles.setArticleStatus(blogStatus);
        String updateBlogResult = articlesService.updateArticle(articles);
        if ("success".equals(updateBlogResult)) {
            return ResultGenerator.genSuccessResult("修改成功");
        } else {
            return ResultGenerator.genFailResult(updateBlogResult);
        }
    }



    @PostMapping("/blogs/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (articlesService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }
}
