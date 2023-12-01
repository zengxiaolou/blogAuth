package com.ruler.auth.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ApiResponse<T> {
    private T result;
    private Map<String, Object> extension;

    @Builder.Default
    private Metadata metadata = new Metadata();

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Metadata {
        @Builder.Default
        private String code = "ok";
        @Builder.Default
        private String message = "success";
        private Page page;
    }

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Page {
        private int page;
        private int size;
        private int total;
    }
}
