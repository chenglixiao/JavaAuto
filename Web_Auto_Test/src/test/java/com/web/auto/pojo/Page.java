package com.web.auto.pojo;

import java.util.List;

/**
 * @ Author：Pada_xiao
 * @ Date：19:40 2018/11/30
 * @ Description：封装Page元素的属性及子元素
 * @ Modified By：
 * @Version:
 */
public class Page {
    private String keyword;
    private List<UIElement> uiElement;
    public Page() {
    }

    public Page(String keyword, List<com.web.auto.pojo.UIElement> UIElement) {
        this.keyword = keyword;
        this.uiElement = UIElement;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<com.web.auto.pojo.UIElement> getUiElement() {
        return uiElement;
    }

    public void setUiElement(List<com.web.auto.pojo.UIElement> UIElement) {
        this.uiElement = UIElement;
    }

    @Override
    public String toString() {
        return ("keyword【"+keyword+"】UIElement【"+uiElement+"】");
    }
}
