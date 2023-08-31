package arep.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServiceServer implements ServiceRest{
    public static ServiceServer instance ;

    private ServiceServer(){}

    @Override
    public String getHeader(String type, String code) {
        return "HTTP/1.1 "+code+"\r\n" +
        "Content-type: text/"+type+"\r\n" +
        "\r\n";
    }

    @Override
    public String getResponse(String path) {
        byte[] fileContent;
        try {
            fileContent = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String(fileContent);
    }

    public static ServiceServer getInstance(){
        if(instance == null){
            instance = new ServiceServer();
        }
        return instance;
    }
    
}
