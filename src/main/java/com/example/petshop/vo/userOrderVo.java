package com.example.petshop.vo;

import com.example.petshop.entity.OrdersItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ToString
public class userOrderVo extends OrdersItem {
     private String goodsOrPetsName;
     private String url;
     private String state;
     private BigDecimal total;

}
