package com.huang.springboot3shopping.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: SpringBoot3-Shopping
 * @BelongsPackage: com.huang.springboot3shopping.bean
 * @Author: HuangJinTao
 * @CreateTime: 2023-07-20  22:31
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Integer roleId;
    private String roleName;
}
