package com.ptit.btl.moviedb.data.source.local;

import com.google.gson.Gson;
import com.ptit.btl.moviedb.data.model.User;
import com.ptit.btl.moviedb.data.source.UserDataSource;
import com.ptit.btl.moviedb.data.source.local.sharedprf.SharedPrefsApi;
import com.ptit.btl.moviedb.data.source.local.sharedprf.SharedPrefsKey;

/**
 * Created by admin on 25/4/18.
 */

public class UserLocalDataSource implements UserDataSource.LocalDataSource {
    private SharedPrefsApi mSharedPrefsApi;

    public UserLocalDataSource(SharedPrefsApi sharedPrefsApi) {
        mSharedPrefsApi = sharedPrefsApi;
    }

    @Override
    public void saveUser(User user) {
        String data = new Gson().toJson(user);
        mSharedPrefsApi.put(SharedPrefsKey.KEY_USER, data);
    }

    @Override
    public User getUser() {
        String data = mSharedPrefsApi.get(SharedPrefsKey.KEY_USER, String.class);
        return new Gson().fromJson(data, User.class);
    }

    @Override
    public void deleteUser() {
        mSharedPrefsApi.delete(SharedPrefsKey.KEY_USER);
    }

    @Override
    public void clearCache() {
        mSharedPrefsApi.clear();
    }
}
