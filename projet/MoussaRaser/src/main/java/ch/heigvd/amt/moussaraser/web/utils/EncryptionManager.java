package ch.heigvd.amt.moussaraser.web.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionManager {
    public static String getHash(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        return HexBin.encode(digest.digest(text.getBytes("UTF-8")));
    }
}