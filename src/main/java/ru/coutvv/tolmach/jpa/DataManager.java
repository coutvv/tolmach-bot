package ru.coutvv.tolmach.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
}
