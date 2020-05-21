package cn.actional.blog.allenum;

import cn.actional.blog.common.Strategy.StatusStrategy;
import cn.actional.blog.common.vo.PageVO;

public enum StatusEnum implements StatusStrategy {

    STATUS_404 {
        @Override
        public PageVO statusInterface() {
            return new PageVO("error_404","404");
        }
    },

    STATUS_405 {
        @Override
        public PageVO statusInterface() {
            return new PageVO("error_404","404");
        }
    },



    STATUS_400 {
        @Override
        public PageVO statusInterface() {
            return new PageVO("error_400","400");
        }


    },

    STATUS_500 {
        @Override
        public PageVO statusInterface() {
            return new PageVO("error_5xx","500");
        }
    }




}
