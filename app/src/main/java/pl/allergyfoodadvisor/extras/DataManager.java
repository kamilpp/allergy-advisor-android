package pl.allergyfoodadvisor.extras;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import pl.allergyfoodadvisor.main.AllergyAdvisor;

public class DataManager {
    private static DataManager mInstance;
    private final String HISTORY = "history";
    private final String MY_ALLERGENS = "my_allergens";

    DataManager() {

    }

    public static DataManager getInstance() {
        if (mInstance == null) {
            mInstance = new DataManager();
        }
        return mInstance;
    }

    private SharedPreferences getManager() {
        return PreferenceManager.getDefaultSharedPreferences(AllergyAdvisor.getInstance().getApplicationContext());
    }

    public List<String> getMyAllergens() {
        Gson gson = new GsonBuilder().create();
        List<String> myAllergens;
        if (getManager().getString(MY_ALLERGENS, null) == null) {
            myAllergens = new ArrayList<String>();
        } else {
            myAllergens = gson.fromJson(getManager().getString(MY_ALLERGENS, null), List.class);
        }

        return myAllergens;
    }

    public void saveToMyAllergens(String value){
        Gson gson = new GsonBuilder().create();
        List<String> myAllergens;
        if (getManager().getString(MY_ALLERGENS, null) == null) {
            myAllergens = new ArrayList<String>();
        } else {
            myAllergens = gson.fromJson(getManager().getString(MY_ALLERGENS, null), List.class);
        }

        if (myAllergens.contains(value)) {
            myAllergens.remove(value); // we want to add it at the end
        }
        myAllergens.add(value);

        for (String x:myAllergens) {
            Log.d("test", x);
        }

        getManager().edit().putString(MY_ALLERGENS, gson.toJson(myAllergens)).commit();
        return;
    }

    public void removeFromMyAllergens(String value){
        Gson gson = new GsonBuilder().create();
        List<String> myAllergens;

        if (getManager().getString(MY_ALLERGENS, null) == null) {
            return;
        } else {
            myAllergens = gson.fromJson(getManager().getString(MY_ALLERGENS, null), List.class);
        }

        if (myAllergens.contains(value)) {
            myAllergens.remove(value); // we want to add it at the end
        }

        getManager().edit().putString(MY_ALLERGENS, gson.toJson(myAllergens)).commit();
        return;
    }

    public List<String> getHistory() {
        Gson gson = new GsonBuilder().create();
        List<String> history;
        if (getManager().getString(HISTORY, null) == null) {
            history = new ArrayList<String>();
        } else {
            history = gson.fromJson(getManager().getString(HISTORY, null), List.class);
        }

        return history;
    }

    public void saveToHistory(String value) {
        Gson gson = new GsonBuilder().create();
        List<String> history;
        if (getManager().getString(HISTORY, null) == null) {
            history = new ArrayList<String>();
        } else {
            history = gson.fromJson(getManager().getString(HISTORY, null), List.class);
        }

        if (history.contains(value)) {
            history.remove(value); // we want to add it at the end
        }
        history.add(value);

        for (String x:history) {
            Log.d("test", x);
        }

        getManager().edit().putString(HISTORY, gson.toJson(history)).commit();
        return;
    }

    public DataManager put(String key, String value) {
        PreferenceManager.getDefaultSharedPreferences(AllergyAdvisor.getInstance().getApplicationContext())
                .edit()
                .putString(key, value.toString())
                .commit();
        return this;
    }

    public String get(String key, String defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(AllergyAdvisor.getInstance().getApplicationContext())
                .getString(key, defaultValue);
    }
}