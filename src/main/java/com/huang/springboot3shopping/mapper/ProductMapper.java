package com.huang.springboot3shopping.mapper;

import com.huang.springboot3shopping.bean.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsProject: spring-boot-3
 * @BelongsPackage: com.huang.springboot3shopping.mapper
 * @Author: HuangJinTao
 * @CreateTime: 2023-07-03  13:32
 * @Description: TODO
 * @Version: 1.0
 */
public interface ProductMapper {
    //添加商品
    public int addProduct(@Param("pId") int pId, @Param("pName") String pName,
                          @Param("pPrice") String pPrice, @Param("pCategory") String pCategory,
                          @Param("pStock") String pStock);
    //更新商品
    public int updateProduct(Product product);
    //删除商品
    public int deleteProduct(@Param("id") Long id);
    //通过id查询商品
    public Product getProductById(@Param("id") Long id);
    //查询所有的商品
    public List<Product> getProductByAll();
    //查询所有商品账号
    public List<Integer> getProductByPId();
    public List<Product> selectProduct(@Param("pId") String pId, @Param("pName") String pName,
                                 @Param("pPrice") String pPrice, @Param("pCategory") String pCategory,
                                 @Param("pStock") String pStock);
    public int updateStock(@Param("pName") String pName);
}
