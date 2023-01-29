import java.io.IOException;
import java.net.URI;

class Handling implements URLHandler{
    String list = "";
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Hello! Welcome to my StringServer");
        } else if (url.getPath().equals("/add-message")) {
            String [] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                list+= parameters[1] + System.lineSeparator();
                return String.format(list);
            }
        }
        return "404 Not Found";
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between" 
                + " 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handling());
    }
}
