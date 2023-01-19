import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

 class Handling implements URLHandler{
    ArrayList<String> words = new ArrayList<String>();
    ArrayList<String> result = new ArrayList<String>();
    public String handleRequest(URI url) {
        
        if (url.getPath().equals("/")) {
            return String.format("Hello! Welcome to this rudimentary search engine.");
        } else if (url.getPath().contains("/add")){
            System.out.println("Path: " + url.getPath());
            String [] parameters = url.getQuery().split("=");
            if(parameters[0].equals("s")) {
                words.add(parameters[1]);
            }
            return String.format("Your list of words is now: %s", words.toString());
        } else if (url.getPath().contains("/search")) {
            String [] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                for (int i = 0; i < words.size(); i++) {
                    if(words.get(i).contains(parameters[1])) {
                        String s = words.get(i);
                        result.add(s);
                    }
                }
            }
            System.out.println(words);
            return String.format("Your results are: %s", result);
        }


        return String.format("There are no words that match your query.");
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handling());
    }
}
