package mx.com.cursodia.javaEE2022.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public final class JsonUtil
{
	private static final Gson GSON = new GsonBuilder().serializeNulls().create();

	private JsonUtil() {}

	public static void writeJson(HttpServletResponse response, int status, Object body) throws IOException
	{
		response.setStatus(status);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(GSON.toJson(body));
		out.flush();
	}

	public static void writeError(HttpServletResponse response, int status, String mensaje) throws IOException
	{
		Map<String, Object> error = new LinkedHashMap<>();
		error.put("error", true);
		error.put("mensaje", mensaje);
		writeJson(response, status, error);
	}

	public static JsonObject readJsonObject(HttpServletRequest request) throws IOException, JsonSyntaxException
	{
		StringBuilder sb = new StringBuilder();
		try(BufferedReader reader = request.getReader())
		{
			String line;
			while((line = reader.readLine()) != null)
			{
				sb.append(line);
			}
		}
		String raw = sb.toString().trim();
		if(raw.isEmpty())
		{
			return new JsonObject();
		}
		return JsonParser.parseString(raw).getAsJsonObject();
	}

	public static Integer getInt(JsonObject json, String key)
	{
		if(json == null || !json.has(key) || json.get(key).isJsonNull())
		{
			return null;
		}
		return json.get(key).getAsInt();
	}

	public static Float getFloat(JsonObject json, String key)
	{
		if(json == null || !json.has(key) || json.get(key).isJsonNull())
		{
			return null;
		}
		return json.get(key).getAsFloat();
	}
}
