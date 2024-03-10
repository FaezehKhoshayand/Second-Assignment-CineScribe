import org.json.JSONObject;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
public class Actors {
    public static final String API_KEY = "lx3V7SSX0UGbFy8DibPSfQ==fbNjG3un4zdTPqV3";
    String name;
    String netWorth;
    Boolean isAlive;
    String gender;
    String date;
    String nationality;

    public Actors(String netWorth, boolean isAlive){
        this.netWorth = netWorth;
        this.isAlive = isAlive;
        gender = date = nationality = "";
    }
    @SuppressWarnings({"deprecation"})
    /**
     * Retrieves data for the specified actor.
     * @param name for which Actor should be retrieved
     * @return a string representation of the Actors info or null if an error occurred
     */
    public String getActorData(String name) {
        try {
            URL url = new URL("https://api.api-ninjas.com/v1/celebrity?name="+
                    name.replace(" ", "+")+"&apikey="+API_KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Api-Key", API_KEY);
            System.out.println(connection);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                return response.toString();
            } else {
                return "Error: " + connection.getResponseCode() + " " + connection.getResponseMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean errorHandler(String actorsInfoJson) {
        JSONArray arr = new JSONArray(actorsInfoJson);
        if (arr.length() == 0) {
            return false;
        }
        return true;
    }

    public String getName(String actorsInfoJson) {
        JSONArray arr = new JSONArray(actorsInfoJson);
        JSONObject obj = arr.getJSONObject(0);
        name = obj.getString("name");
        return name;
    }

    public double getNetWorthViaApi(String actorsInfoJson){
        double result = 0;
        JSONArray arr = new JSONArray(actorsInfoJson);
        JSONObject obj = arr.getJSONObject(0);
        result = obj.getDouble("net_worth");
        return result;
    }

    public boolean isAlive(String actorsInfoJson){
        JSONArray arr = new JSONArray(actorsInfoJson);
        JSONObject obj = arr.getJSONObject(0);
        isAlive = obj.getBoolean("is_alive");
        return isAlive;
    }

    public String getNationality(String actorsInfoJson) {
        JSONArray arr = new JSONArray(actorsInfoJson);
        JSONObject obj = arr.getJSONObject(0);
        nationality = obj.getString("nationality");
        return nationality;
    }

    public String getGender(String actorsInfoJson) {
        JSONArray arr = new JSONArray(actorsInfoJson);
        JSONObject obj = arr.getJSONObject(0);
        gender = obj.getString("gender");
        return gender;
    }

    public String getDateOfDeathViaApi(String actorsInfoJson){
        if (!isAlive(actorsInfoJson)) {
            JSONArray arr = new JSONArray(actorsInfoJson);
            JSONObject obj = arr.getJSONObject(0);
            date = obj.getString("death");
            return date;
        }
        return null;
    }

}