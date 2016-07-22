package ru.coutvv.tolmach.bot;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

/**
 * Класс взаимодействия с telegram API 
 * 
 * @author lomovtsevrs
 */
public class TelegramBot  extends TelegramLongPollingBot{
	
	private Translator trans;
	
	private String token;
	
	private final String NAME = "coutvv";
	
	public TelegramBot(String propFileName) throws IOException {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(propFileName);
		Properties props = new Properties();
		props.load(in);
		token = (String) props.get("bot.token");
		String translatorKey = (String) props.get("translator.key");
		trans = new Translator(translatorKey);
	}

	public void onUpdateReceived(Update update) {
		String text = update.getMessage().getText();
		SendMessage msg = new SendMessage();
		try {
			System.out.println(trans.translate(text));
			
			msg.setText(trans.translate(text));
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

	public String getBotUsername() {
		return  NAME;
	}

	@Override
	public String getBotToken() {
		return token;
	}
	
}
