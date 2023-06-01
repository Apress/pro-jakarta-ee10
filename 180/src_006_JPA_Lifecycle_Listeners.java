  public class MyListener {
    @PrePersist void onPrePersist(Object o) { ... }
    @PostPersist void onPostPersist(Object o) { ... }
    @PostLoad void onPostLoad(Object o) { ... }
    @PreUpdate void onPreUpdate(Object o) { ... }
    @PostUpdate void onPostUpdate(Object o) { ... }
    @PreRemove void onPreRemove(Object o) { ... }
    @PostRemove void onPostRemove(Object o) { ... }
  } 

  ...

  @Entity @EntityListeners({MyListener.class})
  public class TheJpa { ... }
