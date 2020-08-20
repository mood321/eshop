package com.mood.eshop.inventory.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mood321
 * @date 2020/8/6 20:37
 * @email 371428187@qq.com
 * @Des
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    public  static  final String SUCCESS="success";
    public  static  final String FAILURE="failure";
    private String    status;
    private String    message;
}