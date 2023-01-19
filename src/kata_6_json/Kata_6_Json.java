 package kata_6_json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import static java.util.stream.Collectors.joining;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
 
 
public class Kata_6_Json {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://fruityvice.com/api/fruit/all");
            Gson gson = new Gson();
            
            /*
            Vamos a obtener inicialmente unicamente un JsonObject de una fruta del
            JsonArray de todas las frutas del API que hemos decidido usar.
            Vamos a ir obteniendo todas las particularidades el la fruta que están
            contenidas dentro del JsonObject y vamos a ir mostrandolas por separado.
             */
            
            JsonObject Json_Object_Fruta = gson.fromJson(read(url), JsonArray.class).get(3).getAsJsonObject();
            
            System.out.println("JsonObject de la Fruta Obtenido:\n" + Json_Object_Fruta);
            
            System.out.println("Fruta -> " + Json_Object_Fruta.get("name").getAsString()
                                + "\nID -> " + Json_Object_Fruta.get("id").getAsInt()
                                + "\nFamily -> " + Json_Object_Fruta.get("family").getAsString()
                                + "\nOrder -> " + Json_Object_Fruta.get("order").getAsString()
                                + "\nNutritions -> " 
            + "Carbohydrates: " + Json_Object_Fruta.get("nutritions").getAsJsonObject().get("carbohydrates").getAsFloat()
            + ", Protein: " + Json_Object_Fruta.get("nutritions").getAsJsonObject().get("protein").getAsFloat()
            + ", Fat: " + Json_Object_Fruta.get("nutritions").getAsJsonObject().get("fat").getAsFloat()
            + ", Calories: " + Json_Object_Fruta.get("nutritions").getAsJsonObject().get("calories").getAsFloat()
            + ", Sugar: " + Json_Object_Fruta.get("nutritions").getAsJsonObject().get("sugar").getAsFloat());
           
            
            /*
            Vamos a obtener todas las frutas que contiene el JsonArray de frutas
            haciendo uso del iterador de gson. 
            Mostrando el ID y Nombre de todas las frutas.
             */
            
            System.out.println("\nTodas las Frutas del JsonArray:");
            
            Iterator<JsonElement> Frutas = gson.fromJson(read(url), JsonArray.class).asList().iterator();
            
            while(Frutas.hasNext()){
                JsonObject next = Frutas.next().getAsJsonObject();
                System.out.println("Id: " + next.get("id") + ", Name: " + next.get("name").getAsString());
            }
            
            /*
            Ahora vamos a deserializar los JsonObject del JsonArray de la frutas
            haciendo uso de una clase Pojo "Fruit"
             */
            
            System.out.println("\nDeserializado con una clase Pojo:");
            
            Iterator<JsonElement> Frutas_deserializar_Pojo = gson.fromJson(read(url), JsonArray.class).asList().iterator();
            
            while(Frutas_deserializar_Pojo.hasNext()){
                JsonObject next = Frutas_deserializar_Pojo.next().getAsJsonObject();
                Fruit fruit = gson.fromJson(next, Fruit.class);
                System.out.println(fruit);
            }
            
            
            /*
            Como serializar un Pojo a XML
             */
            
            System.out.println("\nSerializado de una clase Pojo:");            
            
            JsonObject fruit_object = gson.fromJson(read(url), JsonArray.class).get(0).getAsJsonObject();
            
            Fruit fruit = gson.fromJson(fruit_object, Fruit.class);
            
            JAXBContext jaxbContext = JAXBContext.newInstance(Fruit.class);
            Marshaller jaxmarshaller = jaxbContext.createMarshaller();
            jaxmarshaller.marshal(fruit, System.out);
            
            System.out.println("\n");
            
            JAXBContext jaxbContext2 = JAXBContext.newInstance(JsonObject.class);
            Marshaller jaxmarshaller2 = jaxbContext2.createMarshaller();
            jaxmarshaller2.marshal(fruit_object, System.out);
            



        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static String read(URL url) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        return bufferedReader.lines().collect(joining());
    }
    
}
