package top.kiyuu.manage.util;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import lombok.Data;

@Data
public class RESTBean<T> {
    private Integer code;
    private String msg;
    private T data;

    private RESTBean() {
    }
    public static <T> RESTBean<T> success(T data) {
        RESTBean<T> bean=new RESTBean<>();
        bean.setCode(200);
        bean.setMsg("success");
        bean.setData(data);
        return bean;
    }
    public static <T> RESTBean<T> success(String msg,T data) {
        RESTBean<T> bean=new RESTBean<>();
        bean.setCode(200);
        bean.setMsg(msg);
        bean.setData(data);
        return bean;
    }

    public static <T> RESTBean<T> fail(Integer code,String msg, T data) {
        RESTBean<T> bean=new RESTBean<>();
        bean.setCode(code);
        bean.setMsg(msg);
        bean.setData(data);
        return bean;
    }

    public static <T> RESTBean<T> fail(Integer code,String msg) {
        RESTBean<T> bean=new RESTBean<>();
        bean.setCode(code);
        bean.setMsg(msg);
        bean.setData(null);
        return bean;
    }

    public String toJsonString() {
        return JSONObject.toJSONString(this, JSONWriter.Feature.WriteNulls);
    }
}
