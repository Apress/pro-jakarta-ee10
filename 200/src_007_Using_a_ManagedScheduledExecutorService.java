  ScheduledFuture<Integer> sf1 = schedExecService.
      schedule( () -> {
          ...
          return 42;
      }, 5L, TimeUnit.SECONDS);
		
  // or, if you don't need a result:
  ScheduledFuture<?> sf2 = schedExecService.
      schedule( () -> 
          ...
      }, 5L, TimeUnit.SECONDS);
