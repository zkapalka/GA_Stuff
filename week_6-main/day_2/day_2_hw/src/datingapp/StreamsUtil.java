package datingapp;

import java.util.List;
import java.util.stream.Collectors;

public class StreamsUtil {

    List<Profile> profiles = ProfileManager.getProfiles();


    //Filter #1
    public static void filterAndPrintProfiles(List<Profile> profiles) {
        List<Profile> filteredProfiles = profiles.stream()
                .filter(profile -> profile.getAge() > 25 && "hiking".equals(profile.getHobby()))
                .collect(Collectors.toList());

        for (Profile profile : filteredProfiles) {
            System.out.println("Name: " + profile.getProfileName());
            System.out.println("Hobby: " + profile.getHobby());
            System.out.println("Age: " + profile.getAge());
            System.out.println();
        }
    }

    //Filter #2
    public static void fillterPplWhoLikesMovies(List<Profile> profiles) {
        List<Profile> filteredProfiles = profiles.stream()
                .filter(profile -> profile.getAge() >= 18 && profile.getAge() <= 35 && profile.getHobby().contains("movies"))
                .collect(Collectors.toList());

        for (Profile profile : filteredProfiles) {
            System.out.println("Name: " + profile.getProfileName());
            System.out.println("Hobby: " + profile.getHobby());
            System.out.println("Age: " + profile.getAge());
            System.out.println();
        }


    }

    //Filter #3
    public static void filterCookingAndPhotography(List<Profile> profiles) {
        List<Profile> filteredProfiles = profiles.stream()
                .filter(profile -> profile.getHobby().contains("cooking") || profile.getHobby().contains("photography"))
                .collect(Collectors.toList());

        for (Profile profile : filteredProfiles) {
            System.out.println("Name: " + profile.getProfileName());
            System.out.println("Hobby: " + profile.getHobby());
            System.out.println("Age: " + profile.getAge());
            System.out.println();
        }
    }

    //Filter #4
    public static void filterAndPrintRemainingProfiles(List<Profile> profiles) {
        List<Profile> remainingProfiles = profiles.stream()
                .filter(profile -> !(profile.getHobby().isEmpty()) )
                .collect(Collectors.toList());

        for (Profile profile : remainingProfiles) {
            System.out.println("Name: " + profile.getProfileName());
            System.out.println("Hobby: " + profile.getHobby());
            System.out.println("Age: " + profile.getAge());
            System.out.println();
        }
    }

    //Map #5
    public static void mapAndPrintProfileAges(List<Profile> profiles) {
        List<Integer> grabbingProfileAge = profiles.stream()
                .map(Profile::getAge)
                .collect(Collectors.toList());

        System.out.println("Age of all profiles: " + grabbingProfileAge);
    }

    //Map #6
    public static void mapAndPrintCapitalizedNames(List<Profile> profiles) {
        List<String> firstLetterNameCapitalized = profiles.stream()
                .map(profile -> profile.getProfileName().substring(0, 1).toUpperCase() + profile.getProfileName().substring(1))
                .collect(Collectors.toList());

        System.out.println("Names with first letter capitalized: " + firstLetterNameCapitalized);
    }

    // anyMatch #7
    public static void checkIfAnyoneUnder18(List<Profile> profiles) {
        boolean anyoneUnder18 = profiles.stream()
                .anyMatch(profile -> profile.getAge() < 18);

        System.out.println("Is there any profiles that are not at least 18 years of age? " + anyoneUnder18);
    }

    //Count #8
    public static void countPeopleWhoLikeMusic(List<Profile> profiles) {
        long numberOfPplWhoLikeMusic = profiles.stream()
                .filter(profile -> profile.getLikes().contains("music"))
                .count();

        System.out.println("Number of people who like music: " + numberOfPplWhoLikeMusic);
    }

    //AllMatch #9
    public static void checkIfAllProfilesAreAtLeast18(List<Profile> profiles) {
        boolean allProfilesAtLeast18 = profiles.stream()
                .allMatch(profile -> profile.getAge() >= 18);

        System.out.println("Are all profiles at least 18 years old?: " + allProfilesAtLeast18);
    }
















}

