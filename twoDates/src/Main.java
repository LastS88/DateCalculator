import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {



    public static void main(String[] args) {


        /////////////////////////////////////////////////////
        // Please write a program that can calculate the number of days in-between two dates.
        // You are not allowed to use java date/time/calendar libraries
        /////////////////////////////////////////////////////
        // Date format: dd/mm-year
        /////////////////////////////////////////////////////


        // Establishing dataset
        int jan = 31;
        int feb = 28;
        int march = 31;
        int apr = 30;
        int may = 31;
        int jun = 30;
        int jul = 31;
        int aug = 31;
        int sep = 30;
        int okt = 31;
        int nov = 30;
        int dec = 31;

        ArrayList<Integer> year = new ArrayList<>();
        year.add(jan);
        year.add(feb);
        year.add(march);
        year.add(apr);
        year.add(may);
        year.add(jun);
        year.add(jul);
        year.add(aug);
        year.add(sep);
        year.add(okt);
        year.add(nov);
        year.add(dec);

        DateMonthYear firstDate;
        DateMonthYear secondDate;

        // Scanner UI

        String regex_Date = "\\d{2}/\\d{2}-\\d{4}";
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("::Day Counter:: ");
            System.out.println("Enter first date:");
            System.out.println("#dd/mm-year");
            Pattern p = Pattern.compile(regex_Date);
            String dateA = scanner.nextLine();
            if (!p.matcher(dateA).matches()) {

                System.out.println("Invalid date format. Please use dd/mm-yyyy.");
                System.out.println("Enter first date:");
                dateA = scanner.nextLine();
            }
            firstDate = stringDateConverter(dateA);


            System.out.println("Enter Second date:");
            System.out.println("#dd/mm-year");
            String dateB = scanner.nextLine();
            if (!p.matcher(dateB).matches()) {
                System.out.println("Invalid date format. Please use dd/mm-yyyy.");
                System.out.println("Enter Second date:");
                dateB = scanner.nextLine();
            }
            secondDate = stringDateConverter(dateB);


            // Ensuring date1 is before date2

            if (firstDate.getYear() > secondDate.getYear() || (firstDate.getMonth() > secondDate.getMonth() && firstDate.getYear() == secondDate.getYear()) || (firstDate.getDay() > secondDate.getDay() && firstDate.getMonth() == secondDate.getMonth() && firstDate.getYear() == secondDate.getYear())) {
                DateMonthYear temp = firstDate;
                firstDate = secondDate;
                secondDate = temp;
            }

            System.out.println("first: " + firstDate);
            System.out.println("Second: " + secondDate);
            System.out.println("===========================================================" );
            System.out.println("Total days between the two dates: " + daysBetweenDates(firstDate, secondDate, year));
            System.out.println("===========================================================" );
            System.out.println(" ");

        }







    }
    // Converting entry date - String -> DateMonthYear.
    public static DateMonthYear stringDateConverter(String dateEntry){
        String[] dataDevided = dateEntry.split("[/-]");
            DateMonthYear date = new DateMonthYear(Integer.parseInt(dataDevided[0]), Integer.parseInt(dataDevided[1]), Integer.parseInt(dataDevided[2]));
            return date;
    }


    public static int daysIntoTheYear(int day, int month, ArrayList<Integer> year){
        int totalDaysIntoTheYear = 0;

        if(month < 2) {
            totalDaysIntoTheYear = day;
        } else {
            for (int i = 0; i < month - 1; i++) {
                totalDaysIntoTheYear = totalDaysIntoTheYear + year.get(i);
            }
            totalDaysIntoTheYear = totalDaysIntoTheYear + day;
        }

        return totalDaysIntoTheYear;
    }

    public static int daysLeftOfTheYear(int day, int month, ArrayList<Integer> year){
        int totalDaysLeft = daysIntoTheYear(day, month, year);
        int daysInAYear = 0;
       for(int i = 0; i < year.size(); i++){
           daysInAYear = daysInAYear + year.get(i);
       }
       return daysInAYear - totalDaysLeft;

    }



    public static int daysBetweenDates(DateMonthYear date1, DateMonthYear date2, ArrayList<Integer> year){

        int totalDays = 0;

        if(date1.getMonth() == date2.getMonth() && date1.getYear() == date2.getYear()) {
            for (int i = date1.getDay(); i < date2.getDay(); i++) {
                totalDays++;
            }
            return totalDays;
        }

        if(date1.getYear() == date2.getYear()){
            totalDays = year.get(date1.getMonth()-1) - date1.getDay();
            for(int i = date1.getMonth()+1; i < date2.getMonth(); i++ ){
                totalDays = totalDays + year.get(i-1);
            }
            return totalDays + date2.getDay() - 1;
        } else {

            totalDays = daysLeftOfTheYear(date1.getDay(), date1.getMonth(), year);

            for(int i = date1.getYear() + 1; i < date2.getYear(); i++){
                for(int j = 0; j < year.size(); j++){
                    totalDays = totalDays + year.get(j);
                }
            }

            return totalDays + daysIntoTheYear(date2.getDay(), date2.getMonth(),year);

        }










    }



}