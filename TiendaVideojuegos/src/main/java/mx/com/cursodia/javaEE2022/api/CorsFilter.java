package mx.com.cursodia.javaEE2022.api;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Permite que Angular (otro origen/puerto) consuma /api/* durante la migracion.
 */
public class CorsFilter implements Filter
{
	@Override
	public void init(FilterConfig filterConfig) {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String origin = req.getHeader("Origin");
		if(origin == null || origin.isBlank())
		{
			origin = "*";
		}

		res.setHeader("Access-Control-Allow-Origin", origin);
		res.setHeader("Vary", "Origin");
		res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		res.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, Authorization");
		res.setHeader("Access-Control-Max-Age", "3600");

		if("OPTIONS".equalsIgnoreCase(req.getMethod()))
		{
			res.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return;
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}
}
