  import jakarta.interceptor.*;
  import jakarta.annotation.PostConstruct;
  import jakarta.annotation.PreDestroy;

  @Interceptor
  @TracingInterceptor
  public class TracingInterceptorSys {
      @AroundInvoke
      public Object logMethodEntry(InvocationContext ctx) 
            throws Exception {
          System.out.println("Before entering method: " +
                ctx.getMethod().getName());

          // We call the next interceptor in the
          // interceptor chain.
          return ctx.proceed();
      }

      @AroundConstruct
      public void aroundConstruct(InvocationContext ctx) {
          ...
          ctx.proceed();
      }

      /*or*/
      @AroundConstruct
      public Object aroundConstruct(InvocationContext ctx) 
              throws Exception {
          ...
          return ctx.proceed();
      }

      @AroundTimeout
      public Object aroundTimeout(InvocationContext ctx) 
              throws Exception {
          ...
          return ctx.proceed();
      }

      @PostConstruct
      public void postConstruct(InvocationContext ctx) {
          ...
          ctx.proceed();
      }

      @PreDestroy
      public void preDestroy(InvocationContext ctx) {
          ...
          ctx.proceed();
      }
  }
