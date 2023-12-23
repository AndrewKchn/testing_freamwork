package org.example;

import java.util.function.Predicate;

public class Some {


    public static void main(String[] args) {
//        ValidateStringIsNotNull validateStringIsNotNull = new ValidateStringIsNotNull();
//        System.out.println(validateStringIsNotNull.test("asdf"));
//        Class<? extends ValidateStringIsNotNull> aClass = validateStringIsNotNull.getClass();
//        System.out.println(aClass.getName());
//
//        Predicate<String> validator = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return s != null;
//            }
//        };
//        System.out.println(validator.test("asdf"));
//
        Validator validator = new Validator();
//        String target = "asdfasdf";
//        System.out.println(validator.validate(target, e -> e.length() > 0));
//
//        abstract class ValidatorAbs implements Predicate<String> {
//        }
//
//        System.out.println(validator.validate(target, new ValidatorAbs() {
//            @Override
//            public boolean test(String s) {
//                return s.length() > 0;
//            }
//        }));
//
//        System.out.println(validator.validate("asdf", s -> !s.isEmpty()));
//        System.out.println(validator.validate(123, i -> i > 0));
        Cat someCat = new Cat("Masya");

        System.out.println(validator.validate(someCat, c -> c.getName().startsWith("M")));

    }
}
