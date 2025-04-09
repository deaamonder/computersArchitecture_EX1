// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Students s = new Students();
        s.insertGrades("C:/אוניברסיטה/grades.txt");
        s.displayHex();
        System.out.println("\nציוני תלמיד 0:");
        for (int j = 0; j < 4; j++) {
            System.out.printf("קורס %d: %d\n", j, s.getStudentExam(2, j));
        }

        System.out.println("\nממוצע של תלמיד 0: " + s.averageStudent(0));
        System.out.println("ממוצע בקורס 2: " + s.averageExam(2));

        System.out.println("\nעידכון ציון...");
        s.setStudentExam(0, 1, 100); // שינוי קורס 1 של תלמיד 0 ל-100

        System.out.println("אחרי עידכון:");
        s.displayHex();
        System.out.println("ציון חדש בקורס 1: " + s.getStudentExam(0, 1));
    }
}