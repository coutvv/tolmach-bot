package ru.coutvv.tolmach.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ru.coutvv.tolmach.jpa.entity.User;
import ru.coutvv.tolmach.jpa.entity.UserWord;
import ru.coutvv.tolmach.jpa.entity.Word;

/**
 * Класс отвечающий за взаимодействие с бд
 * 
 * @author lomovtsevrs
 */
public class DataManager {
	
	EntityManager em;
	
	public DataManager() {
		SessionFactory fact = new Configuration().configure().buildSessionFactory();
		em = fact.createEntityManager();
	}
	/** Словарь **/
	
	public Word getWordByRu(final String ru) {
		List<Word> words = em.createNamedQuery("Word.getRu", Word.class).setParameter("ru", ru.toLowerCase()).getResultList();
		if(words == null || words.size() == 0) return null;
		return words.get(0);
	}
	
	public Word getWordByEn(final String en) {
		List<Word> words = em.createNamedQuery("Word.getEn", Word.class).setParameter("en", en.toLowerCase()).getResultList();
		if(words == null || words.size() == 0) return null;
		return words.get(0);
	}
	
	public void saveWord(Word word) {
		word.setLowerCase();
		em.getTransaction().begin();
		em.persist(word);
		em.getTransaction().commit();
	}
	
	/** Пользователи **/
	
	public void saveUser(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}
	
	public User getUserByUsername(final String username) { 
		List<User> users = em.createNamedQuery("User.getByUsername", User.class).setParameter("username", username).getResultList();
		if(users == null || users.size() == 0) return null;
		return users.get(0);
	}
	
	public User getUserByFullName(final String name, final String lastname) {
		List<User> users = em.createNamedQuery("User.getByFullName", User.class)
				.setParameter("name", name)
				.setParameter("lastname", lastname).getResultList();
		if(users == null || users.size() == 0) return null;
		return users.get(0);
	}
	
	/** Слова принадлежащие пользователю **/
	
	public List<UserWord> getAllUserWords() {
		List<UserWord> result = em.createQuery("select * from UserWord", UserWord.class).getResultList();
		return result;
	}
	
	public List<UserWord> getUserWords(final String username) {
		List<UserWord> userWords = em.createNamedQuery("UserWord.getByUserName", UserWord.class)
				.setParameter("username", username).getResultList();
		return userWords;
	}
	
	public UserWord getUserWordByEnWord(final String en, final String username) {
		
		List<UserWord> userWords = em.createNamedQuery("UserWord.getByEnWordAndUserName", UserWord.class)
									.setParameter("username", username)
									.setParameter("en", en).getResultList();
		if(userWords == null || userWords.size() == 0) return null;
			return userWords.get(0);
	}
	public UserWord getUserWordByRuWord(final String ru, final String username) {
		
		List<UserWord> userWords = em.createNamedQuery("UserWord.getByRuWordAndUserName", UserWord.class)
									.setParameter("username", username)
									.setParameter("ru", ru).getResultList();
		if(userWords == null || userWords.size() == 0) return null;
			return userWords.get(0);
	}
	
	public void saveUserWord(UserWord uw) {
		em.getTransaction().begin();
		em.persist(uw);
		em.getTransaction().commit();
	}
}
