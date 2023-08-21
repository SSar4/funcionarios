package com.saraworks.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AutorizacaoFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		Long user = null;
		if (request.getRequestURI().endsWith("/faces/pessoa.xhtml")) {

			chain.doFilter(req, res);
			System.err.println("index filtro");
		} else {
			try {
				user = (Long) session.getAttribute("pessoa");
			} catch (Exception e) {

				response.sendRedirect(request.getContextPath() + "/faces/pessoa.xhtml");
			}

			if (user != null) {
				System.out.print(user);
				chain.doFilter(req, res);
			} else {

				response.sendRedirect(request.getContextPath() + "/faces/pessoa.xhtml");

			}
		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}