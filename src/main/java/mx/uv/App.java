package mx.uv;
import static spark.Spark.*;

import java.util.HashMap;
import java.util.UUID;

import  com.google.gson.*;
public class App 
{
    static Gson gson = new Gson();
    static HashMap<String, Usuario> usuarios = new HashMap<>();
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        //port(80);

        get("/usuarios", (request, response) ->{
            response.type("application/json");
            return gson.toJson(DAO.dameUsuarios());
        });
        post("/usuarios", (request, response) ->{
            String payload = request.body();
            Usuario u = gson.fromJson(payload, Usuario.class);
            String id = UUID.randomUUID().toString();
            u.setId(id);
            //usuarios.put(id, u);
            DAO.crearUsuario(u);
            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("msj", "");
            return respuesta;
        });
    }
}