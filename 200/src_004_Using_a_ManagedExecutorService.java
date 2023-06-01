List<Callable<Integer>> retrieverTasks = 
      new ArrayList<Callable<Integer>>();
... fill the list ...
List<Future<Integer>> taskResults = execService.
      invokeAll(retrieverTasks);
