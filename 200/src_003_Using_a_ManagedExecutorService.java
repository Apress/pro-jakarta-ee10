  Future<?> f1 = execService.submit( () -> {
          // not returning anything, corresponds
          // to a Runnable
          ...
      }
  );
		
  Future<Integer> f2 = execService.submit( () -> {
          // not returning anything, corresponds
          // to a Runnable
          ...
      }, 42 /* calculated outside the task */
  );
		
  Future<Integer> f3 = execService.submit( () -> {
          // returning something, corresponds
          // to a Callable
          ...
          return 42;
      }
  );
