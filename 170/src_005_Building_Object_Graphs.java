import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

...
SeContainerInitializer containerInit = 
      SeContainerInitializer.newInstance();
SeContainer container = containerInit.initialize();

Invoice proc = container.select(Invoice.class).get(); 

...
// Just for debugging / inspection, list all CDI beans 	    
container.getBeanManager().getBeans(Object.class).
    forEach(b -> {
      System.out.println(b);
    });	    
...

container.close();
