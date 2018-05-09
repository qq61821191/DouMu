package com.cyl.doumu.bean;

import java.io.Serializable;
import java.util.List;

/*
 * @Description: 北美排行榜返回的数据
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/5/8
 */

public class UsBoxListBean implements Serializable {
    private String title;
    private String date;
    private List<UsBoxMovie> subjects;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<UsBoxMovie> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<UsBoxMovie> subjects) {
        this.subjects = subjects;
    }
}
