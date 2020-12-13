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
    private String name;
    private boolean enabled = true;
    private String password;
    private String email;
    private int age;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        return id != null && id.equals(((User) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + ", mobile='" + mobile + '\'' + ", email='" + email + '\'' + ", name='" + name + '\'' + "}";
    }
}