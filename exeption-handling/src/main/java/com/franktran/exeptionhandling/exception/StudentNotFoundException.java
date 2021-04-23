package com.franktran.exeptionhandling.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class StudentNotFoundException extends RuntimeException {

    private final Long id;
}
