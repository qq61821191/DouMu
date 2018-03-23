package com.cyl.doumu.bean;

import java.util.List;

/*
 * @Description: 
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/3/23 0023$ 16:25$
 */
public class MovieListBean {
    /**
     {
     "title": "正在上映的电影-北京",
     "total": 39,
     "start": 0,
     "count": 20,
     "subjects": [<Subject>, ...],
     }
     */
    private String title;
    private int total;
    private int start;
    private int count;
    private List<MovieEntry>  subjects;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<MovieEntry> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<MovieEntry> subjects) {
        this.subjects = subjects;
    }
}
