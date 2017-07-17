package com.spock.examples.parameterized;

/**
 * Created by cweir on 17/07/2017.
 */
public class ImageExtensionCheck {
    private boolean result = false;
    private String errorCode ="";
    private String errorDescription ="";

    public boolean isResult() {
        return result;
    }
    public void setResult(boolean result) {
        this.result = result;
    }
    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorDescription() {
        return errorDescription;
    }
    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }



}