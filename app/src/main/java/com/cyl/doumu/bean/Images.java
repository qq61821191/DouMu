package com.cyl.doumu.bean;

import java.io.Serializable;

/*
 * @Description: 电影海报
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/3/23 0023$ 17:12$
 */
public class Images implements Serializable{
    private String small;

    private String large;

    private String medium;

    public void setSmall(String small){
        this.small = small;
    }
    public String getSmall(){
        return this.small;
    }
    public void setLarge(String large){
        this.large = large;
    }
    public String getLarge(){
        return this.large;
    }
    public void setMedium(String medium){
        this.medium = medium;
    }
    public String getMedium(){
        return this.medium;
    }
}
