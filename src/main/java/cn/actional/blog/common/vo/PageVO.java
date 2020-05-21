package cn.actional.blog.common.vo;

import java.io.Serializable;

public class PageVO implements Serializable {

    private String path;

    private String fragment;


    public PageVO() {
    }

    public PageVO(String path, String fragment) {
        this.path = path;
        this.fragment = fragment;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }
}
