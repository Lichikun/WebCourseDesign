package com.example.petshop.common.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class BaseEntity implements Serializable {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField(exist = false)
    private Object etc;

    public void put(String key, Object value) {
        if(etc == null) {
            etc = new HashMap<String, Object>();
        }
        ((Map<String, Object>) etc).put(key, value);
    }
}
