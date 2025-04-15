package frontend;

public class Validations {
    public static boolean isTrainerIdValid(String id) {
        return id.charAt(0) == 'T' && id.length() == 4;
    }
    public static boolean isValidMemberId(String id) {
        return id.charAt(0) == 'M' && id.length() == 4;
    }

    public static boolean isValidClassId(String id) {
        return id.charAt(0) == 'C' && id.length() == 4;
    }

    public static boolean isValidClassName(String name) {
        return isNameValid(name);
    }

    public static boolean isValidSpecialty(String specialty) {
        return isNameValid(specialty);
    }
    public static boolean isPhoneValid(String phone) {

        if(phone.length() != 10) {
            return false;
        }
        for(int i = 0; i < phone.length(); i++) {
            if(!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmailValid(String email) {
        return email.contains("@") && email.contains(".");
    }
    public static boolean isNameValid(String name) {
        for(int i = 0; i < name.length(); i++) {
            if(Character.isDigit(name.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumberValid(String duration) {
        for (int i = 0; i < duration.length(); i++) {
            if (!Character.isDigit(duration.charAt(i))) {
                return false;
            }
        }
        if (Integer.parseInt(duration) <= 0) {
            return false;
        }
        return true;
    }




}
