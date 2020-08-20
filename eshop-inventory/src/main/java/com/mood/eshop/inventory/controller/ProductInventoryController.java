package com.mood.eshop.inventory.controller;

import com.mood.eshop.inventory.model.ProductInventory;
import com.mood.eshop.inventory.request.CacheLoadRequest;
import com.mood.eshop.inventory.request.DBUpdateRequest;
import com.mood.eshop.inventory.request.Request;
import com.mood.eshop.inventory.service.ProductInventoryService;
import com.mood.eshop.inventory.service.RequestAsyncService;
import com.mood.eshop.inventory.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author mood321
 * @date 2020/8/6 20:36
 * @email 371428187@qq.com
 */
@Controller
@Slf4j
public class ProductInventoryController {

    @Resource
    ProductInventoryService productInventoryService;
    @Resource
    RequestAsyncService requestAsyncService;

    /**
     *  修改库存接口
     * @param productInventory
     * @return
     */
    @RequestMapping("/updateProductInventory")
    @ResponseBody
    public Response updateProductInventory(ProductInventory productInventory){
        Response response=null;
        try {
            log.info("updateProductInventory>>>>>  入参 商品ID = "+productInventory.getProductId() +"    库存 = "+ productInventory.getProductInventory());
            Request dbUpdateRequest = new DBUpdateRequest(productInventory,productInventoryService);
            requestAsyncService.process(dbUpdateRequest);
            response=new Response(Response.SUCCESS,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            response=new Response(Response.FAILURE,"修改失败");
        }
        return    response;
    }
    @RequestMapping("/getProductInventory")
    @ResponseBody
    public ProductInventory getProductInventory(Integer productId){
        log.info("getProductInventory>>>>>  入参 商品ID = "+productId );

        ProductInventory productInventory=null;
        try {
             productInventory = new ProductInventory();
            productInventory.setProductId(productId);
            Request dbUpdateRequest = new CacheLoadRequest( productInventory,productInventoryService,false);
            requestAsyncService.process(dbUpdateRequest);

            // 因为是异步处理  需要等待一会
            long startTime = System.currentTimeMillis();
            long endTime = 0L;
            long waitTimt = 0L;
            while (true){
                if(waitTimt > 200){
                    break;
                }
                productInventory= productInventoryService.getProductInventory(productId);
                if(productInventory!= null){
                     return productInventory ;
                }

               // 超时
               else {
                   Thread.sleep(20);
                    endTime=System.currentTimeMillis();
                    waitTimt=endTime-startTime;
                }
           }
            log.info("getProductInventory>>>>>  尝试数据库  商品ID = "+productId );

            // 尝试数据库
            productInventory =new ProductInventory(productId,-1L);
            productInventory = productInventoryService.findProductInventory(productInventory);
            if(productInventory !=null){

                // 写缓存 要异步  因为这是 flag 标志,是读
                // 强制刷新

                log.info("getProductInventory>>>>>  读数据库 强制刷新redis  商品ID = "+productId );

                dbUpdateRequest= new CacheLoadRequest( productInventory,productInventoryService,true);
                requestAsyncService.process(dbUpdateRequest);

                // 代码会运行到这里，只有三种情况：
                // 1、就是说，上一次也是读请求，数据刷入了redis，但是redis LRU算法给清理掉了，标志位还是false
                // 所以此时下一个读请求是从缓存中拿不到数据的，再放一个读Request进队列，让数据去刷新一下
                // 2、可能在200ms内，就是读请求在队列中一直积压着，没有等待到它执行（在实际生产环境中，基本是比较坑了）
                // 所以就直接查一次库，然后给队列里塞进去一个刷新缓存的请求
                // 3、数据库里本身就没有，缓存穿透，穿透redis，请求到达mysql库

                
                return productInventory;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        productInventory =new ProductInventory(productId,-1L);
        return   productInventory ;
    }
}