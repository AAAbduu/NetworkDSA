package Data;

import Exceptions.InvalidUserStringException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

/**
 * Own class implementation of a User data structure.
 *
 * @author Abdu
 * @author Agus
 */
public class User {

    private final String id;
    private String name;
    private String surnames;
    private String birthDate;
    private String gender;
    private String birthplace;
    private String home;
    private HashSet<String> studyDat;
    private HashSet<String> workDat;
    private HashSet<String> movies;
    private String groupCode;

    public User(String id) {
        this.id = id;
    }

    /**
     * Creating users from a txt file.
     *
     * @param fileName Given txt file name.
     * @return Set of Users read in the file given.
     */
    public static Set<User> fromFile(final String fileName) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.nextLine();
        Set<User> users = new HashSet<>();
        while (scanner.hasNext()) {
            String userData = scanner.nextLine();
            try {
                users.add(User.parse(userData));
            } catch (InvalidUserStringException e) {
                System.out.println("Information wrongly written in input file for this user, ignoring: " + userData);
            }
        }
        return users;
    }

    /**
     * Function to parse a User reading its correspongin line of data in the file.
     *
     * @param userData Users data line in the file.
     * @return User with the data read in the line.
     * @throws InvalidUserStringException If the line is not correctly written or the data is corrupt, throws this Exception.
     */
    public static User parse(final String userData) throws InvalidUserStringException {
        HashSet<String> studyData = new HashSet<String>();
        HashSet<String> workData = new HashSet<String>();
        HashSet<String> movies = new HashSet<String>();
        String[] data = userData.split(",");
        String id = data[0];
        try {
            User newUser = new User(id);
            String name = data[1];
            String surnames = data[2];
            String birthdate = data[3];
            String gender = data[4];
            String birthplace = data[5];
            String home = data[6];
            String[] studyDat = data[7].split(";");
            for (String s : studyDat) {
                studyData.add(s);

            }
            String[] workDat = data[8].split(";");
            for (String s : workDat) {
                workData.add(s);

            }
            String[] movie = data[9].split(";");
            for (String s : movie) {
                movies.add(s);

            }
            String groupCode = data[10];
            if (name != null) newUser.setName(name);
            if (surnames != null) newUser.setSurnames(surnames);
            if (birthdate != null) newUser.setBirthDate(birthdate);
            if (gender != null) newUser.setGender(gender);
            if (birthplace != null) newUser.setBirthplace(birthplace);
            if (home != null) newUser.setHome(home);
            if (studyData != null) newUser.setStudyDat(studyData);
            if (workData != null) newUser.setWorkDat(workData);
            if (movies != null) newUser.setMovies(movies);
            if (groupCode != null) newUser.setGroupCode(groupCode);
            return newUser;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidUserStringException(userData);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurnames() {
        return surnames;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public String getHome() {
        return home;
    }

    public HashSet<String> getStudyDat() {
        return studyDat;
    }

    public HashSet<String> getWorkDat() {
        return workDat;
    }

    public HashSet<String> getMovies() {
        return movies;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public void setStudyDat(HashSet<String> studyDat) {
        this.studyDat = studyDat;
    }

    public void setWorkDat(HashSet<String> workDat) {
        this.workDat = workDat;
    }

    public void setMovies(HashSet<String> movies) {
        this.movies = movies;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "ID='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surnames='" + surnames + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", gender='" + gender + '\'' +
                ", birthplace='" + birthplace + '\'' +
                ", home='" + home + '\'' +
                ", studyDat=" + studyDat +
                ", workDat=" + workDat +
                ", movies=" + movies +
                ", groupCode='" + groupCode + '\'' +
                '}';
    }

}