package com.cyl.doumu.bean;

import java.io.Serializable;

/*
 * @Description: 主演
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/3/23 0023$ 17:14$
 */
public class Cast implements Serializable {
    private String alt;

    private Avatar avatar;

    private String name;

    private String id;

    public void setAlt(String alt){
        this.alt = alt;
    }
    public String getAlt(){
        return this.alt;
    }
    public void setAvatar(Avatar avatar){
        this.avatar = avatar;
    }
    public Avatar getAvatar(){
        return this.avatar;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
}
