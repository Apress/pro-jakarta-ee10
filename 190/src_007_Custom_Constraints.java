package book.jakartapro.restdate;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, 
          CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { Pzn8Validator.class })
public @interface PZN8 {
    String message() default "Not a PZN-8";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    
    boolean includeDelimiters() default false;    
}
