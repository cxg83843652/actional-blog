package cn.actional.blog.controller.admin;

import cn.actional.blog.service.ActionLabelsService;
import cn.actional.blog.utils.PageQueryUtil;
import cn.actional.blog.utils.Result;
import cn.actional.blog.utils.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 13
 * @qq交流群 796794009
 * @email 2449207463@qq.com
 * @link http://13blog.site
 */
@Controller
@RequestMapping("/admin")
public class LabelController {

    @Resource
    private ActionLabelsService labelsService;

    @GetMapping("/labels")
    public String labelPage(HttpServletRequest request) {
        request.setAttribute("path", "labels");
        return "admin/label";
    }

    @GetMapping("/labels/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(labelsService.selectByPage(pageUtil));
    }


    @PostMapping("/labels/save")
    @ResponseBody
    public Result save(@RequestParam("labelName") String labelName) {
        if (StringUtils.isEmpty(labelName)) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (labelsService.insert(labelName) == 1) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("标签名称重复");
        }
    }

    @PostMapping("/labels/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (labelsService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("有关联数据请勿强行删除");
        }
    }


}
