package jsonTest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Setter;

public class Person {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("age")
    @Expose
    @Setter
    private int age;

    @SerializedName("height")
    @Expose
    private double height;

    @SerializedName("marriage")
    @Expose
    private boolean marriage;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public boolean isMarriage() {
        return marriage;
    }
}