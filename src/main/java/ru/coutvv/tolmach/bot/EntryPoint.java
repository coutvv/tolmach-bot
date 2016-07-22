package ru.coutvv.tolmach.bot;

import java.io.IOException;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;

/**
 * Входная точка
 * 
 * @author lomovtsevrs
 */
public class EntryPoint {
	
	private static final String PROPERTIES = "bot.properties";

	public static void main(String[] args) throws IOException, TelegramApiException {
		TelegramBot bot = new TelegramBot(PROPERTIES);
		TelegramBotsApi api = new TelegramBotsApi();
		api.registerBot(bot);
		System.out.println("Tolmach started");
	}
}
