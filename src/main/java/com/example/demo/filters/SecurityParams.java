package com.example.demo.filters;

public interface SecurityParams {
    public static final String JWT_HEADER_NAME="Authorization";
    public static final long EXPIRATION=20*60*1000;
    public static final String HEADER_PREFIX="Bearer ";
    public static final String SECRET = "s0eX10Mk1ugaZGk8SJLY";
}

