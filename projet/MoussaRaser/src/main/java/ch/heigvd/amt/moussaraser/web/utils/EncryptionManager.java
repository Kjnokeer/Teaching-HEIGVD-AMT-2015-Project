/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date    : 16.10.2015
 * Fichier : EncryptionManager.java
 */

package ch.heigvd.amt.moussaraser.web.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe utilitaire qui gère les différentes opérations de hashage/chiffrement ...
 * (ne contient que des méthodes statiques)
 */
public class EncryptionManager {

    /**
     * Retourne le hash du text passé en paramètre (utilise SHA-256)
     * @param text Texte à hasher
     * @return Le hash
     */
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

   /**
    * Retourne une clé d'API unique
    * @return Une clé d'api unique
    */
   public static String getAPIKey() {
      return UUID.randomUUID().toString().replaceAll("[\\s\\-()]", "");
   }

}
