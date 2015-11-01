package ch.heigvd.amt.moussaraser.web.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EncryptionManager {

   public static String getHash(String text) {
      try {
         MessageDigest digest = MessageDigest.getInstance("SHA-256");
         digest.reset();
         return HexBin.encode(digest.digest(text.getBytes("UTF-8")));
      } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
         Logger.getLogger(EncryptionManager.class.getName()).log(Level.SEVERE, null, ex);
      }

      return null;
   }

   public static String getAPIKey() {
      return UUID.randomUUID().toString().replaceAll("[\\s\\-()]", "");
   }

}
