package com.franktran.lombok.student;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

//@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
//@ToString(exclude = {"dob", "email"})
//@EqualsAndHashCode
@Data
public class Student {

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PROTECTED)
    private Long id;
    private final String name;
    private final String email;
    private LocalDate dob;

}
