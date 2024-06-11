package datingapp;

public class Profile {

    //Declaring the variables
    private String profileName;
    private String profileDescription;
    private String hobby;
    private int age;
    private String likes;
    private Boolean superUser;

    //Constructor
    public Profile(String profileName, String profileDescription, String hobby, int age, String likes, boolean superUser) {
        this.profileName = profileName;
        this.profileDescription = profileDescription;
        this.hobby = hobby;
        this.age = age;
        this.likes = likes;
        this.superUser = superUser;
    }

    // Constructor with no superUser
    public Profile(String profileName, String profileDescription, String hobby, int age, String likes) {
        this(profileName, profileDescription, hobby, age, likes, false); // Call the other constructor with default value for superUser
    }

    //Getters
    public String getProfileName() {
        return profileName;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public String getHobby() {
        return hobby;
    }

    public Integer getAge() {
        return age;
    }

    public String getLikes() {
        return likes;
    }

    public Boolean getSuperUser() {
        return superUser;
    }

    //Setters
    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public void setSuperUser(Boolean superUser) {
        this.superUser = superUser;
    }

    //Creating a method to filter for all fully completed profiles
    public static boolean isFullyCompleted(Profile profile) {
        return !profile.getProfileName().isEmpty() &&
                !profile.getProfileDescription().isEmpty() &&
                !profile.getHobby().isEmpty() &&
                profile.getAge() != null &&   // Check if age is not null
                profile.getAge() != 0 &&      // Check if age is not equal to 0
                !profile.getLikes().isEmpty();
    }
}
