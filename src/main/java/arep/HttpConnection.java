package arep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {

    private static final String USER_AGENT = "Mozilla/5.0";

    /**
     * Este método realiza una solicitud a una API externa para obtener información sobre una película.
     * Utiliza el título de la película y una URL para realizar la consulta.
     * @param title El título de la película a consultar en la API externa.
     * @param url La URL para buscar en la API.
     * @return Devuelve la descripción de la película en formato JSON interpretado como una cadena de texto.
     * @throws IOException Se lanza una excepción en caso de error de E/S.
     */
    public static String requestTitle(String title, String url) throws IOException {

        Cache cache = Cache.getInstance();
        if(cache.isOnCache(title)){
            return cache.getMovieDescription(title);
        }

        String siteUrl = url;
        URL obj = new URL(siteUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        // The following invocation perform the connection implicitly before getting the
        // code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());
            String movieData = "[" + response.toString() + "]";
            return movieData;
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return "GET DONE";
    }

} 