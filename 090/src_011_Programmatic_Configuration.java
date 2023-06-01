package the.package;

import java.io.Serializable;

import jakarta.enterprise.inject.Produces;
import jakarta.faces.flow.Flow;
import jakarta.faces.flow.builder.FlowBuilder;
import jakarta.faces.flow.builder.FlowBuilderParameter;
import jakarta.faces.flow.builder.FlowDefinition;

// The name implies that the flow files are inside 
// folder "flow-a"
public class FlowAFlow implements Serializable {
  private static final long serialVersionUID = 1586568679L;

  @Produces
  @FlowDefinition
  public Flow defineFlow(@FlowBuilderParameter FlowBuilder 
        flowBuilder) {

    // Specify a flow start page
    String flowId = "flow-a";
    flowBuilder.id("", flowId);
    flowBuilder.viewNode(flowId, "/" + flowId + 
          "/flow-a-start.xhtml").markAsStartNode();
   
    // Specify a flow return page
    flowBuilder.returnNode("returnFromFlow").
          fromOutcome("/start");

    return flowBuilder.getFlow();
  }
}
