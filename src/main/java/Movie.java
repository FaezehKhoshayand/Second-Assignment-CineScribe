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
    String title;
    String year;
    String rated;
    String release;
    String runtime;
    ArrayList<String> genreList;
    String director;
    ArrayList<String> writersList;
    ArrayList<String> actorsList;
    String plot;
    ArrayList<String> languagesList;
    ArrayList<String> countriesList;
    String awards;
    String rating;
    int ImdbVotes;


    public Movie(ArrayList<String> actorsList, String rating, int ImdbVotes){
        title = "";
        year = "";
        rated = "";
        release = "";
        runtime = "";
        genreList = new ArrayList<>();
        director = "";
        writersList = new ArrayList<>();
        this.actorsList = actorsList;
        plot = "";
        languagesList = new ArrayList<>();
        countriesList = new ArrayList<>();
        awards = "";
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

    public ArrayList<String> getGenreListViaApi(String moviesInfoJson) {
        JSONObject obj = new JSONObject(moviesInfoJson);
        String genre = obj.getString("Genre");
        String[] genres = genre.split(", ");
        for(String i : genres) {
            genreList.add(i);
        }
        return genreList;
    }

    public String getDirectorViaApi(String moviesInfoJson) {
        JSONObject obj = new JSONObject(moviesInfoJson);
        String director = obj.getString("Director");
        return director;
    }

    public ArrayList<String> getWritersListViaApi(String moviesInfoJson) {
        JSONObject obj = new JSONObject(moviesInfoJson);
        String writer = obj.getString("Genre");
        String[] writers = writer.split(", ");
        for(String i : writers) {
            writersList.add(i);
        }
        return writersList;
    }

    public void getActorListViaApi(String movieInfoJson){
        JSONObject obj = new JSONObject(movieInfoJson);
        String actor = obj.getString("Actors");
        String[] actors = actor.split(", ");
        for(String i : actors) {
            actorsList.add(i);
        }
    }

    public String getPlotViaApi(String moviesInfoJson) {
        JSONObject obj = new JSONObject(moviesInfoJson);
        String plot = obj.getString("Plot");
        return plot;
    }

    public ArrayList<String> getLanguagesListViaApi(String moviesInfoJson) {
        JSONObject obj = new JSONObject(moviesInfoJson);
        String language = obj.getString("Genre");
        String[] languages = language.split(", ");
        for(String i : languages) {
            languagesList.add(i);
        }
        return languagesList;
    }

    public ArrayList<String> getCountriesListViaApi(String moviesInfoJson) {
        JSONObject obj = new JSONObject(moviesInfoJson);
        String country = obj.getString("Genre");
        String[] countries = country.split(", ");
        for(String i : countries) {
            countriesList.add(i);
        }
        return countriesList;
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