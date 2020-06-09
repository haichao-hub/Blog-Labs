package cn.chao.blog.lab001.resetdemo.base;

import lombok.Data;

/**
 * @author 胡海超
 * @date 2020/6/9 16:08
 */
@Data
public class Page<T> {
    private Integer pageNum = 1;
    private Integer pageSize = 20;
    private Long total = 0L;
}
