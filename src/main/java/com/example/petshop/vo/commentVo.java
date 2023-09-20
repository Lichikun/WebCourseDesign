package com.example.petshop.vo;

import com.example.petshop.entity.Evaluation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class commentVo extends Evaluation {
    private String name;
    private String avatar;

}
