
public class DateTester {
    
    public static void main(String[] args){
        Date param = new Date(12,1,2015);
        Date empty = new Date();
        Date copy = new Date(empty);
        System.out.println("Date constructed with parameters: " + param);
        System.out.println("Date constructed with no parameters: " + empty);
        System.out.println("Date constructed from other instance: " + copy);
        System.out.println("Show year, month, day of copy: " + copy.getYear() + 
                "-" + copy.getMonth() + "-" + copy.getDay());
        System.out.print("Reset year, month, day of copy to 2015, 12, 1: ");
        copy.setYear(2015);
        copy.setMonth(12);
        copy.setDay(1);
        System.out.println(copy);
        
        System.out.println("Check Date instances param and copy for equality: " +
                param.equals(copy));
        System.out.println("Check copy and empty for equality: " + copy.equals(empty));
        System.out.println("Show day number of Param: " + param.getDayNumber());
        System.out.println("Show day number of empty: " + empty.getDayNumber());
        System.out.println("Show days apart between param and empty: " + param.getDaysApart(empty));
        System.out.print("Add 10 days to copy: ");
        copy.addDays(10);
        System.out.println(copy);
        System.out.print("Add 31 days to copy: ");
        copy.addDays(31);
        System.out.println(copy);
        System.out.print("Add 250 days to copy: ");
        copy.addDays(250);
        System.out.println(copy);
        System.out.print("Add 365 days to copy: ");
        copy.addDays(365);
        System.out.println(copy);
        System.out.print("Add 1000 days to copy: ");
        copy.addDays(1000);
        System.out.println(copy);
        System.out.print("Subtract 10 days to copy: ");
        copy.addDays(-10);
        System.out.println(copy);
        System.out.print("Subtract 31 days to copy: ");
        copy.addDays(-31);
        System.out.println(copy);
        System.out.print("Subtract 250 days to copy: ");
        copy.addDays(-250);
        System.out.println(copy);
        System.out.print("Subtract 365 days to copy: ");
        copy.addDays(-365);
        System.out.println(copy);
        System.out.print("Subtract 1000 days to copy: ");
        copy.addDays(-1000);
        System.out.println(copy);
        
        System.out.println("Compare param and empty: " + param.compareTo(empty));
        System.out.println("Compare param and copy: " + param.compareTo(copy));
        System.out.println("Compare empty and param: " + empty.compareTo(param));
    }
}
