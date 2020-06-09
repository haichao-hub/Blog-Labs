package cn.chao.blog.lab001.resetdemo.base;

import lombok.Data;


/**
 * @author 胡海超
 * @date 2020/6/9 16:07
 */
@Data
public class EntityResponse<T> {
    private Integer code;
    private String msg;
    private T data;
}
