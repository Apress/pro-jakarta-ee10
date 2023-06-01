    // inside the calling flow:
    flowBuilder.flowCallNode("call-flow-b").
          flowReference("", "flow-b").
          outboundParameter("param1FromCallingFlow", 
                "the value");

    // inside the called flow:
    flowBuilder.inboundParameter("param1FromCallingFlow", 
                "#{someFlowBean.value}");
