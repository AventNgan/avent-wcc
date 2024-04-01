package com.avent.base.model.response;

import lombok.Data;

@Data
public class ResponseModel<T> {

    private T data;
    private boolean success;
    private String errorMessage;

    public static <T>ResponseModel success(T data) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setData(data);
        responseModel.setSuccess(true);
        return responseModel;
    }

    public static ResponseModel fail(String errorMessage) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setSuccess(false);
        responseModel.setErrorMessage(errorMessage);
        return responseModel;
    }

}
