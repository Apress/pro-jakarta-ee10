  @Decorator
  public class ProcessorImplNew implements Processor {
      @Inject @Delegate private ProcessorImpl old;
	
      public void process(Invoice inv) {
          // some other code...
      }
  }
