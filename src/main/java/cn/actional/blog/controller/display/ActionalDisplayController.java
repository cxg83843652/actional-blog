package cn.actional.blog.controller.display;

import cn.actional.blog.domain.ActionLink;
import cn.actional.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther actional
 * @create 2020-05-18
 */
@Controller
public class ActionalDisplayController {

    private static final String THEME = "pure";

    @Autowired
    private ActionCategoriesService categoriesService;

    @Autowired
    private ActionLabelsService labelsService;

    @Autowired
    private ArchiveService archiveService;

    @Autowired
    private ActionArticlesService articlesService;

    @Autowired
    private ActionLinkService linkService;

    @Autowired
    private ActionConfigService configService;


    public String page(HttpServletRequest request) {
        return "blog/" + THEME + "/index";
    }

    /**
     * 设置所有页面需要的通用参数
     */
    public String common(HttpServletRequest request) {
        request.setAttribute("categoryByGroup",categoriesService.selectByGroup());
        request.setAttribute("labelList",labelsService.selectByGroup());
        request.setAttribute("archiveList",archiveService.getArchive());
        request.setAttribute("articleLatest",articlesService.getLatest());
        request.setAttribute("articleTotal",articlesService.selectTotal());
        request.setAttribute("config",configService.selectAll());
        return this.page(request);
    }

    @GetMapping(value = {"/","index","index.html"})
    public String index(HttpServletRequest request) {
        request.setAttribute("page","blog/" + THEME + "/main");
        request.setAttribute("content","main-fragment");
        return this.index(request,1,10);
    }


    @GetMapping(value = {"/{currentPage}","index/{currentPage}","index.html/{currentPage}"} )
    public String index(HttpServletRequest request,Integer currentPage,
          @RequestParam(name = "pageSize",defaultValue = "10") Integer PageSize
    ) {
        request.setAttribute("articleByPage",articlesService.getArticlesByPage(currentPage,PageSize));
        return this.common(request);
    }


    @GetMapping("archives")
    public String archives(HttpServletRequest request) {
        request.setAttribute("page","blog/" + THEME + "/archives");
        request.setAttribute("content","archives-fragment");
        request.setAttribute("archiveMap",this.archiveService.getArchiveGroupByYear());
        return this.common(request);
    }

    @GetMapping("categories")
    public String categories(HttpServletRequest request,
                             Long categoryId,Integer currentPage) {
        request.setAttribute("page","blog/" + THEME + "/categories");
        request.setAttribute("content","categories-fragment");
        request.setAttribute("categoryMap",this.categoriesService.getCategoryMap(categoryId,currentPage));
        return this.common(request);
    }

    @GetMapping("category/{categoryId}")
    public String category(HttpServletRequest request, @PathVariable(required = true) Long categoryId) {
        return categories(request,categoryId,1);
    }


    @GetMapping("category/{categoryId}/{currentPage}")
    public String category(HttpServletRequest request,
                           @PathVariable(required = true) Long categoryId,
                           @PathVariable(required = true) Integer currentPage
    ) {
        return categories(request,categoryId,currentPage);
    }



    @GetMapping("labels")
    public String labels(HttpServletRequest request,Long labelId,Integer currentPage) {
        request.setAttribute("page","blog/" + THEME + "/labels");
        request.setAttribute("content","labels-fragment");
        request.setAttribute("labelMap",this.labelsService.getLabelMap(labelId,currentPage));
        return this.common(request);
    }


    @GetMapping("label/{labelId}")
    public String label(HttpServletRequest request,
                      @PathVariable(required = true)  Long labelId
    ) {
        return this.labels(request,labelId,null);
    }

    @GetMapping("label/{labelId}/{currentPage}")
    public String label(HttpServletRequest request,
                      @PathVariable(required = true)  Long labelId,
                      @PathVariable(required = true)  Integer currentPage
    ) {
        return this.labels(request,labelId,currentPage);
    }

    @GetMapping("links")
    public String links(HttpServletRequest request) {
        request.setAttribute("page","blog/" + THEME + "/links");
        request.setAttribute("content","links-fragment");
        request.setAttribute("linkList",linkService.selectAll());
        return this.common(request);
    }

    @GetMapping("about")
    public String about(HttpServletRequest request) {
        request.setAttribute("page","blog/" + THEME + "/about");
        request.setAttribute("content","about-fragment");
        return this.common(request);
    }



    @GetMapping("article/{articleId}")
    public String article(HttpServletRequest request, @PathVariable Long articleId) {

        request.setAttribute("page","blog/" + THEME + "/detail");
        request.setAttribute("content","detail-fragment");
        request.setAttribute("articleDetail",this.articlesService.selectByArticleId(articleId));
        //上一篇的id
        request.setAttribute("pre",this.articlesService.getPreArticleId(articleId));
        //下一篇的id
        request.setAttribute("next",this.articlesService.getNextArticleId(articleId));
        return this.common(request);
    }



    @GetMapping("archive/{date}")
    public String archive(HttpServletRequest request, @PathVariable String date) {
        request.setAttribute("page","blog/" + THEME + "/archives");
        request.setAttribute("content","archives-fragment");
        request.setAttribute("archiveMap",this.articlesService.getArticlesByDate(date));
        System.out.println("*****" + this.articlesService.getArticlesByDate(date));
        return this.common(request);
    }
}
