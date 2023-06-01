package book.jakartapro.javamvc.messages;

import java.util.ResourceBundle;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.mvc.MvcContext;

@RequestScoped
@Named("msg")
public class Messages {
  private static final String BASE_NAME = "messages";

  @Inject
  private MvcContext mvcContext;

  public final String get(final String key) {
    final ResourceBundle bundle = ResourceBundle.
          getBundle(BASE_NAME, mvcContext.getLocale());
    return bundle.containsKey(key) ? 
          bundle.getString(key) : formatUnknownKey(key);
  }

  private static String formatUnknownKey(
        final String key) {
    return String.format("???%s???", key);
  }
}
