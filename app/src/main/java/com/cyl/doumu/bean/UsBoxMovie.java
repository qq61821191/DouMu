package com.cyl.doumu.bean;

import java.io.Serializable;
import java.util.List;

/*
 * @Description: 北美排行榜返回的电影实体类
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/5/8
 */

public class UsBoxMovie implements Serializable {
    private int rank;
    private int box;
    private MovieEntry subject;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getBox() {
        return box;
    }

    public void setBox(int box) {
        this.box = box;
    }

    public MovieEntry getSubject() {
        return subject;
    }

    public void setSubject(MovieEntry subject) {
        this.subject = subject;
    }
}
