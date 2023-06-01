  // In case we want to cancel the execution:
  sf1.cancel(true);
  ...

  // Wait for the termination. The sf2 object comes from
  // a Runnable, so it can return only 'null' and we don't
  // care for the result object.
  int res1 = sf1.get();
  sf2.get();
