package ru.coutvv.tolmach.bot;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import ru.coutvv.tolmach.dictionary.Dictionary;
import ru.coutvv.tolmach.jpa.entity.Word;
import ru.coutvv.tolmach.utils.Translator;

/**
 * Класс взаимодействия с telegram API 
 * 
 * @author lomovtsevrs
 */
public class TolmachBot  extends TelegramLongPollingBot{
	
	/**
	 * Переводчик
	 */
	private Translator trans;
	
	private Dictionary dictionary;
	
	private String token;
	
	private final String NAME = "coutvv";
	
	public TolmachBot(String propFileName) throws IOException {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(propFileName);
		Properties props = new Properties();
		props.load(in);
		token = (String) props.get("bot.token");
		String translatorKey = (String) props.get("translator.key");
		trans = new Translator(translatorKey);
		dictionary = new Dictionary();
	}
	
	private static final Logger logger = Logger.getLogger(TolmachBot.class);
	
	/**
	 * Логгируем всё что пришло от пользователей
	 * @param update
	 */
	private void log(Update update) {
		Message msg  = update.getMessage();
		String username = msg.getFrom().getUserName(),
				name = msg.getFrom().getFirstName(),
				lastName = msg.getFrom().getLastName();
		logger.info("### Сообщение от " + username + "(" + name + " " + lastName + ")" + " с запросом перевода слова: " + msg.getText() + "###");
	}

	@Override
	public void onUpdateReceived(Update update) {
		log(update);
		String text = update.getMessage().getText();
		SendMessage msg = new SendMessage();
		try {
			String sendText;
			if(dictionary.hasWord(text)) {
				String lang = dictionary.detectLang(text);
				Word word = dictionary.getTranslate(text);
				sendText = lang.equals("en") ? word.getRu() : word.getEn(); 
				sendText += "\n\n [Это слово уже есть в словаре]";
			} else {
				sendText = trans.translate(text);
				String lang = trans.detectLang(text);
				if(lang.equals("en")) {
					dictionary.addWord(sendText, text);
					sendText += "\n\n [Слово добавлено в словарь]";
				} else if(lang.equals("ru")){
					dictionary.addWord(text, sendText);
					sendText += "\n\n [Слово добавлено в словарь]";
				}
				
			}
			logger.info("Перевод: " + sendText);
			msg.setText(sendText);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String chatId = update.getMessage().getChatId().toString();
		msg.setChatId(chatId);
		try {
			this.sendMessage(msg);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getBotUsername() {
		return  NAME;
	}

	@Override
	public String getBotToken() {
		return token;
	}
	
}
