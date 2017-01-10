package ru.coutvv.tolmach.test;

import org.junit.Test;

import junit.framework.TestCase;
import ru.coutvv.tolmach.jpa.DataManager;
import ru.coutvv.tolmach.jpa.entity.User;
import ru.coutvv.tolmach.jpa.entity.UserWord;
import ru.coutvv.tolmach.jpa.entity.Word;

public class TestHibernate extends TestCase {
	
	@Test
	public void testDb() {
		DataManager dm = new DataManager();
		saveData(dm);
		System.out.println(dm.getWordByEn("Bitch"));
		
		for(UserWord uw : dm.getUserWords("StupidBitch")) {
			System.out.println(uw.getWord());
		}
	}
	
	private void saveData(DataManager dm) {
		Word w = new Word();
		w.setEn("Bitch".toLowerCase());
		w.setRu("Сука".toLowerCase());
		dm.saveWord(w);
		w = new Word();
		w.setEn("Fuck".toLowerCase());
		w.setRu("Блять".toLowerCase());
		dm.saveWord(w);
		w = new Word();
		w.setEn("Cunt".toLowerCase());
		w.setRu("Пизда".toLowerCase());
		dm.saveWord(w);
		User u = new User("StupidBitch", "Little", "Girl");
		dm.saveUser(u);
		w = dm.getWordByEn("Cunt");
		UserWord uw = new UserWord(u, w);
		dm.saveUserWord(uw);
	}
}
