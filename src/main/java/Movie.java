import org.json.JSONObject;
import org.json.JSONArray;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.util.ArrayList;
public class Movie {
    public static final String API_KEY = "b37d7e30";
    ArrayList<String> actorsList;
    String title;
    String year;
    String rated;
    String release;
    String runtime;
    String genre;
    String director;
    String writer;
    String plot;
    String language;
    String country;
    String awards;
    String rating;
    int ImdbVotes;


    public Movie(ArrayList<String> actorsList, String rating, int ImdbVotes){
        title = "";
        year = "";
        rated = "";
        release = "";
        runtime = "";
        genre = "";
        director = "";
        writer = "";
        plot = "";
        language = "";
        country = "";
        awards = "";
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
        String error = stringBuilder.toString();
        if(error.substring(error.length() - 27).equals("\"Error\":Movie not found"));
        return stringBuilder.toString();
    }

    public String getTitleViaApi(String moviesInfoJson) {
        JSONObject obj = new JSONObject(moviesInfoJson);
        String title = obj.getString("Title");
        return title;
    }

    public String getYear(String moviesInfoJson){
        JSONObject obj = new JSONObject(moviesInfoJson);
        String year = obj.getString("Year");
        return year;
    }

    public String getRatedViaApi(String moviesInfoJson) {
        JSONObject obj = new JSONObject(moviesInfoJson);
        String rated = obj.getString("Rated");
        return rated;
    }

    public String getReleasedViaApi(String moviesInfoJson) {
        JSONObject obj = new JSONObject(moviesInfoJson);
        String release = obj.getString("Released");
        return release;
    }

    public String getRuntimeViaApi(String moviesInfoJson) {
        JSONObject obj = new JSONObject(moviesInfoJson);
        String runtime = obj.getString("Runtime");
        return runtime;
    }

    public String getGenreListViaApi(String moviesInfoJson) {
        JSONObject obj = new JSONObject(moviesInfoJson);
        String genre = obj.getString("Genre");
        return genre;
    }

    public String getDirectorViaApi(String moviesInfoJson) {
        JSONObject obj = new JSONObject(moviesInfoJson);
        String director = obj.getString("Director");
        return director;
    }

    public String getWritersListViaApi(String moviesInfoJson) {
        JSONObject obj = new JSONObject(moviesInfoJson);
        String writer = obj.getString("Writer");
        return writer;
    }

    public ArrayList<String> getActorListViaApi(String movieInfoJson){
        JSONObject obj = new JSONObject(movieInfoJson);
        String actor = obj.getString("Actors");
        String[] actors = actor.split(", ");
        for(String i : actors) {
            actorsList.add(i);
        }
        return actorsList;
    }

    public String getPlotViaApi(String moviesInfoJson) {
        JSONObject obj = new JSONObject(moviesInfoJson);
        String plot = obj.getString("Plot");
        return plot;
    }

    public String getLanguagesListViaApi(String moviesInfoJson) {
        JSONObject obj = new JSONObject(moviesInfoJson);
        String language = obj.getString("Language");
        return language;
    }

    public String getCountriesListViaApi(String moviesInfoJson) {
        JSONObject obj = new JSONObject(moviesInfoJson);
        String country = obj.getString("Country");
        return country;
    }

    public String getAwardsViaApi(String moviesInfoJson) {
        JSONObject obj = new JSONObject(moviesInfoJson);
        String awards = obj.getString("Awards");
        return awards;
    }

    public String getRatingViaApi(String moviesInfoJson){
        JSONObject obj = new JSONObject(moviesInfoJson);
        JSONArray RATINGS = obj.getJSONArray("Ratings");
        String rating = "";
        for(int i = 0; i < RATINGS.length(); i++) {
            if(RATINGS.getJSONObject(i).getString("Source").equals("Internet Movie Database")) {
                rating = rating + RATINGS.getJSONObject(i).getString("Value");
            }
        }
        return rating;
    }

    public int getImdbVotesViaApi(String moviesInfoJson){
        JSONObject obj = new JSONObject(moviesInfoJson);
        String ImdbVote = obj.getString("imdbVotes");
        int ImdbVotes = Integer.parseInt(ImdbVote.replace(",",""));
        return ImdbVotes;
    }
}