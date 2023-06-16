package com.example.petshop.vo;

import com.example.petshop.entity.Shop;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class shopVo extends Shop {
    private String url;
    private String belongId;
    private int state;
}