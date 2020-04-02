package in.nic.pes.lgd.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
public class NoCacheFilter implements Filter {
	@SuppressWarnings("unused")
	private FilterConfig filterConfig = null;
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
		//System.out.println(" ----> inside no cache filter");
	}

	public void destroy() {
		//System.out.println(" ----> exiting no-cache filter");
		this.filterConfig = null;
	}

	/**
	 * Modifies the response. Sets the appropriate headers
	 * and invoke the next filter in the chain:
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
	FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate, max-age=0, post-check=0, pre-check=0");
		httpResponse.setDateHeader("Expires", 0);
		httpResponse.setHeader("Pragma", "No-cache");
		chain.doFilter(request, response);
	}
}
