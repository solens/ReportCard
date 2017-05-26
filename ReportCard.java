package com.example.android.reportcard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static android.media.CamcorderProfile.get;

/**
 * Created by Solen on 5/21/2017.
 */


public class ReportCard {

    private ArrayList<Double> classAverage;
    private ArrayList<String> classNames;
    private String firstName;
    private String lastName;
    private int studentID;
    private HashMap<String,Double[][]> grades;

    // ReportCard includes:
    // -firstName: String with getter ans setter
    // -lastName: String with getter and setter
    // -studentID number: int with getter and setter
    // -grades: Hashmap with getter and setter where keys are the name of the courses and the grades are in format Double[][]
    //      where the first row is the exam and assignment grades in % (so 90% would be written in as 90)
    //      and the second row is the weights of the exams and assignments as decimal values.
    //      The second row should add up to 1.
    // Other methods are:
    // -getClassNames: returns classNames, an ArrayList<String> with the names of all the classes this student is taking
    // -getClassAverages: returns an ArrayList<Double> of the averages for all classes this student is taking
    // -AddClass: takes a String nameOfClass and a Double[][] where the first row is the exam and assignment grades
    //      in % (so 90% would be written in as 90) and the second row is the weights of the exams and assignments
    //      as decimal values. Adds this new class and its grades to the grades Hashmap
    // -toString(): returns a description of this students Report Card with all informastions including all grades
    //      and class averages.
    //

    public ReportCard(String first_name,String last_name, int idNum,HashMap<String,Double[][]> gradesList){
        firstName = first_name;
        lastName = last_name;
        studentID = idNum;
        grades = gradesList;
        classNames = new ArrayList<>(gradesList.keySet());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public HashMap<String, Double[][]> getGrades() {
        return grades;
    }

    public void setGrades(HashMap<String, Double[][]> grades) {
        this.grades = grades;
    }

    public ArrayList<String> getClassNames() {
        return classNames;
    }

    private double getClassGrade(Double[][] oneClass){
        double sum = 0;
        for(int i=0;i<oneClass[0].length;i++){
            sum+=oneClass[0][i]*oneClass[1][i];
        }
        return sum;
    }

    public ArrayList<Double> getClassAverages() {
        classAverage = new ArrayList<Double>(0);
        for(Double[][] grade : grades.values()){
            classAverage.add(getClassGrade(grade));
        }
        return classAverage;
    }

    public void addClass(String classTitle,Double[][] gradesAndWeights){
        this.grades.put(classTitle,gradesAndWeights);
    }

    @Override
    public String toString() {
        String gradesToString = "";
        for (String course: grades.keySet()){

            Double[][] value = grades.get(course);
            Double[] examGrades = value[0];
            Double[] examWeights = value[1];

            gradesToString+="\n" + course + ": Exam Grades in %:" + Arrays.toString(examGrades) + "\n Exam Weights: " + Arrays.toString(examWeights);

        }
        Double[] classAveragesArray = getClassAverages().toArray(new Double[classNames.size()]);

        return "ReportCard:" +
                "\nclassNames=" + classNames +
                "\nfirstName='" + firstName + '\'' +
                "\nlastName='" + lastName + '\'' +
                "\nstudentID=" + studentID +
                "\ngrades=" + gradesToString +
                "\nclassAverages=" + Arrays.toString(classAveragesArray);
    }
}
