package labo3;

import java.time.LocalDate;

public class Date {

    private static final int[] DAYS_IN_MONTHS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final String[] MONTH_IN_FRENCH = {
        "Janvier", "Février", "Mars",
        "Avril", "Mai", "Juin", "Juillet", "Aout",
        "Septembre", "Octobre", "Novembre", "Décembre"
    };
    private static final String[] DAY_IN_FRENCH = {
        "Lundi", "Mardi", "Mercredi",
        "Jeudi", "Vendredi", "Samedi",
        "Dimanche"
    };

    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        setMonth(month);
        setYear(year);
        setDay(day);
    }

    
    /*
    Construit la date du jour.
    
    Remarque : comme on appelle 3x LocalDate.now(), un bug potentiel pourrait
    apparaitre par exemple un 31/12 à 23h59:59,9999. 
    Dans ce cas, le dernier appel pourrait avoir lieu l'année suivante.
    
    Le but de cet exercice est d'insister sur l'obligation d'utiliser this(...) 
    uniquement à la première ligne d'un constructeur.
    
    Comme nous pouvons avoir confiance en la librairie Java, ce constructeur
    pourrait s'écrire dans appeler notre autre constructeur.
    */
    public Date() {
        this(LocalDate.now().getDayOfMonth(),
                LocalDate.now().getMonthValue(),
                LocalDate.now().getYear());

    }
    
    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(int day) {
        if (day >= 1 && day <= this.daysInMonth()) {
            this.day = day;
        } else {
            throw new RuntimeException("bad day");
        }
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        if (month >= 1 && month <= 12) {
            if(day>daysInMonth(month,year)){
                throw new RuntimeException("bad month");
            }
            this.month = month;
        } else {
            throw new RuntimeException("bad month");
        }
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        if(day>daysInMonth(month,year)){
            throw new RuntimeException("Bad year");
        }
        this.year = year;
    }
    
    /**
     * increments the date of one day
     *
     */
    public void increment() {
        if (lastDayOfMonth()) {
            setDay(1);
            if (getMonth() == 12) {
                setMonth(1);
                setYear(getYear() + 1);
            } else {
                setMonth(getMonth() + 1);
            }
        } else {
            setDay(getDay() + 1);
        }
    }

    /**
     * @returns The number of days in the given month.
     *
     */
    private static int daysInMonth(int aMonth, int aYear) {
        return DAYS_IN_MONTHS[aMonth - 1] + (isLeapYear(aYear) && aMonth == 2 ? 1 : 0);
    }

    private int daysInMonth() {
        return daysInMonth(getMonth(), getYear());
    }

    /**
     * @returns true if this is the last day of the month.
     */
    private boolean lastDayOfMonth() {
        return getDay() == daysInMonth();
    }

    /**
     * @returns true if it is a leap year.
     */
    public static boolean isLeapYear(int aYear) {
        return aYear % 400 == 0 || (aYear % 100 != 0 && aYear % 4 == 0);
    }

    private boolean isLeapYear() {
        return isLeapYear(getYear());
    }

    /**
     * @returns the day number in the current year this date represents.
     */
    public int dayOfYear() {
        int dayOfYear = this.getDay();
        for (int i = 1; i < getMonth(); i++) {
            dayOfYear += daysInMonth(i, getYear());
        }
        return dayOfYear;
    }
    public String dayOfWeeks(){
        int day = dayOfWeek();
        return DAY_IN_FRENCH[day];
    }

    /**
     * @returns 0=monday, 1=tuesday, ..., 6=sunday see
     * http://en.wikipedia.org/wiki/Zeller's_congruence
     */
    public int dayOfWeek() {
        int m = this.getMonth(); // local copies because
        int y = this.getYear();  // month and year can be modified
        if (m == 1 || m == 2) {
            m += 12;
            y--;
        }

        int century = y / 100;
        int yearOfCentury = y % 100;
        int dayOfWeek = (getDay()
                + (((m + 1) * 26) / 10)
                + yearOfCentury
                + (yearOfCentury / 4)
                + (century / 4)
                + 5 * century) % 7;

        return (dayOfWeek + 5) % 7;
    }

    @Override
    public String toString() {
        return DAY_IN_FRENCH[dayOfWeek()] + " " + getDay() + " "
                + MONTH_IN_FRENCH[getMonth() - 1] + " " + getYear()
                + " le " + dayOfYear() + "-ième jour de l'année";
    }


    public boolean equals(Object obj) {
     if(obj instanceof Date){
         Date d = (Date) obj;
         return (d.day==day && d.month==month && d.year==year);
     }
     return false;
    }
    public int numberOfDays(){
        int nb = dayOfYear();
        int year = this.year-1;

        for(;year>0;year--){
            if(isLeapYear(year)){
                nb+=366;
            }
            else{
                nb+=365;
            }
        }
        return nb;


    }
    public int compareTo(Date d){
        return this.numberOfDays()-d.numberOfDays();

    }
}
