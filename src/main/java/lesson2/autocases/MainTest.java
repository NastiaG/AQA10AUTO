/* Task 1. Автоматизируйте по два тест-кейса из каждого модуля,
которые вы писали для предыдущего домашнего задания.*/

package lesson2.autocases;

import static lesson2.autocases.TestDeletingAccount.testDeletingAccount;
import static lesson2.autocases.TestEditing.testEditing;
import static lesson2.autocases.TestRegistration.testRegistration;
import static lesson2.autocases.TestSigning.testSigning;

public class MainTest {
    public static void main(String[] args) throws InterruptedException {
        String firstName = "Anastasia";
        String lastName = "Homazava";
        String dateOfBirth = "02/07/1989";
        String email = "nastia.gomozova@gmail.com";
        String password = "Test2024";
        String confirmPassword = "Test2024";

        String badFirstName = "$%^&*#$@:\"";
        String badLastName = "\":%$^##";
        String badDateOfBirth = "26/02/2024";
        String badEmail = "fff@fff.fff";
        String badPassword = "1 234567";
        String badConfirmPassword = "1 234567";

        //RC01 Registration of a non-existent user using correct data (latin and special characters, numbers)
        testRegistration(firstName, lastName, dateOfBirth, email, password, confirmPassword);

        //RC04 Filling the fields with incorrect data
        testRegistration(badFirstName, badLastName, badDateOfBirth, badEmail, badPassword, badConfirmPassword);

        String testEmail = "nastia.gomozova@gmail.com";
        String badTestEmail = "nastia1.gomozova@gmail.com";
        String testPassword = "Test2024";

        //LC01 Logging in with the valid data of an existent user
        testSigning(testEmail, testPassword);

        //LC02 Logging in with the invalid email with the valid password
        testSigning(badTestEmail, testPassword);

        String newPassword = "111111111";
        String confirmNewPassword = "111111111";

        //EC02 The editing of the field New Password and logging in with the new password
        testEditing(email, password, newPassword, confirmNewPassword);

        //EC05 The deleting the account and trying to log in with the same email and password
        testDeletingAccount(email, newPassword);
        testDeletingAccount(badEmail, badPassword);
    }
}
