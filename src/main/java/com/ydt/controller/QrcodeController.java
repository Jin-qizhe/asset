package com.ydt.controller;

import com.ydt.util.QRcode;
import com.ydt.util.SysResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("qrcode")
public class QrcodeController {
    @RequestMapping("/makeqrcode/{text}")
    public SysResult makeQrcode(@PathVariable String text){
        try{
            QRcode.generateQRCodeImage(text);
            return SysResult.ok();
        }catch(Exception e){
            return SysResult.er();
        }
    }

    @RequestMapping("/makeqrcodes/{start}/{end}")
    public SysResult makeQrcodes(@PathVariable int start,@PathVariable int end){
        try{
            QRcode.generateQRCodeImages(start,end);
            return SysResult.ok();
        }catch(Exception e){
            return SysResult.er();
        }
    }

    @RequestMapping("getqrcode/{text}")
    public String getQrcode(@PathVariable String text){
        return "localhost:8080/qrcode/"+text+".png";
    }
}
