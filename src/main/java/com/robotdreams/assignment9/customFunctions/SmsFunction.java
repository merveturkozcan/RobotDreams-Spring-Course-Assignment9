package com.robotdreams.assignment9.customFunctions;

@FunctionalInterface
public interface SmsFunction {
    String replace(String template, String name, String email, String orderNumber);
}
