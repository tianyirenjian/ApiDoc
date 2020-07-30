package com.tianyisoft.apidoc.validators.requiredIf;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RequiredIfValidator.class)
@Documented
public @interface RequiredIf {
    String field();
    String compareField();
    boolean bool();

    String message() default "{com.tianyisoft.apidoc.validators.requiredIf.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
