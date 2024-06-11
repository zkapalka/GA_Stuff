package datingapp;


import java.util.List;
import java.util.stream.Collectors;

import static datingapp.Profile.isFullyCompleted;
import static datingapp.StreamsUtil.*;

public class Main {

    public static void main(String[] args) {

        //Pulling the profiles from ProfileManager
        System.out.println();
        System.out.println("Question #1");
        List<Profile> profiles = ProfileManager.getProfiles();

        //Filter #1
        System.out.println();
        System.out.println("Question #1");
        filterAndPrintProfiles(profiles);

        //Filter #2
        System.out.println();
        System.out.println("Question #2");
        fillterPplWhoLikesMovies(profiles);

        //Filter #3
        System.out.println();
        System.out.println("Question #3");
        filterCookingAndPhotography(profiles);

        //Filter #4
        System.out.println();
        System.out.println("Question #4");
        filterAndPrintRemainingProfiles(profiles);

        //Map #5
        System.out.println();
        System.out.println("Question #5");
        mapAndPrintProfileAges(profiles);

        //Map #6
        System.out.println();
        System.out.println("Question #6");
        mapAndPrintCapitalizedNames(profiles);

        //Anymatch #7
        System.out.println();
        System.out.println("Question #7");
         checkIfAnyoneUnder18(profiles);

        //Count #8
        System.out.println();
        System.out.println("Question #8");
        countPeopleWhoLikeMusic(profiles);

        //AllMatch() #9
        System.out.println();
        System.out.println("Question #9");
        checkIfAllProfilesAreAtLeast18(profiles);



        //Advanced #10
        System.out.println();
        System.out.println("Question #10");
        List<Profile> whoAreSuperUsers = profiles.stream()
                .filter(profile -> isFullyCompleted(profile))
                .map(profile -> {
                    profile.setSuperUser(true);
                    return profile;}
                )
                .collect(Collectors.toList());

        System.out.println("Those people are your super users! Their profiles are all filled out!");
        for (Profile profile : whoAreSuperUsers) {
            System.out.println("Name: " + profile.getProfileName());
            System.out.println();
        }

        //Advanced 10 Part 2
        System.out.println();
        System.out.println("Question #10 Part 2");
        long superUsersWhoLikeArtsOrMusic = whoAreSuperUsers.stream()
                .filter(profile -> profile.getHobby().contains("music") || profile.getHobby().contains("arts"))
                .count();

        System.out.println("Number of SuperUsers who like arts or music: " + superUsersWhoLikeArtsOrMusic);



















    }


}
