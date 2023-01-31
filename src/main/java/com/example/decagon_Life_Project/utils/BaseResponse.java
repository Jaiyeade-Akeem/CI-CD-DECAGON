package com.example.decagon_Life_Project.utils;

import com.example.decagon_Life_Project.enums.ResponseCodeEnum;
import lombok.Data;

@Data
public class BaseResponse {
    private int code;
    private String description;

    public BaseResponse() {
        this(ResponseCodeEnum.ERROR); // default value
    }

    public BaseResponse(ResponseCodeEnum responseCode) {
        this.code = responseCode.getCode();
        this.description = responseCode.getDescription();
    }

    public void assignResponseCodeAndDescription(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
