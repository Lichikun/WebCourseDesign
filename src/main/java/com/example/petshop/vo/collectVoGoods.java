package com.example.petshop.vo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.example.petshop.entity.Goods;
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class collectVoGoods extends  Goods{
    private String userId;
    private String userName;
    private String petsId;
    private String url;
    private String belongId;
    private int state;

}
