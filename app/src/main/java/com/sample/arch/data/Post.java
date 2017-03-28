package com.sample.arch.data;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by rohitkumar.yadav on 26/3/17.
 */

@AutoValue
public  abstract class Post {

    public abstract int userId();
    public abstract int id();
    public abstract String title();
    public abstract String body();

    public static TypeAdapter<Post> typeAdapter(Gson gson) {
        return new AutoValue_Post.GsonTypeAdapter(gson);
    }

}
