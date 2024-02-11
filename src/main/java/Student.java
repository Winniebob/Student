import java.io.*;

public class Student implements Serializable {
    private String name;
    private int age;
    private transient double GPA;

    public Student(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGPA() {
        return GPA;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public static void main(String[] args) {
        // Создание и инициализация объекта класса Student
        Student student = new Student("Ivanov", 20, 4.5);

        // Сериализация объекта в файл
        try {
            FileOutputStream fileOut = new FileOutputStream("student.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(student);
            out.close();
            fileOut.close();
            System.out.println(
                    "The object is serialized and stored in the Student.ser file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

       // Десериализация объекта из файла
        Student deserializedStudent = null;
        try {
            FileInputStream fileIn = new FileInputStream("student.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            deserializedStudent = (Student) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Вывод полей объекта, включая GPA
        if (deserializedStudent != null) {
            System.out.println("Name: " + deserializedStudent.getName());
            System.out.println("Age: " + deserializedStudent.getAge());
            System.out.println("GPA: " + deserializedStudent.getGPA());
        }

        // Ответ на вопрос
        System.out.println("The GPA value was not saved and restored, because it was declared with the transient keyword . ");
    }
}