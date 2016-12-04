package marcel.IncubatorManagerFx.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	public static Integer getIncubatorSound(String incubator) {
		JSONObject json;
		try {
			json = readJsonFromUrl("https://dweet.io/get/latest/dweet/for/" + incubator);
			JSONObject content = json.getJSONArray("with").getJSONObject(0).getJSONObject("content");
			return (Integer) content.get("noise");
		} catch (JSONException | IOException e) {
			e.printStackTrace();
			return -1;
		}

	}
}
