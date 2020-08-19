package com.ydt.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

public class QRcode {
    //public static String code="12332";
    //public static String qrcodepath="C:/Users/jinqizhe/Desktop/"+code+".png";
    public static void generateQRCodeImage(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int width=350;
        int height=350;
        String filePath="C:/Users/jinqizhe/Desktop/"+text+".png";
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }

    public static void generateQRCodeImages(int start,int end) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int width=350;
        int height=350;
        String text;
        ArrayList<String> list = new ArrayList<String> ();
        for(int i=start;i<=end;i++){
            list.add("PC"+i);
        }
        for(int i=0;i<list.size();i++){
            text=list.get(i).toString();
            String filePath="d:/picture/qrcode/"+text+".png";
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

            Path path = FileSystems.getDefault().getPath(filePath);

            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        }


    }

    public static void main(String[] args) throws IOException, WriterException {
        generateQRCodeImage("啦啦啦");
        generateQRCodeImages(10,12);
        System.out.println("成功");
    }
}
