import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        try {
            runMenu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void runMenu() throws IOException {
        while(true) {
            System.out.println("Choose An Option");
            System.out.println("1.Movie");
            System.out.println("2.Actors\\Actresses");
            System.out.println("3.Exit");
            Scanner input = new Scanner(System.in);
            int option = input.nextInt();
            switch(option) {
                case 1:
                    Movie movie = new Movie(new ArrayList<>(),"",0);
                    System.out.println("Please Enter The Name Of A Movie");
                    Scanner Input = new Scanner(System.in);
                    String movieTitle = Input.nextLine();
                    String movieData = movie.getMovieData(movieTitle);
                    System.out.println("MovieTitle: " + movie.getTitleViaApi(movieData));
                    System.out.println("Years: " + movie.getYear(movieData));
                    System.out.println("Rated: " + movie.getRatedViaApi(movieData));
                    System.out.println("Date Of Release:" + movie.getReleasedViaApi(movieData));
                    System.out.println("Runtime: " + movie.getRuntimeViaApi(movieData));
                    System.out.println("Genres: " + movie.getGenreListViaApi(movieData));
                    System.out.println("Directors: " + movie.getDirectorViaApi(movieData));
                    System.out.println("Writers: " + movie.getWritersListViaApi(movieData));
                    System.out.println("Actors: " + movie.getActorListViaApi(movieData));
                    System.out.println("Movie Plot: " + movie.getPlotViaApi(movieData));
                    System.out.println("Languages: " + movie.getLanguagesListViaApi(movieData));
                    System.out.println("Countries: " + movie.getCountriesListViaApi(movieData));
                    System.out.println("Awards: " + movie.getAwardsViaApi(movieData));
                    System.out.println("Rating: " + movie.getRatingViaApi(movieData));
                    System.out.println("IMDB Votes: " + movie.getImdbVotesViaApi(movieData));
                    break;
                case 2:
                    Actors actor = new Actors("",false);
                    System.out.println("Please Enter The Name Of An Actor/Actress");
                    Scanner INput = new Scanner(System.in);
                    String movieStar = INput.nextLine();
                    String actorData = actor.getActorData(movieStar);
                    System.out.println("Name: " + actor.getName(actorData));
                    System.out.println("NetWorth: " + actor.getNetWorthViaApi(actorData));
                    System.out.println("Nationality: " + actor.getNationality(actorData));
                    System.out.println("Gender: " + actor.getGender(actorData));
                    if (!actor.isAlive(actorData)) {
                        System.out.println("DateOfDeath: " + actor.getDateOfDeathViaApi(actorData));
                    }
                    break;
                case 3:
                    return;
            }
        }
    }
}