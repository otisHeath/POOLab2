package labo3;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DateTest {





    @Test
    public void testIncrementSimple() {
        Date d = new Date(1, 1, 2012);
        d.increment();
        assertEquals(2, d.getDay());
        assertEquals(1, d.getMonth());
        assertEquals(2012, d.getYear());
    }

    @Test
    public void testIncrementEndMonth() {
        Date d = new Date(31, 1, 2012);
        d.increment();
        assertEquals(1, d.getDay());
        assertEquals(2, d.getMonth());
        assertEquals(2012, d.getYear());
    }

    @Test
    public void testIncrementEndYear() {
        Date d = new Date(31, 12, 2012);
        d.increment();
        assertEquals(1, d.getDay());
        assertEquals(1, d.getMonth());
        assertEquals(2013, d.getYear());
    }

    @Test(expected = RuntimeException.class)
    public void testConstructorInvalidMonth() {
        new Date(1, -1, 2012);
    }

    @Test(expected = RuntimeException.class)
    public void testConstructorInvalidMonth1() {
        new Date(1, 0, 2012);
    }

    @Test(expected = RuntimeException.class)
    public void testConstructorInvalidMonth2() {
        new Date(1, 13, 2012);
    }

    @Test
    public void testConstructorValid() {
        Date d = new Date(16, 2, 2012);
        assertEquals(16, d.getDay());
        assertEquals(2, d.getMonth());
        assertEquals(2012, d.getYear());
    }

    @Test
    public void testConstructorValidWithoutParam() {
        Date d = new Date();
        assertEquals(LocalDate.now().getDayOfMonth(), d.getDay());
        assertEquals(LocalDate.now().getMonthValue(), d.getMonth());
        assertEquals(LocalDate.now().getYear(), d.getYear());

    }

    @Test
    public void testDayOfLeapYear() {
        Date d = new Date(1, 1, 2004);
        Date d2 = new Date(31, 12, 2004);
        assertEquals(1, d.dayOfYear());
        assertEquals(366, d2.dayOfYear());

    }

    @Test
    public void testDayOfYear() {
        Date d = new Date(1, 1, 2005);
        Date d2 = new Date(31, 12, 2005);
        assertEquals(1, d.dayOfYear());
        assertEquals(365, d2.dayOfYear());
    }

    @Test
    public void testDayOfWeek() {
        Date d = new Date(4, 10, 2022);
        assertEquals("Mardi", d.dayOfWeeks());

    }

    @Test
    public void testStaticisLeapYear() {
        assertEquals(true, Date.isLeapYear(2004));
        assertEquals(false, Date.isLeapYear(2005));
    }

    @Test
    public void testEqualsDate() {
        Date d = new Date(1, 1, 2004);
        Date d2 = new Date();
        Date d3 = new Date();
        Date d4 = new Date(1, 1, 2005);
        Date d5 = new Date(1, 1, 2004);
        assertEquals(true, d2.equals(d3));
        assertEquals(false, d.equals(d4));
        assertEquals(true, d.equals(d5));
        assertEquals(false, d3.equals(d5));


    }

    @Test
    public void testDateCompareTo() {
        Date d = new Date(1, 1, 2004);
        Date d2 = new Date(1, 1, 2005);
        Date d3 = new Date(1, 1, 2004);
        assertTrue(d.compareTo(d2) < 0);
        assertTrue(d2.compareTo(d) > 0);
        assertTrue(d.compareTo(d3) == 0);

    }
    @Test
    public void testSortDate() {
        Date d =new Date();
        Date d2 = new Date(1,1,2000);
        Date d3 = new Date(5,2,1999);
        Date d4 = new Date(2,2,2001);
        Date d5 = new Date(6,7,1999);
        Date d6 = new Date(7,7,2005);
        Date [] tab = {d, d2, d3, d4, d5, d6};
        Program.sortDate(tab);

        assertEquals(d3,tab[0]);
        assertEquals(d5,tab[1]);
        assertEquals(d2,tab[2]);
        assertEquals(d4,tab[3]);
        assertEquals(d6,tab[4]);
        assertEquals(d,tab[5]);

    }

    @Test
    public void testPersonCompareTo() {
        Person p = new Person("", "", 1, 1, 2002);
        Person p2 = new Person("", "", 2, 1, 2002);
        Person p3 = new Person("", "", 1, 1, 2002);
        assertTrue(p.compareTo(p2) > 0);
        assertTrue(p2.compareTo(p) < 0);
        assertTrue(p.compareTo(p3) == 0);


    }

    @Test
    public void testFebruaryLeapYear2012() {
        Date d = new Date(29, 2, 2012);
        assertNotNull(d);
        d.increment();
        assertEquals(1, d.getDay());
        assertEquals(3, d.getMonth());
    }

    @Test
    public void testFebruaryLeapYear2000() {
        Date d = new Date(29, 2, 2000);
        assertEquals(29, d.getDay());
        assertEquals(2, d.getMonth());
        assertEquals(2000, d.getYear());
    }

    @Test(expected = RuntimeException.class)
    public void testFebruaryLeapYear1900() {
        new Date(29, 2, 1900);
    }

    @Test
    public void testFebruary2012Increment() {
        Date d = new Date(29, 2, 2012);
        d.increment();
        assertEquals(1, d.getDay());
        assertEquals(3, d.getMonth());
    }

    @Test
    public void testFebruary1900Increment() {
        Date d = new Date(28, 2, 1900);
        d.increment();
        assertEquals(1, d.getDay());
        assertEquals(3, d.getMonth());
    }

    @Test
    public void testToString() {
        Date d = new Date(1, 3, 2012);
        assertEquals("Jeudi 1 Mars 2012 le 61-ième jour de l'année",
                d.toString());
    }

    @Test(expected = RuntimeException.class)
    public void testSetYearLeapToNotLeap() {
        Date d = new Date(29, 2, 2012);
        d.setYear(2011);
    }

    @Test(expected = RuntimeException.class)
    public void testSetMonthToSmaller() {
        Date d = new Date(31, 1, 2012);
        d.setMonth(2);

    }



}

