/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.Application;
import javax.ejb.Local;

/**
 *
 * @author jermoret
 */
@Local
public interface ApplicationDAOLocal extends IGenericDAO<Application, Long> {
   
}
