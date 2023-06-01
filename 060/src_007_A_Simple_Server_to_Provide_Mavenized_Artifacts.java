package book.jakartapro.marvin.ejb.interfaces;

import jakarta.ws.rs.core.Response.ResponseBuilder;

public interface LocalMirrorLocal {
  byte[] fetchBytes(String path, ResponseBuilder rb);
}
