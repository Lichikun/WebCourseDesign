package com.example.petshop.vo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.example.petshop.entity.Pets;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class petsVo extends  Pets{
    private String url;
    private String belongId;
    private int state;
}
