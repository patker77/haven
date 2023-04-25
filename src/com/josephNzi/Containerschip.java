package com.josephNzi;

import java.util.ArrayList;

/**
 * Containerschip is een schip class die gebruikt wordt om 100 container te vervoert naar een haven. Daar wordt ie met behulp van twee kranen geloost
 * <ul>
 *     <li>Om bij te kunnen houden welke container door welke kraan en welke vrachtwagen wordtbehandeld, heeft iedere container een uniek volgnummer.</li>
 *     <li>Er zijn 3 types containers (random gedistribueerd op de containerschepen): een standaardtype, een gekoelde container en een verwarmde container. De manier waarop de containersvan het schip afgehaald worden verschilt per type container:
 *     <ul>
 *             <li>de standaard type heeft geen bijzondere handelingen bij het uitladen.</li>
 *             <li>de verwarmde container moet eerst losgekoppeld uit de verwarmingselementen.</li>
 *             <li>de gekoelde  container moet eerst losgekoppeld uit de koelingselementen.</li>
 *         </li>
 *     </ul>
 *     </li>
 * </ul>
 * @author Joseph Nzi
 * @version %I% %G%
 * @see #containersAanmaken
 * @see #containerGeven
 * @since 1.0
 */

public class Containerschip {

    private ArrayList <Containers> containers = new ArrayList<>();

    public ArrayList<Containers> getContainers() {
        return containers;
    }

    public void setContainers(ArrayList<Containers> containers) {
        this.containers = containers;
    }

/*  ===========================================================================================================
    *                                                                                                         *
    *                                          CONTAINER AANMAKEN                                             *
    ===========================================================================================================
*/

/**
* Het aanmaken van een type container met de type Gekoeld, verwarmt en standaart
* op een wilekeurig wijst tot een totale van 100 containers
 * @see #containerGeven
 */
    public void containersAanmaken(){
        ArrayList<Containers>c = new ArrayList<Containers>();
        int max = 3;
        int min =1;
        int rand;

        for (int i = 1; i <=100; i++) {
             rand = (int)(Math.random()*(max-min + 1)+min);
            if (rand == 1) {
                Containers containers = new GekoeldeContainers();
                containers.setVolgnummer(i);
                c.add(containers);
            }else if (rand==2){
                Containers containers = new VerwarmdeContainers();
                containers.setVolgnummer(i);
                c.add(containers);
            }else {
                Containers containers = new StandaarContainers();
                containers.setVolgnummer(i);
                c.add(containers);
            }
        }
        setContainers(c);
    };

 /*  ===========================================================================================================
    *                                                                                                         *
    *                                           CONTAINER GEVEN                                               *
    ===========================================================================================================
*/

    /**
    * Het overdragen van een Container aan en Kranen
    * @return een container
     * @see #containersAanmaken
    */

    public synchronized Containers containerGeven(){

        Containers containers1 = null;

        containers1 = this.containers.get(0);
        if (containers1.getClass().equals(GekoeldeContainers.class)){
            System.out.println(ColorsCoded.PRETTY_BLUE+Thread.currentThread().getName()+"  koel element van container "+ containers1.getVolgnummer()+" wordt losgekoppeld");
        }else if(containers1.getClass().equals(VerwarmdeContainers.class)) {
            System.out.println(ColorsCoded.PRETTY_BLUE + Thread.currentThread().getName() + "  verwarmde element van container " + containers1.getVolgnummer() + " wordt losgekoppeld");
        }else {
            System.out.println(ColorsCoded.PRETTY_BLUE + Thread.currentThread().getName() + "  standaar container " + containers1.getVolgnummer() + " wordt uitgelaad");
        }
        System.out.println(ColorsCoded.PRETTY_CYAN+"ship:  container "+ containers1.getVolgnummer() + " is gegeven");
        this.containers.remove(containers1);
        return containers1;
    };
}
