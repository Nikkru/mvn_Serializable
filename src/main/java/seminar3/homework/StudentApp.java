package seminar3.homework;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class StudentApp {
    public static final String FILE_JSON = "students.json";
    public static final String FILE_BIN = "students.bin";
    public static final String FILE_XML = "students.xml";

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

//    public static void addNewStudent(Student newStudent, List<Student> students) {
//        students.add(newStudent);
//        System.out.println("Success");
//    }

    /**
     * метод сохранения списка студентов
     * @param fileName название файла для сохранения списка студентов
     * @param students список студентов для сохранения в файл
     * @throws FileNotFoundException
     */
    public static void saveStudentsToFile (String fileName, List<Student> students) throws FileNotFoundException {
        try(FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(students);
            System.out.println("Ok. userdata is serializable.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * метод чтения из файла списка студентов
     * @param fileName имя файла для чтения
     * @param students список для извлечения данных из файла
     */
    public static void readStudentsFromFile(String fileName, List<Student> students) {
        try(FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            students = (List<Student>) objectInputStream.readObject();
            System.out.println("Ok. userdata is deserializable.\n");
            for (Student student : students) {
                System.out.println(student);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * метод добавляющий новый экземпляр класса в список и записывающих его в файлы формата JSON, BIN, XML
     * @param student - новый экземпляр класса
     * @param students - список экземпляров класса
     */
    public static void addNewStudent(Student student, List<Student> students) {
        students.add(student);
        saveStudentToFiles(FILE_JSON, students);
        saveStudentToFiles(FILE_BIN, students);
        saveStudentToFiles(FILE_XML, students);
        System.out.println("Student by name " + student.getName() + " are here.");
    }

    /**
     * метод записывающий список студентов в бинарный файл
     * @param fileName название файла
     * @param students список для записи
     */
    public static void saveStudentToFiles(String fileName, List<Student> students) {
        try {
            if (fileName.endsWith(".json")) {
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                objectMapper.writeValue(new File(fileName), students);
            } else if (fileName.endsWith(".bin")) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                    oos.writeObject(students);
                }
            } else if (fileName.endsWith(".xml")) {
                xmlMapper.writeValue(new File(fileName), students);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * метод загрузки из файла списка студентов
     * @param fileName название файла
     * @return возвращение списка студентов, содержащегося в файле
     */
    public static List<Student> loadStudentFromFile(String fileName) {
        List<Student> students = new ArrayList<>();

        File file = new File(fileName);
        if (file.exists()) {
            try {
                if (fileName.endsWith(".json")) {
                    students = objectMapper.readValue(
                            file,
                            objectMapper.getTypeFactory().constructCollectionType(List.class, Student.class));
                    Object json = objectMapper.readValue(file, Object.class);
                    String prettyJsonString = objectMapper.writerWithDefaultPrettyPrinter()
                            .writeValueAsString(json);
                    System.out.println("файл " + fileName + " cодержит седующий список студентов: " + prettyJsonString);
                } else if (fileName.endsWith(".bin")) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        students = (List<Student>) ois.readObject();
                    }
                } else if (fileName.endsWith(".xml")) { students = xmlMapper.readValue(file,
                        xmlMapper.getTypeFactory().constructCollectionType(List.class, Student.class));
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return students;
    }
}
