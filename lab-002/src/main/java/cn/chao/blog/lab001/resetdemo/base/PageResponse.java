package cn.chao.blog.lab001.resetdemo.base;

import lombok.Data;

/**
 * @author 胡海超
 * @date 2020/6/9 16:05
 */
@Data
public class PageResponse<T> {
    private Integer code;
    private String msg;
    private Page<T> data;
}
