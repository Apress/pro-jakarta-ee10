    builder.addNull("elementName");

    builder.
        add("element1", 
            Json.createObjectBuilder().
                add("inner1", 42).
                add("inner2", "deepThought")).
        add("element2", 3.1415);
