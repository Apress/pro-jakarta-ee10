  ...
  // waits until background task finishes
  f1.get();
  ...

  ...
  // waits until background task finishes
  Integer backgroundRes = f2.get();
  ...

  // waits until all background tasks finish
  for (Future<Integer> taskResult : taskResults) {
    try {
      Integer ii = taskResult.get();
      // do something with the res...
    } catch (ExecutionException e) {
      e.printStackTrace(System.err);
    }
  }
