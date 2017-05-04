package test;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 * Jednoducha stateless session beana
 */
@Stateless
public class GameBean implements GameBeanRemote {

	public GameBean(){
	   }

   @PersistenceContext(unitName="EjbComponentPU")
	private EntityManager em;
	
	@Override
	public void addUser(UserDetails user) {
		EntityTransaction tr = em.getTransaction();
		
		tr.begin();
		
		em.persist(user); 
		
		tr.commit();
		
	}  

	@Override
	public List<UserDetails> getNameh() {
		return em.createQuery("from UserDetails").getResultList();

	}
	
	public String vratMeno(int id) {
		return em.createNativeQuery("SELECT users.name from users WHERE id = '12'").getResultList().get(0).toString();
	}

	/**
	 * Biznis logika ktora vrati rozsireny textovy retazec
	 */
    
    

}
