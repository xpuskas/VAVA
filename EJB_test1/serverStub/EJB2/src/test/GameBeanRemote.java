package test;

import java.util.List;

import javax.ejb.Remote;

/**
 * Remote rozhranie TestBean-y pre pristup z klienta/klientov
 * @author Jaroslav Jakubik
 */
@Remote
public interface GameBeanRemote {

	void addUser(UserDetails user);
	
	List<UserDetails> getNameh();
	
	String vratMeno(int id);
	
}
