package arep;

import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    
    private static Cache instance;
    static ConcurrentHashMap<String, String> cache;

    /**
     * Constructor de la clase
     */
    public Cache(){
        cache = new ConcurrentHashMap<String, String>();
    }

    /**
     * Saber si un titulo de una pelicula esta incluido en el cache
     * @param title Titulo a consultar
     * @return Retorna el valor de verdad de si la pelicula ya fue consultada
     */
    public boolean isOnCache(String title){
        return cache.containsKey(title);
    }

    /**
     * Obtener la descripción de una pelicula almacenada en el cache basada en su titulo
     * @param title Titulo de la pelicula a consultar
     * @return Informaciuón de la pelicula requerida
     */
    public String getMovieDescription(String title){
        return cache.get(title);
    }

    /**
     * Obtener la unica instancia de cache que existe basado en patron de SINGLETON
     * @return Instancia del Cache
     */
    public static Cache getInstance() {

        if(instance == null){
            instance = new Cache();
        }
        return instance;
    }

    /**
     * Limpiar al caché y dejarlo sin peliculas en su interior
     */
    public void clear(){cache.clear();}
}