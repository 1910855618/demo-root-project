package com.demolombok.entity;

import lombok.*;

@Getter
@Setter
@ToString(includeFieldNames = false, callSuper = true, exclude = {"age", "sex"})
@EqualsAndHashCode(exclude = "age")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
//@Data
@Builder
public class Student {
//    @Getter(AccessLevel.PROTECTED)
//    @Setter
    @NonNull
    private String name, sex;
    private int age;
}
