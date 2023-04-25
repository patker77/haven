package com.josephNzi;

/**
 * Deze app is bedoel om het lossen van één containerschip te simuleren.
 * <ul>
 *     <li>Een containerschip is aangekomen in de Rotterdamse haven en ligt aan de kade.</li>
 *     <li>Containers worden van het schip afgetakeld met behulp van twee kranen.</li>
 *     <li>De kranen zetten containers neer op de kade, gesteld dat er genoeg plek is om eencontainer neer te zetten.</li>
 *     <li>De op de kade geplaatste containers worden opgehaald en weggereden door drievrachtwagens.
 *     <li>Let op, vrachtwagens maken geen gebruik van de kranen. Containersworden door de vrachtwagens zelf opgepakt.</li>
 *     <li>Het uit het schip takelen en op de kade plaatsen van een container kost tijd. De ene keerduurt dat iets langer dan de andere</li>
 *     <li>Iedere vrachtwagen zal enige tijd bezig zijn met het wegrijden van een container. Ook ditduurt de ene keer iets langer dan de andere.</li>
 * </ul>
 * @author Joseph Nzi
 * @version %I% %G%
 * @since 1.0
 */

public class App {

    public static void main(String[] args) {

        //Containerschip instantieren en containers aanmaken

        Containerschip containerschip = new Containerschip();
        containerschip.containersAanmaken();

        // Thread Kade en Vrachtwagen instantieren

        Kade kade = new Kade();
            Kranen kranen = new Kranen("kraan 1: ",containerschip,kade);
            Thread kraneThread = new Thread(kranen);
            kraneThread.setName(kranen.getNaam());

            Kranen kranen2 = new Kranen("kraan 2: ",containerschip,kade);
            Thread kraneThread2 = new Thread(kranen2);
            kraneThread2.setName(kranen2.getNaam());

        Vrachtwagens vrachtwagens= new Vrachtwagens("vrachrwagen A: ",containerschip,kade);
        Thread vrachtwagenThread = new Thread(vrachtwagens);
        vrachtwagenThread.setName(vrachtwagens.getNaam());

        Vrachtwagens vrachtwagens2 = new Vrachtwagens("vrachrwagen B: ",containerschip,kade);
        Thread vrachtwagenThread2 = new Thread(vrachtwagens2);
        vrachtwagenThread2.setName(vrachtwagens2.getNaam());

        Vrachtwagens vrachtwagens3 = new Vrachtwagens("vrachrwagen C: ",containerschip,kade);
        Thread vrachtwagenThread3 = new Thread(vrachtwagens3);
        vrachtwagenThread3.setName(vrachtwagens3.getNaam());

        //Threads beginnen

        kraneThread.start();
        kraneThread2.start();
        vrachtwagenThread.start();
        vrachtwagenThread2.start();
        vrachtwagenThread3.start();


    }

  }

