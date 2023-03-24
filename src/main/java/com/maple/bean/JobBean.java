package com.maple.bean;

/**
 * @Author dwang
 * @Description TODO
 * @create 2023/3/24 14:44
 * @Modified By:
 */
public class JobBean {

    private JobEnum name;
    private String perfectUrl;
    private String jobRole;
    private String jobLink;

    public JobBean(JobEnum name, String perfectUrl, String jobRole, String jobLink) {
        this.name = name;
        this.perfectUrl = perfectUrl;
        this.jobRole = jobRole;
        this.jobLink = jobLink;
    }

    public JobEnum getName() {
        return name;
    }

    public void setName(JobEnum name) {
        this.name = name;
    }

    public String getPerfectUrl() {
        return perfectUrl;
    }

    public void setPerfectUrl(String perfectUrl) {
        this.perfectUrl = perfectUrl;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getJobLink() {
        return jobLink;
    }

    public void setJobLink(String jobLink) {
        this.jobLink = jobLink;
    }
}

