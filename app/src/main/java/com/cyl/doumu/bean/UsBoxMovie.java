package com.cyl.doumu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
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
