package com.tianyisoft.apidoc.validators.requiredIf;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RequiredIfValidator implements ConstraintValidator<RequiredIf, Object> {
    String field;
    String compareField;
    boolean bool;

    @Override
    public void initialize(RequiredIf constraintAnnotation) {
        field = constraintAnnotation.field();
        compareField = constraintAnnotation.compareField();
        bool = constraintAnnotation.bool();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(value);
        Object fieldValue = beanWrapper.getPropertyValue(field);
        Object compareFieldValue = beanWrapper.getPropertyValue(compareField);
        if ( compareFieldValue != null && bool == (Boolean) compareFieldValue) {
            return fieldValue != null;
        }
        return true;
    }
}
