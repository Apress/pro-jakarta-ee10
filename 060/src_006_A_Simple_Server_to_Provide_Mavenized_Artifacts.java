package book.jakartapro.marvin.ejb;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import book.jakartapro.marvin.ejb.interfaces.
      LocalMirrorLocal;

@Stateless
@Local(LocalMirrorLocal.class)
public class LocalMirror {
  static Conf conf = Conf.getInstance();
  
  public byte[] fetchBytes(String path, 
        ResponseBuilder rb) {
    String repoDir = conf.string("repoDir");
    File f = new File(repoDir + "/" + path);

    // If the file does not exist in our local (mirror)
    // repository, we contact the external (public)
    // Maven repository. This is the place where you 
    // could add filters
    if (!f.exists())
      loadExternal(path, f, rb);

    // If it still does not exist, the public repo doesn't
    // have it either (and it was not copied into our
    // mirror repo), and we output an error message. 
    // Otherwise read the bytes
    if (!f.exists()) {
      rb.type("text/plain");
      return ("Cannot load '" + path + "'\n").getBytes();
    } else {
      try {
        return IOUtils.toByteArray(
              new FileInputStream(f));
      } catch (Exception e) {
      }
      return null;
    }
  }

  ////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////
	
  private void loadExternal(String path, File tgtFile, 
        ResponseBuilder rb) {
    try {

      // Contact the external (public) repositories until
      // we find the file requested
      InputStream ins = null;
      String[] urls = conf.string("externalRepos").
            split(",");
      for (String r : urls) {
        HttpURLConnection c = (HttpURLConnection) 
              new URL(r + "/" + path).openConnection();
        c.setRequestProperty("User-Agent", "Marvin");

        int respCode = c.getResponseCode();
        rb.status(respCode);

        if (respCode != 200)
          continue; // not yet found

        ins = c.getInputStream();
        break;
      }

      if (ins != null)
        writeToMirror(ins, tgtFile);
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }
  }

  // Mirror the file in our local repo
  private void writeToMirror(InputStream ins, 
        File tgtFile) throws Exception {
    tgtFile.getParentFile().mkdirs();
    FileUtils.copyInputStreamToFile(ins, tgtFile);
  }
}
