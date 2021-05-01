package com.franktran.jsp.config.security;

public enum UserPermission {

    ENROLMENT_READ("ENROLMENT:READ"),
    ENROLMENT_WRITE("ENROLMENT:WRITE"),
    USER_READ("USER:READ"),
    USER_WRITE("USER:WRITE"),
    COURSE_READ("COURSE:READ"),
    COURSE_WRITE("COURSE:WRITE"),
    STUDENT_READ("STUDENT:READ"),
    STUDENT_WRITE("STUDENT:WRITE");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
