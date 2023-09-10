package com.example.petshop.vo;
import com.example.petshop.entity.Orders;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ordersVo extends Orders {
    private String goodsId;
    private String goodsQuantity;
    private String goodsPrice;
    private String goodsName;
    private String goodsCategory;
}
