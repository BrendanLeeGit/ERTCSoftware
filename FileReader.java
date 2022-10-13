import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/*
    file should use this format or smt similar:
    Quarter: quarter name
    ExpectedTotal: 1313114.13 (basically whatever the ttoal wages should be)
    *(this first token is ignored if it has numbers)* Someones Name Here 1231.10 whatyever else can be here it doesn't matter
    148-92-6265 Hurff, Nicholas, J 14034.38 13
    154-15-2402 Gibbs, Noah J, J 1731.50 1
    148-52-9656 Perone, John, D 15341.38 13
    147-70-4769 Ang, Kimberly, A 11510.00 13
    Quarter: quarter name 2
    ExpectedTotal: 1313114.13 (basically whatever the ttoal wages should be)
    *(this first token is ignored if it has numbers)* Someones Name Here 1231.10 whatyever else can be here it doesn't matter
    148-92-6265 Hurff, Nicholas, J 14034.38 13
    154-15-2402 Gibbs, Noah J, J 1731.50 1
    148-52-9656 Perone, John, D 15341.38 13
    147-70-4769 Ang, Kimberly, A 11510.00 13
    */
public class FileReader{
    Person[] people = new Person[200];
    int personCount = 0;
    String[] quarterNames = new String[10];
    int quarterCount = 0;

    public FileReader(String fileName) throws FileNotFoundException{
        //initializae a bunch of ppl with the name z
        for (int i = 0; i < 200; i++){
            people[i] = new Person("zzz");
        }

        String token;
        String name = "";
        String quarter = "";
        double expectedTotal;
        double wage;

        Scanner scan = new Scanner(new File(fileName));
        while (scan.hasNextLine()) {
            token = scan.next();



            token = token.replace(",", "");
            token = token.replace("*", "");
            //if (token.contains("*") || (token.contains("-") && Character.isDigit(token.charAt(10)))){/*do nothing lol*/}
            //if (token.contains("*") || ((token.charAt(0) == 'X') && token.charAt(1) == 'X')){/*do nothing lol*/}

            //if the social security number isnt censored, and names dont jhave hyphens. do this
            //if (token.contains("-") || ((token.charAt(0) == 'X') && token.charAt(1) == 'X')){/*do nothing lol*/}

            //if no hyphens in names, then do this
            if (token.contains("-")){/*do nothing lol*/}

            else if (token.contains("Quarter:")){
                quarter = scan.next();
                quarterNames[quarterCount] = quarter;
                quarterCount++;
            }
            else if (token.contains("ExpectedTotal:")){
                expectedTotal = Double.parseDouble(scan.next());
            }
            else{
                for (;;) {
                    try {

                        token = token.replace(",","");
                        token = token.replace("$","");

                        wage = Double.parseDouble(token);
                        if (find(name) != null){
                            find(name).addQuarterWage(quarter, wage);
                        }
                        else{
                            people[personCount] = new Person(name);
                            people[personCount].addQuarterWage(quarter, wage);
                            personCount++;

                        }
                        scan.nextLine();
                        name = "";
                        break;
                        } catch (Exception E) {

                            token = token.replace(",", "");

                            name += token + " ";
                            token = scan.next();
                        }
                }
            }

        }
        scan.close();

    }

    public Person find(String name){
        for (int i = 0; i < personCount; i++){
            if (name.equals(people[i].name)) {
                return people[i];
            }
        }
        return null;
    }
    public void printPeople(){
        for (int i = 0; i < personCount; i++){
            System.out.println(people[i].name);
        }
    }

    public void printWages(){
        String wageString = "";

        for (int i = 0; i < personCount; i++){
            System.out.print(people[i].name + ": ");
            for (int p = 0; p < quarterCount; p++) {
                wageString += people[i].getWage(quarterNames[p]) + " ";
            }
            System.out.print(wageString + "\n");
            wageString = "";
        }

    }

    public void printTotalWages(){
        double totalWages = 0;
        for (int i = 0; i < personCount; i++){

            totalWages += people[i].wageTotal;

        }
        System.out.println(totalWages);
    }

    public void sortPeople(){
        Arrays.sort(people, Person::compareThem);
    }

    public void createCSV(String csvName) throws IOException {
        //prints results to CSV file
        FileWriter writer = new FileWriter(csvName);

        writer.write("Name,");
        for (int i = 0; i < quarterCount; i++){
            writer.write(quarterNames[i] + ",");
        }
        writer.write("\n");

        String wageString = "";
        for (int i = 0; i < personCount; i++){
            for (int p = 0; p < quarterCount; p++) {
                wageString += people[i].getWage(quarterNames[p]) + ",";
            }

            writer.write(people[i].name + "," + wageString + "\n");

            wageString = "";
        }
        //excel equations at the bottom
        writer.write("Sums:,=sum(B2:B" + (personCount+1) + ")" + " ,=sum(C2:C" + (personCount+1) + ")" + " ,=sum(D2:D" + (personCount+1) + ")" + " ,=sum(E2:E" + (personCount+1) + ")");
        writer.close();

    }

}
