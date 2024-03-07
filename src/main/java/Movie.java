import org.json.JSONObject;
import org.json.JSONArray;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.util.ArrayList;
public class Movie {
    public static final String API_KEY = "b37d7e30";   // TODO --> add your api key about Movie here
    int ImdbVotes;
    ArrayList<String> actorsList;
    String rating;

    public Movie(ArrayList<String> actorsList, String rating, int ImdbVotes){
        //TODO --> (Write a proper constructor using the get_from_api functions)
        this.actorsList = actorsList;
        this.rating = rating;
        this.ImdbVotes = ImdbVotes;
    }

    @SuppressWarnings("deprecation")
    /**
     * Retrieves data for the specified movie.
     *
     * @param title the name of the title for which MovieData should be retrieved
     * @return a string representation of the MovieData, or null if an error occurred
     */

    public String getMovieData(String title) throws IOException {
        URL url = new URL("https://www.omdbapi.com/?t="+title+"&apikey="+API_KEY);
        URLConnection Url = url.openConnection();
        Url.setRequestProperty("Authorization", "Key" + API_KEY);
        BufferedReader reader = new BufferedReader(new InputStreamReader(Url.getInputStream()));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine())!=null) {
            stringBuilder.append(line);
        }
        reader.close();
        //handle an error if the chosen movie is not found
        return stringBuilder.toString();
    }
    public int getImdbVotesViaApi(String moviesInfoJson){
        //TODO --> (This function must change and return the "ImdbVotes" as an Integer)
        // NOTICE :: you are not permitted to convert this function to return a String instead of an int !!!
        JSONObject votes = new JSONObject(moviesInfoJson);
        String ImdbVote = votes.getString("imdbVotes");
        int ImdbVotes = Integer.parseInt(ImdbVote.replace(",",""));
        return ImdbVotes;
    }

    public String getRuntime(String moviesInfoJson) {
        JSONObject RUNTIME = new JSONObject(moviesInfoJson);
        String runtime = RUNTIME.getString("Runtime");
        return runtime;
    }

    public String getRatingViaApi(String moviesInfoJson){
        //TODO --> (This function must return the rating in the "Ratings" part
        // where the source is "Internet Movie Database")  -->
        JSONObject RATING = new JSONObject(moviesInfoJson);
        JSONArray RATINGS = RATING.getJSONArray("Ratings");
        String rating = "";
        for(int i = 0; i < RATINGS.length(); i++) {
            if(RATINGS.getJSONObject(i).getString("Source").equals("Internet Movie Database")) {
                rating = rating + RATINGS.getJSONObject(i).getString("Value");
            }
        }
        return rating;
    }

    public void getActorListViaApi(String movieInfoJson){
        //TODO --> (This function must return the "Actors" in actorsList)
        JSONObject ACTOR = new JSONObject(movieInfoJson);
        String actor = ACTOR.getString("Actors");
        String[] actors = actor.split(", ");
        for(String i : actors) {
            actorsList.add(i);
        }
    }
}