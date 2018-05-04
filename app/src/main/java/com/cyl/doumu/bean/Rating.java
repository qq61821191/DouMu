package com.cyl.doumu.bean;

import java.io.Serializable;

/*
 * @Description: 评分
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/3/23 0023$ 17:12$
 */
public class Rating implements Serializable {
    private int max;

    private double average;

    private String stars;

    private int min;

    public void setMax(int max){
        this.max = max;
    }
    public int getMax(){
        return this.max;
    }
    public void setAverage(double average){
        this.average = average;
    }
    public double getAverage(){
        return this.average;
    }
    public void setStars(String stars){
        this.stars = stars;
    }
    public String getStars(){
        return this.stars;
    }
    public void setMin(int min){
        this.min = min;
    }
    public int getMin(){
        return this.min;
    }
}
