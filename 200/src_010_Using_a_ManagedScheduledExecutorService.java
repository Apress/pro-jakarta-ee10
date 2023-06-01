  int firstDelay = 5;
  int betweenDelay = 2;

  ScheduledFuture<?> f1 =
  schedExecService.scheduleAtFixedRate(() -> {
          ...			
      }, firstDelay, betweenDelay, TimeUnit.SECONDS);

  ...

  ScheduledFuture<?> f2 =
  schedExecService.scheduleWithFixedDelay(() -> {
          ...			
      }, firstDelay, betweenDelay, TimeUnit.SECONDS);  
