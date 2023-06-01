  // Thread thr = new Thread(); // NO, NOT IN JAKARTA EE!

  Thread thr = threadFactory.newThread( () -> {
          ...
      }
  );

  thr.start();
