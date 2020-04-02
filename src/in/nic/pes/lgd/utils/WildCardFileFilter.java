package in.nic.pes.lgd.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.regex.Pattern;

public class WildCardFileFilter implements FileFilter {
	private String _pattern;

	private WildCardFileFilter(String pattern) {
		_pattern = pattern.replace("*", ".*").replace("?", ".");
	}

	public boolean accept(File file) {
		return Pattern.compile(_pattern).matcher(file.getName()).find();
	}
	
	public static File[] getFiles(String fileName) throws IOException {
		File dir = new File(ApplicationConstant.getDefaultFilePath());
		String pattern = "*.ser";
		if(fileName != null && !fileName.contains(".")) {
			pattern = fileName+".*";
		} else if(fileName != null) {
			pattern = fileName;
		}
		File[] files = dir.listFiles(new WildCardFileFilter(pattern));
		return files;
	}
}