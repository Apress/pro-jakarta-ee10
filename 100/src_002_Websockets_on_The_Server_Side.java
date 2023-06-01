    // Build a data object:
    JsonProvider provider = JsonProvider.provider();
    JsonObject msg = provider.createObjectBuilder()
        .add("someField", "someValue")
        .add("someOtherField", 42)
        ...     
        .build();

    // Send it to the client:
    try {
        session.getBasicRemote().sendText(msg.toString());
    } catch (IOException ex) {
        // Handle errors. Also maybe remove the session
        // from the session storage:
        // sessions.remove(session);
    }
