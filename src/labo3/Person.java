package labo3;


public class Person {

    private final String firstName;
    private final String lastName;
    private final Date dateOfBirth;

    public Person(String firstName, String lastName, 
            int day, int month, int year) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = new Date(day, month,year);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " né le " + dateOfBirth
                + ", il a " + getAge() + " ans.";
    }

    public int getAge() {
        Date today = new Date();
        int age = today.getYear() - dateOfBirth.getYear();
        if (today.getMonth() < dateOfBirth.getMonth()
                || (today.getMonth() == dateOfBirth.getMonth() 
                    && today.getDay() < dateOfBirth.getDay())) {
            age--;
        }
        return age;
    }
    public int daysOfLife(){
        Date d = dateOfBirth;
        Date today = new Date();
        int a = today.numberOfDays();
        int b = d.numberOfDays();


        return a- b;



    }
    public int compareTo(Person p ){
        return this.daysOfLife()-p.daysOfLife();


    }
}
