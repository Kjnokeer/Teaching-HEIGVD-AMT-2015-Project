/**
 * Auteurs : Olivier Liechti
 * Fichier : IGenericDAO.java
 */

package ch.heigvd.amt.moussaraser.services.dao;

import ch.heigvd.amt.moussaraser.model.entities.AbstractDomainModelEntity;
import java.util.List;
import javax.ejb.Local;

/**
 * 
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Local
public interface IGenericDAO<T extends AbstractDomainModelEntity, PK> {

   public PK create(T t);

   public T createAndReturnManagedEntity(T t);

   public void update(T t);

   public void delete(T t);

   public long count();

   public T findById(PK id);

   public List<T> findAll();

   public List<T> findAllByPage(int pageSize, int pageIndex);

}
