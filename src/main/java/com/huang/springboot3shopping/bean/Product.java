package com.huang.springboot3shopping.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: spring-boot-3
 * @BelongsPackage: com.huang.springboot3shopping.bean
 * @Author: HuangJinTao
 * @CreateTime: 2023-07-03  13:08
 * @Description: TODO
 * @Version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private Long id;
    private int pId;
    private String pName;
    private String pPrice;
    private String pStock;
    private String pCategory;
}
