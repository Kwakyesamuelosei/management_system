package test.java.io.turntabl;

import main.java.io.turntabl.ClientController;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ClientControllerTest {

    @Test
    public void testDeleteClient() {
        ClientController clientController = new ClientController();
        Map<String,String> actual = clientController.removeClient("194029");
        Map<String,String> expected = new HashMap<>();
        expected.put("code","00");
        expected.put("msg","Client deleted Successfully");

        assertEquals(expected,actual);
    }

    @Test
    public void testUpdateClient() {
        ClientController clientController = new ClientController();
        Map<String,String> updateData = new HashMap<>();
        updateData.put("name","");
        updateData.put("address","Kasoa");
        updateData.put("phoneNumber","055555555");
        updateData.put("email","yaaMaame@gmail.com");
        updateData.put("id","738811");

        Map<String,String> actual = clientController.updateClient(updateData);
        Map<String,String> expected = new HashMap<>();
        expected.put("code","00");
        expected.put("msg","Clients details Updated Successfully");

        assertEquals(expected,actual);
    }
}