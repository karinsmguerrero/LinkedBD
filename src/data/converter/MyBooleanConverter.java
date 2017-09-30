package data.converter;


import javafx.util.converter.BooleanStringConverter;

public class MyBooleanConverter extends BooleanStringConverter {


    @Override
    public Boolean fromString(final String value) {
        return value.isEmpty() || !isNumber(value) ? null
                : super.fromString(value);
    }

    public boolean isNumber(String value) {
        int size = value.length();
        for (int i = 0; i < size; i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return size > 0;
    }


}
