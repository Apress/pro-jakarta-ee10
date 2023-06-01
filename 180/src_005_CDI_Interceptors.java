  import jakarta.interceptor.AroundInvoke;
  import jakarta.interceptor.Interceptor;
  import jakarta.interceptor.InvocationContext;

  @Interceptor
  public class TracingInterceptorSys {
      @AroundInvoke
      public Object logMethodEntry(InvocationContext ctx) 
            throws Exception {
          System.out.println("Before entering method: " +
                ctx.getMethod().getName());
          return ctx.proceed();
      }
  }

  // --------------------------------------------

  @Interceptors({TracingInterceptorSys.class})
  public class SomeBean {
      ...
  }
