package com.xkcoding.learn.java_functional;

import lombok.*;

import java.io.Serializable;

/**
 * <p>
 * 用户对象
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2020-12-13 23:15
 */
@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String mobile;
}