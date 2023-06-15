package com.example.petshop.vo;
import com.example.petshop.entity.ShoppingCart;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class shoppingCartVo extends ShoppingCart{

    private String goodsPrice;

    private String goodsName;

    private String shopName;

    private String pictureUrl;


}
