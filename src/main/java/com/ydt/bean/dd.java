package com.ydt.bean;

import com.dingtalk.api.response.OapiUserGetResponse;

public class dd extends OapiUserGetResponse {

    private String qrcode;

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
}
