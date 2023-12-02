package com.axon.utils;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ErrorMessage {
    private String message;
    private List<String> details = new ArrayList<>();

    public ErrorMessage(String message) {
        this.message = message;
    }

    public ErrorMessage(String message, List<String> details) {
        this.message = message;
        this.details = details;
    }

}
