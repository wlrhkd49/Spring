package com.example.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String name;

    @NotNull
    private Long level;

    private String password;

    public boolean isAdmin() {
        return level >= 100;
    }


    public boolean isActive() {
        return level > 0;
    }

    public void deactivate() {
        level = 0L;
    }

    @JsonIgnore // 패스워드 없는 경우 문제되지 않게함.
    public String getAccessToken() {
        if(password == null) return "";
        return password.substring(0, 10); //password에서 잘라옴
    }
}
