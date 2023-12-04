package com.ruler.auth.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class ApiResponse<T> {
    private T result;
    private Map<String, Object> errors;
    private Metadata metadata;

    public static <T> ApiResponse<T> successWithMessage(String message, T result) {
        return ApiResponse.<T>builder()
                .result(result)
                .metadata(new Metadata(message, "ok"))
                .build();
    }

    public static <T> ApiResponse<T> failureWithMessage(String message) {
        return ApiResponse.<T>builder()
                .result(null)
                .metadata(new Metadata(message, "fail"))
                .build();
    }

    @Getter
    @Setter
    @Builder
    public static class Metadata {
        private String code;
        private String message;

        public Metadata(String message, String code) {
            this.message = message;
            this.code = code;
        }
    }
}
