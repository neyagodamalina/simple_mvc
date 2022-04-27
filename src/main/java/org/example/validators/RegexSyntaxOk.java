package org.example.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RegexSyntaxValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface RegexSyntaxOk {

    String message() default "Syntax error in a regular-expression pattern";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

