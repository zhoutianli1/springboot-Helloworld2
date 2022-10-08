package com.zhou.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户user")
public class User {
    @ApiModelProperty("用户id")
    private int id;
    private String name;
    private String pwd;
    private String perms;
}
