package guru.qa.models;

import java.util.ArrayList;

public class Teacher {

    private ArrayList<String> names;
    private String profession;
    private Boolean isGoodTeacher;
    private Integer age;
    private Passport passport;

    public Teacher(ArrayList<String> names, String profession, Boolean isGoodTeacher, Integer age, Passport passport) {
        this.names = names;
        this.profession = profession;
        this.isGoodTeacher = isGoodTeacher;
        this.age = age;
        this.passport = passport;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Boolean isGoodTeacher() {
        return isGoodTeacher;
    }

    public void setGoodTeacher(Boolean goodTeacher) {
        isGoodTeacher = goodTeacher;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public static class Passport {
        private int number;

        public Passport(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }



}
