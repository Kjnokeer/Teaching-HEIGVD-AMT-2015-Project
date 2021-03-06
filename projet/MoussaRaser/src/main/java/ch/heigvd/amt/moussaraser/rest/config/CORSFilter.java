/**
 * Auteurs : Jérôme Moret & Mathias Dolt & Thibaud Duchoud & Mario Ferreira
 * Date : 15.01.2016
 * Fichier : CORSFilter.java
 */
package ch.heigvd.amt.moussaraser.rest.config;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Mathias
 */
@Provider
public class CORSFilter implements ContainerResponseFilter {

   @Override
   public void filter(ContainerRequestContext creqc, ContainerResponseContext cresc) throws IOException {
      cresc.getHeaders().add("Access-Control-Allow-Origin", "*");
      cresc.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
      cresc.getHeaders().add("Access-Control-Allow-Credentials", "true");
      cresc.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD");
      cresc.getHeaders().add("Access-Control-Max-Age", String.valueOf(60 * 60 * 24 * 14)); // 14 days
   }

}
