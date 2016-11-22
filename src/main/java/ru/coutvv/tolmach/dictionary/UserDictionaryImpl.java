package ru.coutvv.tolmach.dictionary;

import ru.coutvv.tolmach.jpa.DataManager;
import ru.coutvv.tolmach.jpa.entity.User;
import ru.coutvv.tolmach.jpa.entity.UserWord;
import ru.coutvv.tolmach.jpa.entity.Word;

public class UserDictionaryImpl implements UserDictionary {

	private DataManager dm = new DataManager();
	
	public UserDictionaryImpl(String username) {
		this.user = dm.getUserByUsername(username);
	}
	
	private User user;
	
	@Override
	public void addWord(String ru, String en) {
		Word word;
		if(dm.hasWord(en) || dm.hasWord(ru)) {
			word = dm.getWordByEn(en);
		} else {
			word = new Word(ru, en);
			dm.saveWord(word);
		}
		UserWord uw = new UserWord(user, word);
		dm.saveUserWord(uw);
	}
	
//	public Word getRuWord(String ru) {
//		UserWord w = dm.getUserWordByRuWord(ru, user.getUsername());
//		return w!=null ? w.getWord() : null;
//	}
//	
//	public Word getEnWord(String en) {
//		UserWord w = dm.getUserWordByEnWord(en, user.getUsername());
//		return w!=null ? w.getWord() : null;
//	}

}
