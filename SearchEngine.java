import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

 class SearchEngine {
    
    public String handleRequest(URI url) {
        ArrayList<String> words = new ArrayList<String>();
        if (url.getPath().equals("/")) {
            return String.format("Nothing has been queried!");
        } else if (url.getPath().contains("/add")){
            System.out.println("Path: " + url.getPath());
            String [] parameters = url.getQuery().split("=");
            if(parameters[0].equals("s")) {
                words.add(parameters[1]);
            }
        } else if (url.getPath().contains("/search")) {
            String result = "";
            String [] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                for (int i = 0; i < words.size(); i++) {
                    if(words.get(i).contains(parameters[1])) {
                        result += words.get(i);
                    }
                }
            }
            return result;
        }


        return String.format("Oh no it does not work");
    }
}
