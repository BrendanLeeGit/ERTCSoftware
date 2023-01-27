/**
 * @author Brendan Lee
 */

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException {
        FileReader f1 = new FileReader("input.txt");
        //f.printPeople();
        f1.sortPeople();
        f1.printWages();

        f1.createCSV("output.csv");

        FileReader f2 = new FileReader("input_2.txt");
        //f.printPeople();
        f2.sortPeople();
        f2.printWages();
        f2.createCSV("output_2.csv");

        f1.printTotalWages();
        f2.printTotalWages();

        //opens the files automatically
        //change path names for each computer
        File file = new File("C:\\Users\\BLee\\IdeaProjects\\ERTCDataOrganizationSoftware\\output.csv");
        File file2 = new File("C:\\Users\\BLee\\IdeaProjects\\ERTCDataOrganizationSoftware\\output_2.csv");

        Desktop desktop = Desktop.getDesktop();

        desktop.open(file);
        desktop.open(file2);
    }
}