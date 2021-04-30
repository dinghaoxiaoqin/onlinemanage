package com.dinghao.core.security;

import lombok.Data;

@Data
public class AddS {

    private String id;

    private String name;

    private String nickName;

    public AddS() {
    }

    public AddS(String id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }
}
