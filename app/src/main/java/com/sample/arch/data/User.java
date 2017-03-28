package com.sample.arch.data;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rohitkumar.yadav on 26/3/17.
 */

@AutoValue
public abstract class User {

    public abstract int id();

    public abstract String name();

    @SerializedName("username")
    public abstract String userName();

    public abstract String email();

    public abstract String phone();

    public abstract String website();

    public static TypeAdapter<User> typeAdapter(Gson gson) {
        return new AutoValue_User.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_User.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {

        public abstract Builder setId(int id);

        public abstract Builder setName(String name);

        public abstract Builder setUserName(String userName);

        public abstract Builder setEmail(String email);

        public abstract Builder setPhone(String phone);

        public abstract Builder setWebsite(String website);

        public abstract User build();
    }

}
