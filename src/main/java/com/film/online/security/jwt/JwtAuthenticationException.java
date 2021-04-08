package com.film.online.security.jwt;

import jdk.nashorn.internal.objects.annotations.Constructor;

import javax.naming.AuthenticationException;
public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg){
        super(msg);
    }
}
