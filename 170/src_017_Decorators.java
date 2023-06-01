  public interface Processor {
      void process(Invoice inv);
  }

  // --------------------------------------------

  public class ProcessorImpl implements Processor {
      public void process(Invoice inv) {
          // some code...
      }
  }
