package ru.coutvv.tolmach.test;

import junit.framework.TestCase;
import ru.coutvv.tolmach.jpa.DataManager;
import ru.coutvv.tolmach.jpa.entity.Word;

public class TestHibernate extends TestCase {
	public void testDb() {
		DataManager dm = new DataManager();
//		saveData(dm);
		System.out.println(dm.getWordByEn("Bitch"));
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
	}
}
