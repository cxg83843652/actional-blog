package cn.actional.blog.controller.display;


import cn.actional.blog.allenum.StatusEnum;
import cn.actional.blog.common.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorPageController implements ErrorController {

    @Autowired
    private ActionalDisplayController blogController;


    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String error(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == 200) {
            return this.blogController.page(request);
        }
        PageVO pageVO = StatusEnum.valueOf("STATUS_" + statusCode).statusInterface();

        request.setAttribute("page","error/"  + pageVO.getPath());
        request.setAttribute("content","error_" + pageVO.getFragment()  + "-fragment");

        return this.blogController.page(request);
    }



}
