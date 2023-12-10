package seminar3.homework;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static seminar3.homework.StudentApp.*;

public class Program {

    public static void main(String[] args) throws FileNotFoundException {
        Student student = new Student("Olga", 21, 3.5);
        Student student1 = new Student("Tamara", 18, 3.2);
        Student student2 = new Student("Ivan", 19, 4.0);
        Student student3 = new Student("Igor", 17, 3.0);

        StudentApp studentApp = new StudentApp();

        /**
         * файл для представления списка студентов в формате JSON
         */
        File studentsFile = new File(StudentApp.FILE_JSON);


        /**
         * список студентов для демонстрации бинарной записи
         */
        List<Student> students = new ArrayList<>(){{
            add(student);
            add(student1);
            add(student2);
        }};
        List<Student> students2 = null;

        /**
         * список студентов для демонстрации работы библиотеки Джексона
         * с выводом содержимого файла в консоль
         */
        List<Student> studentsInJSON;

        if (studentsFile.exists() && !studentsFile.isDirectory())
            studentsInJSON = loadStudentFromFile(FILE_JSON);
        else {
            studentsInJSON = prepareStudents();
            studentApp.saveStudentsToFile(StudentApp.FILE_JSON, studentsInJSON);
           }

//        studentApp.addNewStudent(student, students);
//        studentApp.addNewStudent(student1, students);
//        studentApp.addNewStudent(student2, students);

/**
 * запись в бинарный файл списка студентов
 */
        studentApp.saveStudentsToFile(FILE_BIN, students);
/**
 * чтение из бирарного файла списка студентов с выводом его в консоль
 */
        studentApp.readStudentsFromFile(FILE_BIN, students2);

//        studentApp.addNewStudent(student3, studentsInJSON);
//        studentApp.addNewStudent(student1, studentsInJSON);

        List<Student> students3 = new ArrayList<>();
        students3 = studentApp.loadStudentFromFile("students.json");
    }

    static List<Student> prepareStudents()
    {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("Olga", 21, 3.5));
        list.add(new Student("Tamara", 18, 3.2));
        list.add(new Student("Ivan", 19, 4.0));
        return list;
    }
}
