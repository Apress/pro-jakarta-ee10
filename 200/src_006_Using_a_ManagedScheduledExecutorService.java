  @Resource(name=
    "java:comp/DefaultManagedScheduledExecutorService")
  private ManagedScheduledExecutorService 
    schedExecService;

  ...or...

  ManagedScheduledExecutorService schedExecService = 
      InitialContext.
      doLookup(
        "java:comp/DefaultManagedScheduledExecutorService"
      );
