package domain.hash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {
    @Test
    void test(){
            HashMap<String, String> map = new HashMap<>();

            map.put("Daniel","8888-4444");
            map.put("Aidan","4445-3355");
            map.put("Andreé","5566-8877");
            map.put("Jerome","8791-4452");

            System.out.println("Lista de Contactos:");
            System.out.println("Cantidad de contactos: " + map.size());
            for (String key : map.keySet()) {
                System.out.println(key + ": " + map.get(key));
            }

            System.out.println("Telefono de Andreé: " + map.get("Andreé"));

            map.remove("Jerome");

            System.out.println("Cantidad de contactos: " + map.size());

            System.out.println("Lista de Contactos");
            for (String key : map.keySet()) {
                System.out.println(key + ": " + map.get(key));
            }
        }
}