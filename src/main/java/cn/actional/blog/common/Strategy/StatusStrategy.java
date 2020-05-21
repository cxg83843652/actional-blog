package cn.actional.blog.common.Strategy;


import cn.actional.blog.common.vo.PageVO;

/**
 *    400，404，500处理策略接口
 *
 */
public interface StatusStrategy {

    PageVO statusInterface();
}
