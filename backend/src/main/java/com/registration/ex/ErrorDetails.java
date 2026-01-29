package com.registration.ex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

    private String errorMessage;
    private int status;
    private List<String> details;

    public ErrorDetails(String errorMessage, int status) {
        this.errorMessage = errorMessage;
        this.status = status;
    }
}
