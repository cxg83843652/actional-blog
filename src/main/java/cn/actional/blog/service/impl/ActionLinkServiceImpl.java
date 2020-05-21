package cn.actional.blog.service.impl;

import cn.actional.blog.domain.ActionCategories;
import cn.actional.blog.domain.ActionLink;
import cn.actional.blog.utils.PageQueryUtil;
import cn.actional.blog.utils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import cn.actional.blog.mapper.ActionLinkMapper;
import cn.actional.blog.service.ActionLinkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  @auther  actional
 *  @create 2020-05-20
 *
 */
@Service
public class ActionLinkServiceImpl implements ActionLinkService{

    @Resource
    private ActionLinkMapper actionLinkMapper;

    @Override
    public List<ActionLink> selectAll() {
        return actionLinkMapper.selectAll();
    }

    @Override
    public Integer selectCount() {
        return actionLinkMapper.selectCount(new ActionLink());
    }

    @Override
    public PageResult selectByPage(PageQueryUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getLimit());
        List<ActionLink> linkList = actionLinkMapper.selectAll();
        PageInfo pageInfo = new PageInfo(linkList);
        return new PageResult(pageInfo.getTotal(),pageInfo.getPageSize(),pageInfo.getPages(),pageInfo.getPageNum(),pageInfo.getList());
    }


    @Transactional
    @Override
    public Boolean insert(ActionLink link) {
        return actionLinkMapper.insertSelective(link) == 1;
    }


    @Override
    public ActionLink selectByPrimaryKey(Integer id) {
        return actionLinkMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public Boolean update(ActionLink tempLink) {
        return actionLinkMapper.updateByPrimaryKeySelective(tempLink) == 1;
    }

    @Transactional
    @Override
    public Boolean deleteByPrimaryKey(Long linkId) {
        return actionLinkMapper.deleteByPrimaryKey(linkId) == 1;
    }


    @Transactional
    @Override
    public Boolean deleteBatch(Integer[] ids) {
        Boolean status = false;
        for (int i = 0; i < ids.length; i++) {
            status = deleteByPrimaryKey(ids[i].longValue());
            if (!status) {
                throw new RuntimeException("删除失败");
            }
        }
        return status;
    }

}
