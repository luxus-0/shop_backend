package com.github.shop.infrastructure.security.login.exception;

import static com.github.shop.domain.constant.Constants.USER_ROLE_NOT_FOUND;

public class UserRoleNotFoundException extends RuntimeException{
    public UserRoleNotFoundException(){
        super(USER_ROLE_NOT_FOUND);
    }
}
