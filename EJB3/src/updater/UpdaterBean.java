package updater;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import fetcher.FetcherBean;
import fetcher.FetcherBeanRemote;
import model.Comment;
import model.DeveloperGame;
import model.Genre;
import model.OfficialGame;
import model.RatingOfGame;
import model.Review;
import model.Screenshot;
import model.UserAccount;
import model.ViewGameByUser;

@Stateless
public class UpdaterBean implements UpdaterBeanRemote {

	@PersistenceContext(unitName="EjbComponentPU")
	private EntityManager em;
	
	@Override
	public void addUser(UserAccount user) {
		em.persist(user);
	}  
	
	
	@Override
	public void addGame(OfficialGame game) {
		em.persist(game);
	}
	
	@Override
	public void addDeveloperGame(DeveloperGame game) {
		em.persist(game);
	}
	
	
	@Override
	public void addGenre(Genre genre) {
		em.persist(genre);
	}
	
	
	@Override
	public void addVGBU(ViewGameByUser vgbu) {
		em.persist(vgbu);
	}
	
	
	@Override
	public void addScreenshot(Screenshot screen) {
		em.persist(screen);
	}
	
	
	@Override
	public void addComment(Comment comment) {
		em.persist(comment);
	}
	
	
	@Override
	public void addRatingOfGame(RatingOfGame rog) {

		em.persist(rog);
		
	}
	
	@Override
	public RatingOfGame updateRatingOfGame(RatingOfGame rog) {
		return em.merge(rog);
	}
	
	
	@Override
	public void addReview(Review review) {
		em.persist(review);
	}
	
}
