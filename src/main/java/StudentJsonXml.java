import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.io.*;


public class StudentJsonXml implements Serializable {
    private String name;
    private int age;
    private transient double GPA;

    public StudentJsonXml(String name, int age, double GPA) {
        this.name = name;
        this.age = age;
        this.GPA = GPA;
    }


    public void serializeToJson(String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StudentJsonXml deserializeFromJson(String filePath) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(filePath)) {
            StudentJsonXml studentJsonXml = gson.fromJson(reader, StudentJsonXml.class);
            return studentJsonXml;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void serializeToXml(String filePath) {
        XStream xstream = new XStream();
        try (Writer writer = new FileWriter(filePath)) {
            xstream.toXML(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StudentJsonXml deserializeFromXml(String filePath) {
        XStream xstream = new XStream();
        try (Reader reader = new FileReader(filePath)) {
            StudentJsonXml studentJsonXml = (StudentJsonXml) xstream.fromXML(reader);
            return studentJsonXml;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", GPA=" + GPA +
                '}';
    }

    public static void main(String[] args) {

        XStream xStream = new XStream();
        xStream.addPermission(NoTypePermission.NONE);
        xStream.addPermission(AnyTypePermission.ANY);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Пример сериализации объекта в JSON
        StudentJsonXml studentJsonXml = new StudentJsonXml("John Doe", 25, 4.7);
        String json = gson.toJson(studentJsonXml);
        System.out.println("JSON: " + json);

        // Пример десериализации объекта из JSON
        StudentJsonXml deserializedStudent = gson.fromJson(json, StudentJsonXml.class);
        System.out.println("Deserialized student: " + deserializedStudent);

        // Пример сериализации объекта в XML
        String xml = xStream.toXML(studentJsonXml);
        System.out.println("XML: " + xml);

        // Пример десериализации объекта из XML
        StudentJsonXml deserializedStudentXml = (StudentJsonXml) xStream.fromXML(xml);
        System.out.println("Deserialized student from XML: " + deserializedStudentXml);
    }
}