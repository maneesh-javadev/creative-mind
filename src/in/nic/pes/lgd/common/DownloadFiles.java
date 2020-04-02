package in.nic.pes.lgd.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

public class DownloadFiles {
	private static final Logger log = Logger.getLogger(DownloadFiles.class);
	
	public byte[] getFileContent(String fileLocation) throws IOException {

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		try {
			File dir = new File(fileLocation);
			if (dir.exists()) {
				if(log.isInfoEnabled()) {
					log.info("File found...");
				}
				InputStream inStream = new FileInputStream(dir);
				copy(inStream, outStream);
				inStream.close();
				outStream.close();
			} else {
				if(log.isInfoEnabled()) {
					log.info("File not found...");
				}
				throw new FileNotFoundException("File not found !!");
			}
		} catch (Exception e) {
			log.error("File not found..");
		}
		return outStream.toByteArray();
	}

	private void copy(InputStream source, OutputStream destination) throws IOException {
		try {
			// Transfer bytes from source to destination
			byte[] buf = new byte[1024];
			int len;
			while ((len = source.read(buf)) > 0) {
				destination.write(buf, 0, len);
			}
			source.close();
			destination.close();
		} catch (IOException ioe) {
			throw ioe;
		}
	}
}
