package by.imix.taskexecutor.temp;

import java.util.Random;

/**
 * VKActionSettings
 */
public class VKActionSettings {
    private String bDay;
    private int ageStart = 18;
    private int ageFinish = 28;
    private String ageforsearch;
    private String bmonth;
    private String sex = "1"; //1-women, 2-men
    private String city = "282"; //Gomel-392; Minsk-282
    private String country = "3";//Belarus-3
    private String photo = "1"; //1-есть

    public VKActionSettings() {
        bDay = Integer.toString(getRandom(1, 31));
        bmonth = Integer.toString(getRandom(1, 12));
        ageforsearch = Integer.toString(getRandom(ageStart, ageFinish));
    }

    public VKActionSettings(String bDay, int ageStart, int ageFinish, String ageforsearch, String bmonth, String sex,
                            String city, String country, String photo) {
        this.bDay = bDay;
        this.ageStart = ageStart;
        this.ageFinish = ageFinish;
        this.ageforsearch = ageforsearch;
        this.bmonth = bmonth;
        this.sex = sex;
        this.city = city;
        this.country = country;
        this.photo = photo;
    }

    public String getbDay() {
        return bDay;
    }

    public void setbDay(String bDay) {
        this.bDay = bDay;
    }

    public int getAgeStart() {
        return ageStart;
    }

    public void setAgeStart(int ageStart) {
        this.ageStart = ageStart;
    }

    public int getAgeFinish() {
        return ageFinish;
    }

    public void setAgeFinish(int ageFinish) {
        this.ageFinish = ageFinish;
    }

    public String getAgeforsearch() {
        return ageforsearch;
    }

    public void setAgeforsearch(String ageforsearch) {
        this.ageforsearch = ageforsearch;
    }

    public String getBmonth() {
        return bmonth;
    }

    public void setBmonth(String bmonth) {
        this.bmonth = bmonth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    private Random rnd = new Random(System.currentTimeMillis());

    /**
     * Method return random in diaposon min-max
     * @param min min
     * @param max max
     * @return max
     */
    public int getRandom(int min, int max) {
        int number = min + rnd.nextInt(max - min + 1);
        return number;
    }
}
