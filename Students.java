import java.io.*;
import java.util.*;

public class Students {
    private int[] grades;


    public int[] insertGrades(String fileName) {
        List<Integer> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length != 4) continue;

                int packed = 0;
                for (int i = 0; i < 4; i++) {
                    int grade = Integer.parseInt(tokens[i].trim());
                    packed |= (grade & 0xFF) << (i * 8);
                }
                list.add(packed);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        grades = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            grades[i] = list.get(i);
        }
        return grades;
    }


    public void displayHex() {
        for (int grade : grades) {
            System.out.printf("%08X", grade);
        }
        System.out.println();
    }


    public int getStudentExam(int i, int j) {
        if (grades == null || i < 0 || i >= grades.length || j < 0 || j > 4) {
            return -1;
        }
        return (grades[i-1] >> ((j-1) * 8)) & 0xFF;
    }


    public void setStudentExam(int i, int j, int k) {
        if (grades == null || i < 0 || i >= grades.length || j < 0 || j > 4 || k < 0 || k > 100) {
            return;
        }
        grades[i-1] &= ~(0xFF << ((j-1) * 8));
        grades[i-1] |= (k & 0xFF) << ((j-1) * 8);
    }


    public float averageStudent(int i) {
        if (grades == null || i < 0 || i >= grades.length) return -1;

        int packed = grades[i-1];
        int sum = 0;
        for (int j = 0; j < 4; j++) { // fix: include exam 1
            sum += (packed >> (j * 8)) & 0xFF;
        }
        return sum / 4.0f;
    }



    public float averageExam(int j) {
        if (grades == null || grades.length == 0 || j < 0 || j > 4) { // fix: j < 4
            return -1;
        }

        int sum = 0;
        for (int i = 0; i < grades.length; i++) { // fix: include all students
            sum += (grades[i] >> ((j-1) * 8)) & 0xFF;
        }
        return sum / (float) grades.length;
    }

}
