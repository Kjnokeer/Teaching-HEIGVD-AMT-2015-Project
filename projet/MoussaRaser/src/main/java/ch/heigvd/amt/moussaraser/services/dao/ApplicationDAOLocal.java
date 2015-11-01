package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.Application;
import ch.heigvd.amt.moussaraser.model.entities.User;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ApplicationDAOLocal extends IGenericDAO<Application, Long> {

   List<Application> getAllAplicationsForUser(User u);

   Application getManagedApplicationByApiKey(String apiKey);
}
