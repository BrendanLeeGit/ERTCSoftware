public class Person {
    public String name;
    QuarterWages[] quarterWages = new QuarterWages[10];
    int wageCount = 0;
    double wageTotal = 0;

    public Person(String name){
        this.name = name;
    }

    public void addQuarterWage(String quarter, double wage){
        quarterWages[wageCount] = new QuarterWages(quarter, wage);
        wageCount++;
        wageTotal += wage;
    }
    public double getWage(String quarter){
        for (int i = 0; i < wageCount; i++){
            if (quarterWages[i].quarter.equals(quarter)){
                return quarterWages[i].wages;
            }
        }
        return 0;
    }

    public static int compareThem(Person a, Person b){
        return (a.name.compareTo(b.name));
    }
}

