package com.cyl.doumu.data.base;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;
/*
 * @Description:
 * @Author: Cyl
 * @Version: V1.0
 * @Create: 2018/5/8
 */
public class StringConverter implements  Converter<ResponseBody, String> {

    public static final StringConverter INSTANCE = new StringConverter();

    @Override
    public String convert(ResponseBody value) throws IOException {
        return value.string();
    }
}
