package cn.actional.blog.test;

import cn.actional.ActionalBlogApplication;
import cn.actional.blog.domain.ActionLabels;
import cn.actional.blog.mapper.ActionCategoriesMapper;
import cn.actional.blog.mapper.ActionLabelsMapper;
import cn.actional.blog.service.ActionArticlesService;
import cn.actional.blog.service.ActionConfigService;
import cn.actional.blog.service.ActionLabelsService;
import cn.actional.blog.service.ArchiveService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther actional
 * @create 2020-05-18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActionalBlogApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Rollback(true)
public class TestMapper {

    @Autowired
    private ActionLabelsMapper labelsMapper;

    @Autowired
    private ActionLabelsService labelsService;


    @Autowired
    private ActionArticlesService articlesService;

    @Autowired
    private ArchiveService archiveService;

    @Autowired
    private ActionCategoriesMapper  categoryMapper;

    @Autowired
    private ActionConfigService configService;



    @Test
    public void test1() {
        System.out.println(labelsMapper.selectByGroup());
    }

    @Test
    public void test2() {
        System.out.println(archiveService.getArchive());
    }


    @Test
    public void test3() {
        articlesService.getLatest().forEach(System.out::println);
    }

    @Test
    public void test4() {
        archiveService.getArchive().forEach(System.out::println);
    }

    @Test
    public void test5() {

        System.out.println(labelsService.getLabelMap(1L, 1));
    }

    @Test
    public void test6() {

       /* Map<String, String> stringStringMap = configService.selectAll();
        System.out.println(stringStringMap);*/
        ActionLabels actionLabels = new ActionLabels(null, "122222111");
        int insert = labelsMapper.insert(actionLabels);
        System.out.println(actionLabels.getLabelId());
        System.out.println(insert);
    }

    @Test
    public void test7() {
        System.out.println(configService.selectAll());
    }


}
