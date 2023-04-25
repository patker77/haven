package com.josephNzi;


/**
 * Vrachtwagen is een een thread class die gebruik wordt om een container vanuit de kade verder te vervoeren.
 * <ul>
 *     <li>Als er een container op de kade staat, kan deze door een vrachtwagen worden opgehaalden weggereden.</li>
 *     <li>De vrachtwagen  kijken van welke type de container op de kade is, en specifiekehandelingen verrichten voor speciale type containers:
 *     <ul>
 *         <li>Voor standaard types alleen een melding afdrukken op het scherm dat de containeris geladen op de vrachtwagen</li>
 *         <li>Voor verwarmde types moet de container gekoppeld worden aan hetverwarmingselement en hiervan  een melding afdrukken op het scherm dat decontainer is gekoppeld aan het verwarmingselement</li>
 *         <li>Voor gekoelde types moet de container gekoppeld worden aan het koelingselementen hiervan  een melding afdrukken op het scherm dat de container is gekoppeld aanhet koelingselement (die anders is dan de melding voor de verwarmde container)</li>
 *     </ul>
 *     </li>
 *     <li>Wanneer er op de kade geen containers te vinden zijn, wacht een vrachtwagen tot er eenbeschikbaar is.</li>
 *     <li>Een vrachtwagen doet er minstens 1 minuut over om een container weg te rijden</li>
 *     <li>Iedere vrachtwagen maakt voortdurend duidelijk wat deze aan het doen is</li>
 * </ul>
 * @author Joseph Nzi
 * @version %I% %G%
 * @see #containerOphalen
 * @since 1.0
 */

public class Vrachtwagens extends Mythread {

    /**
     * @param containerschip de containerschip met x containers die geloost worden.
     * @param naam de uniek naam van de vrachtwagen.
     * @param kade de kade waar de container zal opgehaald worden.
     * @see Mythread#Mythread(String, Containerschip, Kade)
     */

    public Vrachtwagens(String naam, Containerschip containerschip, Kade kade) {
        super(naam, containerschip, kade);
    }

/*  ===========================================================================================================
    *                                                                                                         *
    *                                           CONTAINER OPHALEN                                             *
    ===========================================================================================================
*/
    /**
     * Methoden om een container bij de kade te laden
     * @trows InterruptedException,IndexOutOfBoundsException
     * @see Kade#containerGeven
     */


    public  Containers containerOphalen() {
            System.out.println(ColorsCoded.PRETTY_YELLOW+Thread.currentThread().getName() + "  wil container ophalen");
            container = kade.containerGeven();
            System.out.println(ColorsCoded.PRETTY_YELLOW+Thread.currentThread().getName() + "  brengt container  " + container.getVolgnummer() + " weg ");
        return container;
    }


    //Roep de methode aan zolang de schip niet leeg is.

    @Override
    public void run() {

        try {
                while (!containerschip.getContainers().isEmpty()) {
                  containerOphalen();
                     Thread.sleep(wachtlus());
                }
        } catch (Exception e) {
        }
    }
}
