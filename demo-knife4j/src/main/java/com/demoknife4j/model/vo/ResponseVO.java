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
@ApiModel(value = "ResponseVO", description = "响应信息 VO")
public class ResponseVO {
    @ApiModelProperty(name = "timestamp", value = "响应时间戳", example = "1711604116246")
    private Long timestamp;
    @ApiModelProperty(name = "message", value = "响应信息", example = "success")
    private String message;
    @ApiModelProperty(name = "code", value = "响应码", example = "200")
    private Integer code;
    @ApiModelProperty(name = "data", value = "响应数据", example = "{}")
    private Object data;
}
