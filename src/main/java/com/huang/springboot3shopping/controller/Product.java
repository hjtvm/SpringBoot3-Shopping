package com.huang.springboot3shopping.controller;

import com.huang.springboot3shopping.bean.temporary;
import com.huang.springboot3shopping.mapper.ProductMapper;
import com.huang.springboot3shopping.mapper.temporaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: SpringBoot3-Shopping
 * @BelongsPackage: com.huang.springboot3shopping.controller
 * @Author: HuangJinTao
 * @CreateTime: 2023-07-25  20:43
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
public class Product {

    @Autowired
    temporaryMapper TemporaryMapper;

    @Autowired
    ProductMapper productMapper;
    /**
     * 实现求 总 价格
     **/
    @PostMapping(value = "/purchase")
    public Float purchase(){
        List<temporary> temporaries = TemporaryMapper.selectAllTemporary();
        float sum = 0;
        for (temporary temporary : temporaries) {
            sum= sum + Float.parseFloat(temporary.getPPrice());
        }
        return sum;
    }

    /**
     * 加入购物车操作
     **/
    @PostMapping(value = "/value")
    public void value(@RequestBody Map<String, String> data){
        try{
            TemporaryMapper.addTemporary(data.get("pName"),data.get("pPrice"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 实现支付成功中的数据变化
     **/
    @PostMapping(value = "/Paymentsuccessful")
    public void payment(){
        try{
            List<temporary> temporaries = TemporaryMapper.selectAllTemporary();
            for (temporary temporary : temporaries) {
                String pName = temporary.getPName();
                productMapper.updateStock(pName);
            }
            TemporaryMapper.deleteAllTemporary();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
