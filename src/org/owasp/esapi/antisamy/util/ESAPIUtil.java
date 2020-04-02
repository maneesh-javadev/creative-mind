package org.owasp.esapi.antisamy.util;

import java.io.File;

import org.owasp.validator.html.Policy;

public class ESAPIUtil {
	public static Policy loadPolicy(String name) {
		Policy loadedPolicy = null;
		try {
			//URL url = new URL("/owasp_demo/WEB-INF/"+name);
			loadedPolicy = Policy.getInstance(new File(name));
			/*
			if (url == null || url.getFile() == null) {
				throw new FileNotFoundException("classpath file '" + name
						+ "' was not found!");
				
			}
			*/
			
			//System.out.println("url.getFile()=="+url.getFile());
			//Policy loadedPolicy = Policy.getInstance(url.getFile("c:/"+name));
			

			return loadedPolicy;
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}
}
