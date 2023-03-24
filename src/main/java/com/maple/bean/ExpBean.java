package com.maple.bean;

/**
 * @Author dwang
 * @Description table数据
 * @create 2023/3/24 10:12
 * @Modified By:
 */
public class ExpBean {
    private String name;
    private String desc;
    private String fun;

    public ExpBean() {
    }

    public ExpBean(String name, String desc, String fun) {
        this.name = name;
        this.desc = desc;
        this.fun = fun;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFun() {
        return fun;
    }

    public void setFun(String fun) {
        this.fun = fun;
    }
}
