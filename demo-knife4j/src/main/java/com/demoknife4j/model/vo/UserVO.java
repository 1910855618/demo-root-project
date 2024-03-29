package com.demoknife4j.model.vo;

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
@ApiModel(value = "UserVO", description = "用户 VO")
public class UserVO {
    @ApiModelProperty(name = "userId", value = "用户 ID", example = "443")
    private Integer userId;
    @ApiModelProperty(name = "name", value = "姓名", dataType = "String", allowableValues = "1~20", example = "Emma", required = true, hidden = false)
    private String name;
    @ApiModelProperty(name = "age", value = "年龄", example = "24")
    private Integer age;
    @ApiModelProperty(name = "phone", value = "电话", example = "158745784415", required = true)
    private String phone;
    @ApiModelProperty(name = "email", value = "邮件", example = "Emma@qq.com")
    private String email;
}
