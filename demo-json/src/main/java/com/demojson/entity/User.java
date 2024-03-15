package com.demojson.entity;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
// 忽略注解参数中的这些成员变量
@JsonIgnoreProperties({"account", "phone"})
// 命名策略风格小点大小写策略 accountType -> account.type
//@JsonNaming(PropertyNamingStrategies.LowerDotCaseStrategy.class)
// 命名策略风格烤肉串策略 accountType -> account-type
//@JsonNaming(PropertyNamingStrategies.KebabCaseStrategy.class)
// 命名策略风格小写策略 accountType -> accounttype
//@JsonNaming(PropertyNamingStrategies.LowerCaseStrategy.class)
// 命名策略风格首字母大写驼峰策略 accountType -> AccountType
//@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
// 命名策略风格驼峰策略 AccountType -> accountType
//@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
// 命名策略风格全大写下划线策略 accountType -> ACCOUNT_TYPE
//@JsonNaming(PropertyNamingStrategies.UpperSnakeCaseStrategy.class)
// 命名策略风格全小写下划线策略 accountType -> account_type
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
// 自定义序列化
//@JsonSerialize(using = UserSerializer.class)
// 自定义反序列化
//@JsonDeserialize(using = UserDeserializer.class)
public class User implements Serializable {
    private static final long serialVersionUID = 8260276943185481013L;

    public interface UserNameView {};
    public interface AllView extends UserNameView {};

    @JsonView(AllView.class)
    private String account;

    // 分组
    @JsonView(UserNameView.class)
    private String name;

    // 忽略此属性，在序列化为 JSON 时会忽略有此注解的成员变量
    @JsonIgnore
    private String password;

    @JsonView(AllView.class)
    private String accountType;

    @JsonView(AllView.class)
    private String phone;

    @JsonView(AllView.class)
    private Integer age;

    // 将 User 对象的成员属性 birthday 设置别名为 bth
    @JsonProperty("bth")
    @JsonView(AllView.class)
    // 格式化时间类型
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;
}
