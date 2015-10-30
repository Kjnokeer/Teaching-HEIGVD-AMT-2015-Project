/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.User;
import javax.ejb.Local;

/**
 *
 * @author thibaud
 */
@Local
public interface UsersDAOLocal extends IGenericDAO<User, Long> {

   User login(String username, String password);
   
   User getFromId(Long id);
    
}
