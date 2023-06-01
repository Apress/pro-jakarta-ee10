  @Resource(name="java:comp/DefaultManagedExecutorService")
  private ManagedExecutorService execService;

  @Resource
  private UserTransaction ut;

  ...
  public void someMethod() {
      Future<?> f1 = execService.submit( new Runnable() {
          public void run() {
              ut.begin();
              try{
                  ...
                  ut.commit();
              }catch(Exception e){
                  ut.rollback();
              }
      }});
      ...
  }
