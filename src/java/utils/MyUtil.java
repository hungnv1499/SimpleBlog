/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Random;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author DELL
 */
public class MyUtil implements Serializable{
    public static String getHash(String message, String algorithm){
        String result = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(message.getBytes());
            byte[] digestBytes = messageDigest.digest();
            result = DatatypeConverter.printHexBinary(digestBytes).toLowerCase();
        } catch (Exception e) {
            result = null;
        }
        return result;
    }
    public static int getCode(int max, int min){
        return (int)(Math.random()*(max  - min + 1) + min);
    }
}
