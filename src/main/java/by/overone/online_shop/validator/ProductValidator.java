package by.overone.online_shop.validator;

public class ProductValidator {

    private final static String NAME_REGEX = "^[\\w]$";
    private final static String DESCRIPTION_REGEX = "^[\\w]$";
    private final static String PRICE_REGEX = "^[\\d]$";
    private final static String COUNT_REGEX = "^[\\d]$";

    private static boolean validateName(String name) {
        return name != null && !name.isBlank() && name.matches(NAME_REGEX);
    }

    private static final boolean validateDescription(String discription){
        return discription !=null && !discription.isBlank() && discription.matches(DESCRIPTION_REGEX);
    }

//    private static final boolean validatePrice(double price){
//        return price != 0 &&
//    }

//    private static final boolean validateCount(long count){
//        return count != 0 &&
//    }
}
