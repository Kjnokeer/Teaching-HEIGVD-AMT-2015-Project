/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.Application;
import javax.ejb.Stateless;

/**
 *
 * @author jermoret
 */
@Stateless
public class ApplicationDAO extends GenericDAO<Application, Long> implements ApplicationDAOLocal {

    // Add business logic below. (Right-click in editor and choose
   // "Insert Code > Add Business Method")
}
