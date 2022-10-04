package labo3;

import java.util.ArrayList;

public class Program {
    public static void sortDate(Date [] tab){
        for(int i = 0;i< tab.length;i++){
            int minInd = i;
            for(int j = i+1;j< tab.length;j++){
                if(tab[j].compareTo(tab[minInd])<0){
                    minInd = j;
                }

            }
            Date tmp = tab[i];
            tab[i] = tab[minInd];
            tab[minInd] = tmp;

        }


    }

    public static void main(String[] args) {
        Date[] tab = {new Date(),new Date(1,1,2000),
                new Date(5,2,1999),new Date(2,2,2001)
        ,new Date(6,7,1999),new Date(7,7,2005)};

        for(Date d : tab){
            System.out.println(d);
        }
        System.out.println("Affichage du tableau trié");
        sortDate(tab);
        for(Date d : tab)
            System.out.println(d);


    }
}
