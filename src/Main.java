import static java.lang.System.in;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
public class Main {
    public static void Create_file(String root){
        try {
            File myObj = new File(root);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String OnlyOneLetter(String Text){
        String TextWithoutRepeat = "";
        for (char c : Text.toCharArray()){
            char[] m = {c};
            String con = new String(m);
            if (!TextWithoutRepeat.contains(con) && con.matches("[a-zA-z]*"))
                TextWithoutRepeat += c;}
        return TextWithoutRepeat;
    }
    public static String LetterCount(String Text){
        String One = OnlyOneLetter(Text);
        char[] Two = One.toCharArray();
        char[] Three = Text.toCharArray();
        ArrayList<Character> alphabet = new ArrayList<Character>();
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i< Three.length; i++){
            String prom = String.valueOf(Three[i]);
            list.add(prom);
        }
        for (int i = 0; i < Two.length; i++)
        {
            alphabet.add(Two[i]);
        }
        String result = "";
        int sum = 0;
        for (Character c : Two){
            int count = 0;
            for (String s : list)
            {
                for (int i = 0; i < s.length(); i++)
                {
                    if(s.charAt(i) == c)
                    {
                        count++;
                    }
                }
            }
            result += c + "-" + count+"\n";
            sum+=count;
        }
        result += "Sum:" + sum;
        return result;
    }

    public static String FullPathText(String name){
        String root1 = System.getProperty("user.dir");
        root1 = root1 + "//Text"+"//"+name+".txt";
        return root1;
    }
    public static String FullPathCount(String name){
        String root = System.getProperty("user.dir");
        root = root + "//Counter"+"//"+name+".txt";
        return root;
    }

    public static void ClearFile(String name) throws IOException {
        isFileExists(name);
        String root = FullPathText(name);
        String root1 = FullPathCount(name);
        String input = "";
        try (FileWriter writer = new FileWriter(root, false)){
            writer.write(input);
        }
        try (FileWriter writer1 = new FileWriter(root1, false)){
            writer1.write(input);
        }
    }
    public static String readFiles(String name) throws IOException {
        String root1 = FullPathText(name);
        return new String(Files.readAllBytes(Paths.get(root1)));
    }

    public static void WriteToFile(String name, String input) {
        isFileExists(name);
        String root1 = FullPathText(name);
        try (FileWriter writer = new FileWriter(root1, false)){
            writer.write(input);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String TextInput(){
        Scanner sc = new Scanner(System.in);
        String result = "";
        ArrayList<String> list = new ArrayList<String>();
        String current = sc.nextLine();
        while(!current.equals("")){
            list.add(current);
            current = sc.nextLine();
        }
        String[] list2 = list.toArray(new String[0]);
        for (int i = 0; i<list2.length; i++){
            result += list2[i] +"\n";
        }
        return result;
    }
    public static void MainOperation(String name) throws IOException{
        isFileExists(name);
        String FullFile = readFiles(name);
        String root_count = FullPathCount(name);
        String Result = LetterCount(FullFile);
        try (FileWriter writer = new FileWriter(root_count, false)){
            writer.write(Result);
        }
    }
    public static void isFileExists(String name){
        String root = System.getProperty("user.dir");
        String root1 = root;
        root1 = root1 + "//Text"+"//"+name+".txt";
        root = root + "//Counter"+"//"+name+".txt";
        File file = new File(root);
        File file1 = new File(root1);
        if (file.exists()){
            System.out.println("The counter file has already been created");
        }
        else{
            System.out.println("No counter file");
            Create_file(root);
        }
        if (file1.exists()){
            System.out.println("The text file has already been created");
        }
        else{
            System.out.println("No text file");
            Create_file(root1);
        }
    }
    public static void isSecondaryDirectoryExists(){
        String root = System.getProperty("user.dir");
        String name = "/Text";
        root = root + name;
        File Tex = new File(root);
        Path root1 = Paths.get(root);
        if (Files.exists(root1)) {
            System.out.println("Secondary directory exists");
        }
        else {
            System.out.println("Secondary directory doesn't exist, Create a new directory");
            Tex.mkdir();
            System.out.println("Secondary directory created");
        }
    }
    public static void isMainDirectoryExists(){
        String root = System.getProperty("user.dir");
        String name = "/Counter";
        root = root + name;
        File Cou = new File(root);
        Path root1 = Paths.get(root);
        if (Files.exists(root1)) {
            System.out.println("Main directory exists");
        }
        else {
            System.out.println("Main directory doesn't exist, Create a new directory");
            Cou.mkdir();
            System.out.println("Main directory created");
        }
    }
    public static void main(String[] args) throws IOException {
        isMainDirectoryExists();
        isSecondaryDirectoryExists();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter file name:");
        String name = in.next();
        System.out.println("Enter some text to write to file(Enter an empty string to end input):");
        String SomeText = TextInput();
        WriteToFile(name, SomeText);
        MainOperation(name);
    }}
