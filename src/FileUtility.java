import java.io.BufferedReader;
import java.io.FileReader;

public class FileUtility {

	public static String loadString(String filepath) {
		StringBuilder shaderString = new StringBuilder();
		String line = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filepath));
			while ((line = reader.readLine()) != null) {
				shaderString.append(line);
				shaderString.append('\n');
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to load shader from file [" + filepath + "]", e);
		}

		return shaderString.toString();
	}

}
