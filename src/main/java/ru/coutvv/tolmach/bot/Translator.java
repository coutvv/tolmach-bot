package ru.coutvv.tolmach.bot;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Класс использующий сервисы перевода Yandex Market
 * 
 * @author lomovtsevrs
 */
public class Translator {
	/**
	 * Ключ от сервиса Яндекс-маркет
	 */
	private String key;

	public Translator(String key) {
		this.key = key;
	}

	/**
	 * Простой http Get запрос
	 * 
	 * @param url
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private String request(String url) throws UnsupportedOperationException, ClientProtocolException, IOException {
		String result;
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		InputStream input = client.execute(get).getEntity().getContent();
		byte[] answer = new byte[128000];
		int i = 0;
		while (input.available() != 0 && i < 128000) {
			answer[i] = (byte) input.read();
			i++;
		}
		byte[] buf = new byte[i];
		for (int j = 0; j < i; j++) {
			buf[j] = answer[j];
		}
		result = new String(buf);
		return result;
	}

	/**
	 * Линк на сервис яндекс переводчика
	 */
	private final String YANDEX_TRANSLATE_URL = "https://translate.yandex.net/api/v1.5/tr.json/";
	
	/**
	 * Определяет к какому языку принадлежит текст
	 * 
	 * @param text
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private String detectLang(String text) throws UnsupportedOperationException, ClientProtocolException, IOException {
		text = change(text);
		String url = YANDEX_TRANSLATE_URL + "detect?key=" + key + "&text=" + text;
		String ans = request(url);
		JSONObject res = new JSONObject(ans);
		return res.getString("lang");
	}

	

	/**
	 * перевести текст
	 * 
	 * @param text
	 * @param lang
	 *            в какую сторону переводить
	 * @return
	 * @throws UnsupportedOperationException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private String translate(String text, String lang)
			throws UnsupportedOperationException, ClientProtocolException, IOException {
		String url = YANDEX_TRANSLATE_URL + "translate?key=" + key + "&text=" + text + "&lang=" + lang;

		JSONObject res = new JSONObject(request(url));
		JSONArray texts = res.getJSONArray("text");
		String result = "";
		for (int i = 0; i < texts.length() - 1; i++) {
			result = result + texts.getString(i) + "\n";
		}
		result = result + texts.getString(texts.length() - 1);// чтобы последний
																// без перехода
																// строки
		return result;
	}

	/**
	 * Перевод текста
	 * 
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public String translate(String text) throws Exception {
		String result = "";
		text = change(text);
		String lang = detectLang(text);
		if (lang.equals("ru")) {
			result = translate(text, "ru-en");
		} else if (lang.equals("en")) {
			result = translate(text, "en-ru");
		} else {
			result = "Uknown Language";
		}
		return result;
	}

	/**
	 * Функция корректировки текста перед отправкой
	 * 
	 * @param text
	 * @return
	 */
	private String change(String text) {
		text = text.replaceAll(" ", "%20").replaceAll("ё", "е");
		return text;
	}
}
