package com.backstage.record.domain;

import org.springframework.data.annotation.Id;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

    @Id
    private Long id; //主键 自增

    @NotNull(message = "用户名不能为空")
    @Size(min = 6 , max = 18, message = "用户名应设为6至18位")
    private String username;

    @NotNull(message = "密码不能为空")
    @Size(min = 6 , max = 18, message = "密码应设为6至18位")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
