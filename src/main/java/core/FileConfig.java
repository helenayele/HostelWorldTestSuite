package core;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class FileConfig {
	
	private static Properties prop;
	private static FileInputStream fs;
	
	public static void loadConfig(String env) {
		String fileInfo =  System.getProperty("user.dir")
				+"//src//main//resources//config//config."+env.toLowerCase()+".properties";
		try {
			fs = new FileInputStream(new File(fileInfo));
			prop = new Properties();
			prop.load(fs);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		if(prop.containsKey(key)) {
			return prop.getProperty(key);
		}
		return null;
	}
	
	
	
	
	
	

}
