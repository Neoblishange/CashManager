package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Admin extends User {

    public Admin(String name, String password) {
        super(name, password);
    }

    public void changeSettings(String field, String value) throws IOException {
        String newLine = field + " : " + value + "\n";
        File file = new File("src/main/resources/Settings.txt");
        FileWriter fw= new FileWriter(file, true);


        for (int i = 0; i < newLine.length(); i++)
            fw.write(newLine.charAt(i));

        //close the file
        fw.close();

        //TODO Must read and display content of Settings.txt
        //TODO insert into Settings.txt
    }


}
