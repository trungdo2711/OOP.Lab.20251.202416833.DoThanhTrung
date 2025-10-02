import java.util.Scanner;
public class Bai64MonthProgram {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter month: ");
        String input = scanner.nextLine().trim();
        input = input.replace(".", "").toLowerCase();

        int month;

        switch(input){
            case "1": case "jan": case "january":
                month = 1;
                break;
            case "2": case "feb": case "february":
                month = 2;
                break;
            case "3": case "mar": case "march":
                month = 3;
                break;
            case "4": case "april": case "apr":
                month = 4;
                break;
            case "5": case "may":
                month = 5;
                break;
            case "6": case "june": case "jun":
                month = 6;
                break;
            case "7": case "july": case "jul":
                month = 7;
                break;
            case "8": case "august": case "aug":
                month = 8;
                break;
            case "9": case "september": case "sep":
                month = 9;
                break;
            case "10": case "october": case "oct":
                month = 10;
                break;
            case "11": case "november": case "nov":
                month = 11;
                break;
            case "12": case "december": case "dec":
                month = 12;
                break;
            default:
                System.out.println("Invalid month!");
                return;
        }

        switch(month){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                System.out.println("31 days");
                break;
            case 4: case 6: case 9: case 11:
                System.out.println("30 days");
                break;
            case 2:
                if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0){
                    System.out.println("29 days");
                }
                else{
                    System.out.println("28 days");
                }
                break;
            default:
                System.out.println("Invalid month");
        }
    }
}