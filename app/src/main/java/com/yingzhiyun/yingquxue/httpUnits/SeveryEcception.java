package com.yingzhiyun.yingquxue.httpUnits;

/**
 * Created by Administrator on 2019/1/17.
 */

public class SeveryEcception extends Throwable {
   private int code;
   private String message;
   public SeveryEcception(int code,String message){
        this.code=code;
        this.message=message;
   }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
