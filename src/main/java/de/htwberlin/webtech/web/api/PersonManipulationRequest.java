package de.htwberlin.webtech.web.api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class PersonManipulationRequest {


    private String firstname;
    private String lastname;
    private String email;

    private String password;

    public PersonManipulationRequest( String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        this.password = Arrays.toString(hash);

    }

    public PersonManipulationRequest(){}

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte hashBytes[] = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
        BigInteger noHash = new BigInteger(1, hashBytes);
        String hashStr = noHash.toString(16);
        this.password = hashStr;
    }
}
