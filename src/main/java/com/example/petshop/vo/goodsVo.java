package com.example.petshop.vo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.example.petshop.entity.Goods;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class goodsVo extends Goods{
    private String url;
    private String belongId;
    private int state;
}
