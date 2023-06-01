  @Resource(name = "java:comp/DefaultManagedThreadFactory")
  private ManagedThreadFactory threadFactory;

  ...or...

  ManagedThreadFactory threadFactory = 
      InitialContext.
      doLookup(
        "java:comp/DefaultManagedThreadFactory"
      );
