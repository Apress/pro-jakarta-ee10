ThreadPoolExecutor exec = new ThreadPoolExecutor(
      5, 10, 5, TimeUnit.SECONDS,
   new ArrayBlockingQueue<Runnable>(10), 
   threadFactory);
