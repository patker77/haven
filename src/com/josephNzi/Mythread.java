package com.josephNzi;

import java.util.Random;

/**
 * Mythread is een abstract Thread class die gerbruikt zal worden om handeling verrichten bij de kranen en vrachtwagen.
 * <ul>
 *     <li>Het ophalen van een container bij de schip</li>
 *     <li>Hoelang het ophalen en wegbrengen van de container zal nemen</li>
 * </ul>
 * @author Joseph Nzi
 * @version %I% %G%
 * @see Kranen
 * @see Vrachtwagens
 * @see #wachtlus
 * @since 1.0
 */

public abstract class Mythread extends Thread{
    protected String naam;
    protected Containerschip containerschip;
    protected Kade kade;
    protected Containers container = null;


    /**
     * @param containerschip de containerschip met x containers die geloost worden.
     * @param naam de uniek naam van de Thread.
     * @param kade de kade waar de container zal opgehaald/gezet worden.
     */

    public Mythread(String naam, Containerschip containerschip,Kade kade) {
        this.naam = naam;
        this.kade = kade;
        this.containerschip = containerschip;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    /**
     * Methode voor het ophalen van een container
     * @trows InterruptedException,IndexOutOfBoundsException
     * @see Kranen#containerOphalen
     * @see Vrachtwagens#containerOphalen
     */
    public abstract Containers containerOphalen()throws InterruptedException,IndexOutOfBoundsException ;

    //Methode om de thread te runnen
    public abstract void run();

    /**
     * Methode om de wachtijd normaal te verdelen.
     * @return een nummer tussen 1000 een 6000
     */

    public int wachtlus() {
        int wachtijd;
        int min = 1000;
        int max = 6000;
        Random random = new Random();
        double val = random.nextGaussian() * 1000 + 5000;
        wachtijd = (int) Math.round(val);
        if (wachtijd<min){
            wachtijd= min;
        }else if (wachtijd>max){
            wachtijd = max;
        }
        return wachtijd;
    }
}
