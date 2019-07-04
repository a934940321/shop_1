package com.qf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "rolename")
    private String roleName;
    @TableField(value = "rolealias")
    private String roleAlias;
    private Date createtime = new Date();
    private Integer status;

    @TableField(exist = false)
    private boolean checked;

}
