package ru.coutvv.tolmach.utils;

import ru.coutvv.tolmach.jpa.DataManager;
import ru.coutvv.tolmach.jpa.entity.Word;

/**
 * Класс отвечающий за хранение и получение слов из локальной базы
 * 
 * @author lomovtsevrs
 */
public class Dictionary {
	
	private DataManager dm; 
	
	public Dictionary() {
		dm = new DataManager();
	}
	
	public void addWord(String ru, String en) { 
		Word word = new Word(ru, en); 
		dm.saveWord(word);
	}
	
	public boolean hasWord(String word) {
		Word ru = dm.getWordByRu(word),
			 en = dm.getWordByEn(word);
		return ru != null || en != null; 
	}
	
	public String detectLang(String word) {
		String result = "";
		Word ru = dm.getWordByRu(word),
			 en = dm.getWordByEn(word);
		if(ru != null) result = "ru";
		else if(en != null) result = "en";
		
		return result;
	}
	
	
	public String getRuTranslate(String word) {
		Word ru = dm.getWordByRu(word);
		return ru == null ? null : ru.getEn();
	}
	
	public String getEnTranslate(String word) {
		Word en = dm.getWordByEn(word);
		return en == null ? null : en.getRu();
	}
	
	public Word getTranslate(String word) { 
		Word result = dm.getWordByEn(word);
		if(result == null) result = dm.getWordByRu(word);
		return result;
	}
}
