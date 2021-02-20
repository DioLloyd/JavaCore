package ru.diolloyd.javacore.lesson9;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Course math = new Course("math");
        Course language = new Course("language");
        Course geometry = new Course("geometry");
        Course literature = new Course("literature");
        Course physics = new Course("physics");
        Course history = new Course("history");
        Course geography = new Course("geography");
        Course biology = new Course("biology");

        Student karl = new Student("Karl", Arrays.asList(math, language, geometry));
        Student james = new Student("James", Arrays.asList(math, literature, physics));
        Student bobby = new Student("Bobby", Arrays.asList(math, history, geography, language));
        Student miller = new Student("Miller", Arrays.asList(math, language, geometry, biology, physics));

        List<Student> students = new ArrayList<>();
        students.add(karl);
        students.add(james);
        students.add(bobby);
        students.add(miller);

//        Первое задание
        System.out.println("Unique course list:");
        uniqCourses(students)
                .stream().map(Course::getName)
                .forEach(System.out::println);

        System.out.println();
//        Второе задание
        System.out.println("The most curiosity students:");
        curiosityStudents(students).stream()
                .map(Student::getName)
                .forEach(System.out::println);

        System.out.println();
//        Третье задание
//        Чтобы сравнивались не объекты, а значения объектов, переопределяется equals у Course
        Course course = new Course("physics");
        System.out.println("Students attending the course '" + course.getName() + "':");
        studentsAttendingCourse(students, course).stream()
                .map(Student::getName)
                .forEach(System.out::println);

    }

    public static List<Student> studentsAttendingCourse(List<Student> studentList, Course course) {
        return studentList.stream()
                .filter(student -> student.getAllCourses().contains(course))
                .collect(Collectors.toList());
    }

    public static List<Student> curiosityStudents(List<Student> studentList) {
        return studentList.stream()
                .sorted(Comparator.<Student>comparingInt(o -> o.getAllCourses().size()).reversed())
//                или можно записать иначе
//                .sorted((o1, o2) -> o2.getAllCourses().size() - o1.getAllCourses().size())
                .limit(3)
                .collect(Collectors.toList());

    }

    public static List<Course> uniqCourses(List<Student> studentList) {
        return studentList.stream()
                .flatMap(student -> student.getAllCourses().stream())
                .distinct()
                .collect(Collectors.toList());
    }

}

class Student {
    private String name;
    private List<Course> courses;

    Student(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", courses=" + courses +
                '}';
    }
}

class Course {
    private String name;

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return name.equals(course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}