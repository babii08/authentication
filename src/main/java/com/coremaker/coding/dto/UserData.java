package com.coremaker.coding.dto;

import lombok.Value;

@Value(staticConstructor = "of")
public class UserData {

    String name;
    String email;
    String password;

}
