public class Student {

    float x;
    float y;

    float gpa; float taken; float passed; float current; float age; String gender;
    String ageGroup; float attempted; int gradYear; String home; String major;

    public Student(float x, float y) {

        this.x = x;
        this.y = y;
    }

    public Student(float gpa, float taken, float passed, float current, float age, String gender) {

        this.gpa = gpa;
        this.taken = taken;
        this.passed = passed;
        this.current = current;
        this.age = age;
        this.gender = gender;
    }

    public Student(String gender, String ageGroup, float attempted, float passed, float gpa, int gradYear, String home, String major) {

        this.gender = gender;
        this.ageGroup = ageGroup;
        this.attempted = attempted;
        this.passed = passed;
        this.gpa = gpa;
        this.gradYear = gradYear;
        this.home = home;
        this.major = major;
    }
}