package com.github.union.validation;

import com.github.union.model.Shop;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ShopValidator implements Validator {
    private final static String EMPLOYEES_NUMBER = "employeesNumber";

    @Override
    public boolean supports(Class<?> aClass) {
        return Shop.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Shop shop = (Shop) o;

        Integer employeesNumber = shop.getEmployeesNumber();

        ValidationUtils.rejectIfEmpty(errors, "name", "shop.name.empty");
        ValidationUtils.rejectIfEmpty(errors, EMPLOYEES_NUMBER, "shop.employeesNumber.empty");

        if (employeesNumber != null && employeesNumber < 1) {
            errors.rejectValue(EMPLOYEES_NUMBER, "shop.employeesNumber.lessThenOne");
        }
    }
}