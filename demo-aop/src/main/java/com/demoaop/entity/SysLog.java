package com.demoaop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysLog implements Serializable {
    private static final long serialVersionUID = 7167932565922589040L;

    private Integer id;
    private String userName;
    private String operation;
    private Integer time;
    private String method;
    private String params;
    private String ip;
    private LocalDateTime createTime;
}
