package practice;/*
ID: cravuri
LANG: JAVA
TASK: practice.gift1
*/
import java.io.*;
import java.util.*;

/**
 * 7/22/15
 */
class gift1 {

//    private static final String directory = "/Users/cravuri/Documents/";
//    private static final String fileSuffix = ".txt";
    private static final String directory = "";
    private static final String fileSuffix = "";

    static List<Person> people = new ArrayList<Person>();
    static HashMap<String, Integer> money = new HashMap<String, Integer>();
    static List<String> order = new ArrayList<String>();
    static List<String> name = new ArrayList<String>();
    static List<Integer> value = new ArrayList<Integer>();

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader(directory + "practice.gift1.in" + fileSuffix));
        String line = text.readLine();
        int numOfPeople = Integer.parseInt(line);
        for (int i = 0; i < numOfPeople; i++) {
            order.add(text.readLine());
        }
        for (int i = 0; i < numOfPeople; i++) {
            String name = text.readLine();
            line = text.readLine();
            Person p = new Person();
            StringTokenizer st = new StringTokenizer(line);
            int amount = Integer.parseInt(st.nextToken());
            p.name = name;
            p.initialMoney = amount;
            money.put(name, amount);
            int length = Integer.parseInt(st.nextToken());
            p.gifts = new ArrayList<String>();
            for (int j = 0; j < length; j++) {
                line = text.readLine();
                p.gifts.add(line);
            }
            people.add(p);
        }
        int x = 0;
    }

    public static void orderedList() {
        for (int i = 0; i < order.size(); i++) {
            int num = lookUp(order.get(i));
            Person p = people.get(num);
            int total = money.get(p.name) - p.initialMoney;
            name.add(order.get(i));
            value.add(total);
        }
    }

    public static int lookUp(String name) {
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).name.equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public static void allMoney() {
        for (Person aPerson : people) {
            List<String> gifts = aPerson.gifts;
            for (int j = 0; j < aPerson.gifts.size(); j++) {
                int x = aPerson.initialMoney/gifts.size();
                String name = gifts.get(j);
                int newValue = money.get(name) + x;
                money.put(name, newValue);
                newValue = money.get(aPerson.name) - x;
                money.put(aPerson.name, newValue);
            }
        }
        int x = 0;
    }

    public static void writeFile() throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter(directory + "practice.gift1.out" + fileSuffix));
        for (int i = 0; i < name.size(); i++) {
            text.write(name.get(i) + " " + value.get(i));
            text.newLine();
        }
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        allMoney();
        orderedList();
        writeFile();
    }

    static class Person {
        String name;
        int initialMoney;
        List<String> gifts;
    }
}
