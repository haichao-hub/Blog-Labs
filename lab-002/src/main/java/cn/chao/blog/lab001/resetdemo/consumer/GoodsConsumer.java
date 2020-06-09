package cn.chao.blog.lab001.resetdemo.consumer;

import cn.chao.blog.lab001.resetdemo.base.EntityResponse;
import cn.chao.blog.lab001.resetdemo.base.ListResponse;
import cn.chao.blog.lab001.resetdemo.base.Page;
import cn.chao.blog.lab001.resetdemo.base.PageResponse;
import cn.chao.blog.lab001.resetdemo.component.RestComponent;
import cn.chao.blog.lab001.resetdemo.consumer.dto.GoodsDTO;
import cn.chao.blog.lab001.resetdemo.consumer.req.ListGoodsReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 胡海超
 * @date 2020/6/9 16:32
 */
@Component
public class GoodsConsumer {
    @Autowired
    private RestComponent restComponent;

    /**
     * 分页查询商品
     * @param req
     * @return
     */
    public Page<GoodsDTO> pageGoods(ListGoodsReq req){
        PageResponse<GoodsDTO> pageResponse = restComponent.postForPage("/goods/page", req, GoodsDTO.class);
        return pageResponse.getData();
    }

    /**
     * 查询商品列表（不分页）
     * @param req
     * @return
     */
    public List<GoodsDTO> listGoods(ListGoodsReq req){
        ListResponse<GoodsDTO> goodsListRes = restComponent.getForList("/goods/list", req, GoodsDTO.class,null);
        return goodsListRes.getData();
    }

    /**
     * 查询单个商品
     * @param req
     * @return
     */
    public GoodsDTO getGoods(ListGoodsReq req){
        EntityResponse<GoodsDTO> goodsRes= restComponent.getForEntity("/goods/list", req, GoodsDTO.class, null);
        return goodsRes.getData();
    }
}
