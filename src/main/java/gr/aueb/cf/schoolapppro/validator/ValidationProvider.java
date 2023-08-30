package gr.aueb.cf.schoolapppro.validator;

import gr.aueb.cf.schoolapppro.dto.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class ValidationProvider {

    private ValidationProvider() {}

    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]{5,20}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#^])[A-Za-z\\d@$!%*?&#^]{8,30}$";
    private static final String NAME_PATTERN = "^[A-Za-z]{2,50}$";

    private static final Pattern NAME_REGEX = Pattern.compile(NAME_PATTERN);
    private static final Pattern USERNAME_REGEX = Pattern.compile(USERNAME_PATTERN);
    private static final Pattern PASSWORD_REGEX = Pattern.compile(PASSWORD_PATTERN);

    private static boolean isUsernameValid(String username) {
        return username != null && USERNAME_REGEX.matcher(username).matches();
    }

    private static boolean isPasswordValid(String password) {
        return password != null && PASSWORD_REGEX.matcher(password).matches();
    }

    public static boolean areUserRegisterCredentialsValid(UserRegisterDTO userRegisterDTO) {
        return isUsernameValid(userRegisterDTO.getUsername()) && isPasswordValid(userRegisterDTO.getPassword())
                                    && (userRegisterDTO.getPassword() == userRegisterDTO.getConfirmationPassword());
    }

    public static boolean areUserInsertCredentialsValid(UserInsertDTO userInsertDTO) {
        return isUsernameValid(userInsertDTO.getUsername()) && isPasswordValid(userInsertDTO.getPassword());
    }

    public static boolean areUserInsertCredentialsValid(UserUpdateDTO userUpdateDTO) {
        return isUsernameValid(userUpdateDTO.getUsername()) && isPasswordValid(userUpdateDTO.getPassword());
    }

    private static boolean isNameValid(String name) {
        return name != null && NAME_REGEX.matcher(name).matches();
    }

    private static boolean isBirthdateValid(LocalDate birthdate) {
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthdate, currentDate).getYears();
        return age > 18 && age < 100;
    }

    public static boolean isTeacherValid(TeacherInsertDTO teacherInsertDTO) {
        return isNameValid(teacherInsertDTO.getFirstname()) && isNameValid(teacherInsertDTO.getLastname());
    }

    public static boolean isStudentValid(StudentInsertDTO studentInsertDTO) {
        return isNameValid(studentInsertDTO.getFirstname()) &&
                isNameValid(studentInsertDTO.getLastname()) &&
                isBirthdateValid(studentInsertDTO.getBirthDate());
    }



}
