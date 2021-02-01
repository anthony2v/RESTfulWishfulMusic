package entities;

public class Artist {
    String nickName;
    String firstName;
    String lastName;
    String autoBiography;

    public Artist(String nickName, String firstName, String lastName, String autoBiography) {
        this.nickName = nickName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.autoBiography = autoBiography;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAutoBiography() {
        return autoBiography;
    }

    public void setAutoBiography(String autoBiography) {
        this.autoBiography = autoBiography;
    }
}
