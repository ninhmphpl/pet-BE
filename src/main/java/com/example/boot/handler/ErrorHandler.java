package com.example.boot.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class ErrorHandler{
    private int statusError;
    private String name;
    private String description;
}
