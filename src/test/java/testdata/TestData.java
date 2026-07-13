package testdata;

import com.github.javafaker.Faker;

import java.util.Locale;

import static utils.RandomUtils.generateCity;
import static utils.RandomUtils.generatePhoneNumber;

public class TestData {

    private final Faker faker = new Faker();
    private final Faker fakerRu = new Faker(new Locale("ru"));

    // Valid test data for TextBoxTests
    public String userName = fakerRu.name().fullName();
    public String userEmail = faker.internet().emailAddress();
    public String currentAddress = fakerRu.address().fullAddress();
    public String permanentAddress = fakerRu.address().fullAddress();

    // Invalid test data for TextBoxTests
    public String notValidEmail = fakerRu.name().fullName();


    // Valid test data for StudentRegistrationFormTests
    public String name = fakerRu.name().firstName();
    public String surname = fakerRu.name().lastName();
    public String email = faker.internet().emailAddress();
    public String gender = faker.options().option("Female", "Male", "Other");
    public String genderF = "Female";
    public String genderM = "Male";
    public String phoneNumber = generatePhoneNumber(10);
    public int dayOfBirth = faker.number().numberBetween(1, 29);
    public String monthOfBirth = faker.options().option(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    );
    public String yearOfBirth = String.valueOf(
            faker.number().numberBetween(1950, 2010));
    public String subjects = faker.options().option(
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
    public String hobbies = faker.options().option(
            "Sports", "Reading", "Music"
    );
    public String imgText = "for_test.jpeg";
    public String address = fakerRu.address().fullAddress();
    public String state = faker.options().option(
            "NCR", "Uttar Pradesh", "Haryana", "Rajasthan"
    );
    public String city = generateCity(state);
    public String successfulMessage = "Thanks for submitting the form";

    // Invalid test data for StudentRegistrationFormTests
    public String invalidPhoneNumber = generatePhoneNumber(11);


}
