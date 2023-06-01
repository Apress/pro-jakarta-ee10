  import java.lang.annotation.Retention;
  import java.lang.annotation.Target;
  import static java.lang.annotation.RetentionPolicy.*;
  import static java.lang.annotation.ElementType.*;
  import jakarta.interceptor.InterceptorBinding;

  @InterceptorBinding
  @Retention(RUNTIME)
  @Target({TYPE, METHOD, FIELD})
  public @interface TracingInterceptor { }
