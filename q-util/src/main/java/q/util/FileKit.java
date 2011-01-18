package q.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class FileKit {

	/**
	 * @param file
	 * @return file total line numbers
	 * @throws IOException
	 */
	public static long getLines(File file) {
		int lines = 0;
		LineNumberReader lrf = null;
		long fileLength = file.length();
		try {
			lrf = new LineNumberReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (lrf != null) {
			try {
				lrf.skip(fileLength);
			} catch (IOException e) {
				e.printStackTrace();
			}
			lines = lrf.getLineNumber();
		}
		return lines;
	}

}
