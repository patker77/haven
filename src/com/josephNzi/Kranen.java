package com.josephNzi;

/**
 * Kranen is een een thread class die gebruik wordt om een container van een een schip te lossen
 * <ul>
 *     <li>Iedere kraan kan één container tegelijk uit het containerschip takelen</li>
 *     <li>Wanneer een kraan een container uit het schip getakeld heeft, zet deze de container op de kade neer,
 *     gesteld dat op de kade genoeg plek is om de container neer te zetten.</li>
 *     <li>Wanneer het schip leeg is, stoppen de kranen met werken.</li>
 *     <li>Indien er geen plek op de kade is om de container neer te zetten, zal de kraan moetenwachten tot er een plek vrijkomt.</li>
 *     <li>Om de ene kraan van de andere te kunnen onderscheiden, heeft iedere kraan een uniekenaam.</li>
 *     <li>Een kraan doet hier minimaal 1 minuut en maximaal 6 minuten over(het zit soms wel eens tegen met het vastmaken van de kabels).</li>
 *     <li>Een kraan maakt voortdurend duidelijk wat deze aan het doen is</li>
 * </ul>
 * @author Joseph Nzi
 * @version %I% %G%
 * @see #containerOphalen
 * @see #containerLossen
 * @since 1.0
 */

public class Kranen extends Mythread {
    
    /**
     * @param containerschip de containerschip met x containers die geloost worden.
     * @param naam de uniek naam van de kraan.
     * @param kade de kade waar de container gezet zal worden na het lossen.
    * @see Mythread#Mythread(String, Containerschip, Kade) 
    */

    public Kranen(String naam, Containerschip containerschip, Kade kade) {
        super(naam, containerschip, kade);
    }
/*  ===========================================================================================================
    *                                                                                                         *
    *                                           CONTAINER OPHALEN                                             *
    ===========================================================================================================
*/

    /**
     * Methode voor het ophalen van een container bij de schip
     * @return een container
     * @trows InterruptedException,IndexOutOfBoundsException
     * @see Containerschip#containerGeven
     * @see #containerLossen
     */
    public  Containers containerOphalen(){
            System.out.println(ColorsCoded.PRETTY_BLUE+Thread.currentThread().getName() + " wil container ophalen");
            container = containerschip.containerGeven();
            System.out.println(ColorsCoded.PRETTY_BLUE+Thread.currentThread().getName()+ " container "+container.getVolgnummer() + " overladen voltooid...");
        return container;
    }
/*  ===========================================================================================================
    *                                                                                                         *
    *                                           CONTAINER LOSSEN                                              *
    *                                                                                                         *
    ===========================================================================================================
*/
    /**
     * Methode voor het lossen van een container bij een de kade
     * @trows InterruptedException,IndexOutOfBoundsException
     * @see Kade#containerNeerzetten(Containers)
     * @see #containerOphalen
     */
    public  void containerLossen()throws InterruptedException,IndexOutOfBoundsException {
            System.out.println(ColorsCoded.PRETTY_BLUE+Thread.currentThread().getName() + " wil container "+ container.getVolgnummer()+ " plaatsen.");
            kade.containerNeerzetten(container);
    }

    //Roep de twee methoden aan zolang de schip niet leeg is.
    @Override
    public void run() {

        try {

            while (!containerschip.getContainers().isEmpty()) {
                containerOphalen();
                containerLossen();
                Thread.sleep(wachtlus());
            }
        }catch(Exception e){}
    }

}





