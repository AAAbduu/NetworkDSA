package Data;

import javax.sound.midi.SysexMessage;
import java.util.HashSet;
import java.util.Scanner;

public class User implements Comparable<User> {
    private final String ID;
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


    public User(String ID) {
        this.ID = ID;

    }

    public String getID() {
        return ID;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getID().equals(user.getID());
    }


    @Override
    public String toString() {
        return "User{" +
                "ID='" + ID + '\'' +
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

    @Override
    public int compareTo(User o) {
        return 0;
    }
}
