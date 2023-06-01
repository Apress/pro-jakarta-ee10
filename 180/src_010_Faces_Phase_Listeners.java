package the.pac.kage;

...
public class MyPhaseListener implements PhaseListener {
  private static final long serialVersionUID = 
      -7607159318721947672L;

  // The phase where the listener is going to be called
  private PhaseId phaseId = PhaseId.RENDER_RESPONSE;

  public void beforePhase(PhaseEvent event) {
    ...
  }
	 
  public void afterPhase(PhaseEvent event) {
    ...
  }

  public PhaseId getPhaseId() {
    return phaseId;
  }	
}
