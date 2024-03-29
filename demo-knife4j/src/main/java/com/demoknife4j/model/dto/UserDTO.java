package com.demoknife4j.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UserDTO", description = "用户 DTO")
public class UserDTO {
    @ApiModelProperty(name = "userId", value = "用户 ID")
    private Integer userId;
    @ApiModelProperty(name = "name", value = "姓名")
    private String name;
    @ApiModelProperty(name = "age", value = "年龄")
    private Integer age;
    @ApiModelProperty(name = "phone", value = "电话")
    private String phone;
    @ApiModelProperty(name = "email", value = "邮件")
    private String email;
}
