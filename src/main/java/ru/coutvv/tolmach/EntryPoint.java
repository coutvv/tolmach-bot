package ru.coutvv.tolmach;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;

import ru.coutvv.tolmach.bot.TelegramBot;

/**
 * Входная точка
 * 
 * @author lomovtsevrs
 */
public class EntryPoint {
	
	private static final String PROPERTIES = "bot.properties";
	
	private static final Logger logger = Logger.getLogger(EntryPoint.class);

	public static void main(String[] args) throws IOException, TelegramApiException {
		TelegramBot bot = new TelegramBot(PROPERTIES);
		TelegramBotsApi api = new TelegramBotsApi();
		api.registerBot(bot);
		logger.info("### TOLMACH WAS STARTED ###");
	}
}
