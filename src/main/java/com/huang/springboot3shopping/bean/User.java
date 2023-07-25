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
public class User {
    private int id;
    private String username;
    private String password;
    private int roleId;
}
