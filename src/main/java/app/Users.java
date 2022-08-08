package app;

import com.google.gson.annotations.SerializedName;

import java.util.Set;

public class Users {
    private String users;
    @SerializedName("type")
    private final String TYPE="userslist";

    public String getUsers() {
        return users;
    }

    public Users(String users) {
        this.users = users;
    }
}
