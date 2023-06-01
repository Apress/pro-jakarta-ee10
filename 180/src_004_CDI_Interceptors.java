  @TracingInterceptor
  public class SomeBean {
      ...
  }

  // --------------------------------------------

  public class SomeBean {
      ...
      @TracingInterceptor
      public void someMethod() {
          ...
      }
  }
