package testdata;
import com.github.javafaker.Faker;
import java.util.Locale;
import static utils.RandomUtils.*;

public class TestData {

    static Faker faker = new Faker();
    static Faker fakerRu = new Faker(new Locale("ru"));

    // Valid test data for TextBoxTests
    public static String userName = fakerRu.name().fullName();
    public static String userEmail = faker.internet().emailAddress();
    public static String currentAddress = fakerRu.address().fullAddress();
    public static String permanentAddress = fakerRu.address().fullAddress();

    // Invalid test data for TextBoxTests
    public static String notValidEmail = fakerRu.name().fullName();


    // Valid test data for StudentRegistrationFormTests
    public static String name = fakerRu.name().firstName();
    public static String surname = fakerRu.name().lastName();
    public static String email = faker.internet().emailAddress();
    public static String gender = faker.options().option("Female", "Male", "Other");
    public static String genderF = "Female";
    public static String genderM = "Male";
    public static String phoneNumber = generatePhoneNumber(10);
    public static int dayOfBirth = faker.number().numberBetween(1, 29);
    public static String monthOfBirth = faker.options().option(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    );
    public static String yearOfBirth = String.valueOf(
            faker.number().numberBetween(1950, 2010));
    public static String subjects = faker.options().option(
            "Maths",
            "Arts",
            "Accounting",
            "Biology",
            "Chemistry",
            "Commerce",
            "Civics",
            "Computer Science",
            "Economics",
            "English",
            "Hindi",
            "History",
            "Physics",
            "Social Studies"
    );
    public static String hobbies = faker.options().option(
            "Sports", "Reading", "Music"
    );
    public static String imgText = "for_test.jpeg";
    public static String address = fakerRu.address().fullAddress();
    public static String state = faker.options().option(
            "NCR", "Uttar Pradesh", "Haryana", "Rajasthan"
    );
    public static String city = generateCity(state);
    public static String successfulMessage = "Thanks for submitting the form";

    // Invalid test data for StudentRegistrationFormTests
    public static String invalidPhoneNumber = generatePhoneNumber(11);


}
