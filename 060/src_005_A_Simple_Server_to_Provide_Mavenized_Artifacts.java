package book.jakartapro.marvin.ejb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.
      Configurations;
import org.apache.commons.configuration2.ex.
      ConfigurationException;
import org.apache.commons.io.IOUtils;

public class Conf {
  private static Conf INSTANCE = null;
  public static Conf getInstance() {
  if(INSTANCE == null) INSTANCE = new Conf();
    return INSTANCE;
  }

  private Configuration c;
	
  private Conf() {
    try {
      Configurations configs = new Configurations();
		
      // From Class, the path is relative to the package
      // of the class unless you include a leading slash, 
      // so if you don't want to use the current package, 
      // include a slash like this:
      InputStream ins = this.getClass().
          getResourceAsStream("/marvin.properties");
			
      File f = File.createTempFile("marvin-x48", null);
      IOUtils.copy(ins, new FileOutputStream(f));
      c = configs.properties(f);
      f.delete();
    } catch (ConfigurationException | IOException e) {
      e.printStackTrace(System.err);
    }
  }

  public String string(String key) {
    return c.getString(key);
  }
}
