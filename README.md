# Lab2 Java 
## [Файл с кодом находится здесь](https://github.com/ThisisFinee/Lab2JavaFiles/blob/d27ed5dab59f5ab288634aedac5347b7ad9fdbcb/src/Main.java)
---
## Функции
### Основные функции:
#### Функции проверки существования папок:
##### Проверка существования папки счётчика(Counter):
```java
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
```
#### Проверка существования папки текста(Text):
```java
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
```
В этих двух функциях мы проверяем существование нужных нам папок(счётчика и текста) и в случае их отсутствия создаём их, запускаются в самом начале, до всех других функций.
---
#### Функция проверки файла:
```java
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
```
Мы проверяем существование файла(в папке Text и папке Counter, которые мы создали ранее), данную функцию мы будем вызывать изо всех других функций связанных с файлами, для того чтобы избежать ошибок с удалением файлов.
Если какого-то из файлов не существует, то мы используем функцию Create_File(для создания файлов), указывая путь(root,root1):
> ```java
> public static void Create_file(String root){
>       try {
>            File myObj = new File(root);
>            if (myObj.createNewFile()) {
>                System.out.println("File created: " + myObj.getName());
>            }
>        } catch (IOException e) {
>            System.out.println("An error occurred.");
>            e.printStackTrace();
>        }
>    } 
  #### Функция чтения данных из файла:
  ```java
    public static String readFiles(String name) throws IOException {
        String root1 = FullPathText(name);
        return new String(Files.readAllBytes(Paths.get(root1)));
    }
  ```
  Получает на вход имя файла далее прогоняет функцию нахождения пути для файлов с текстом(FullPathText):
 > ```java
 > public static String FullPathText(String name){
 >       String root1 = System.getProperty("user.dir");
 >       root1 = root1 + "//Text"+"//"+name+".txt";
 >       return root1;
 >   }
 После чего возвращает содержимое файла в виде строки.
 
 #### Функция подсчёта английских букв в тексте:
 ```java
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
 ```
 В первую очередь используется функция для нахождения всех букв в единичном экземпляре(OnlyOneLetter):
 ```java
 public static String OnlyOneLetter(String Text){
        String TextWithoutRepeat = "";
        for (char c : Text.toCharArray()){
            char[] m = {c};
            String con = new String(m);
            if (!TextWithoutRepeat.contains(con) && con.matches("[a-zA-z]*"))
                TextWithoutRepeat += c;}
        return TextWithoutRepeat;
    }
 ```
После чего проходим сравнивая каждый символ из строки с символами из полученного массива. Результат записываем как текст в форме: буква - кол-во в тексте(Маленькие и большие буквы считаются как разные)

#### Функция записи текста в файл(не ответа):
```java
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
```
Данная функция принимает на вход название файла и строку которую надо записать. Может быть использована вместе с Функцией ввода текста(в несколько строк)(TextInput)(Для остановки требуется задать пустую строку).
```java
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
```
После чего с помощью FileWriter записывает в файл то текстовое значение, которое нам нужно.

#### Функция очистки файлов(Не используется, но имеется):
```java
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
```
Перезаписывает файлы(счётчик, текстовый) с пустой строкой, использует функции FullPathText(Ранее уже появлялась) и FullPathCount(будет ниже) для нахождения пути к файлам.
Поиск пути к счётчику(FullPathCount):
```java
 public static String FullPathCount(String name){
        String root = System.getProperty("user.dir");
        root = root + "//Counter"+"//"+name+".txt";
        return root;
    }
```
Основная операция(MainOperation):
```java
 public static void MainOperation(String name) throws IOException{
        isFileExists(name);
        String FullFile = readFiles(name);
        String root_count = FullPathCount(name);
        String Result = LetterCount(FullFile);
        try (FileWriter writer = new FileWriter(root_count, false)){
            writer.write(Result);
        }
    }
```
Сначала проверяет существование файла, после чего получает его содержимое и путь к нему, после чего производит над содержимым операцию подсчёта букв и записывает в файл счётчик результат.
---
Main(Точка запуска программы):
```java
 public static void main(String[] args) throws IOException {
        isMainDirectoryExists();
        isSecondaryDirectoryExists();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter file name:");
        String name = in.next();
        System.out.println("Do you need to write text to the file (yes/no)?");
        String ask = in.next();
        if (ask == "yes"){
            System.out.println("Enter some text to write to file(Enter an empty string to end input):");
            String SomeText = TextInput();
            WriteToFile(name, SomeText);
        }
        MainOperation(name);
    }
```
Сначала проверяет существовуют ли папки, после чего вводится имя файла, дополнительно спрашивается нужно ли нам через консоль заполнить текстовый файл(не счётчик), в случае если ответ положительный ввод производится(с помощью функции TextInput), до тех пор пока не будет передана пустая строка. После чего выполняет подсчёт букв в файле и последующую запись в другой файл(счётчик).
Файлы хранятся в папках Text(файлы с текстом) и Conter(файлы счётчики), создаются они автоматически в рабочей директории(функции isMainDirecoryExists и isSecondaryDirectoryExists)

  
