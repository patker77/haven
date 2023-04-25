package com.josephNzi;

import java.util.ArrayList;

/**
 * Kade is class die gebruikt word om een container tijdelijk te lossen voor het verdere vervoer met de vrachtwagen
 * <ul>
 *     <li>Op de kade is plek voor maximaal 5 containers.</li>
 *     <li>De volgorde, waarin containers op de kade worden geplaatst doet er niet toe. Als er plek is,kan een kraan een container neerzetten.</li>
 *     <li>De volgorde, waarin containers door de vrachtwagens worden opgehaald doet er niet toe.Als er een container op de kade staat, kan een vrachtwagen deze container wegrijden.</li>
 *     <li>De kade maakt voortdurend duidelijk hoeveel containers er op de kade staan.</li>
 * </ul>
 * @author Joseph Nzi
 * @version %I% %G%
 * @see #isGenoegPlek
 * @see #containerGeven
 * @see #containerNeerzetten(Containers)
 * @since 1.0
 */


public class Kade {


    private ArrayList <Containers> containers = new ArrayList<>(5);

    public ArrayList<Containers> getContainers() {
        return containers;
    }
    private int aantalContainerOntvangen;
    public void setContainers(ArrayList<Containers> containers) {
        this.containers = containers;
    }

 /* ===========================================================================================================
    *                                                                                                         *
    *                                           CHECK VOOR VOLDOENDE PLEK                                     *
    ===========================================================================================================
*/

    /**
     * Methode om te check als er voldoende plek is op de kade.
     * @return True als er voldoende plek is.
     * @see #containerNeerzetten(Containers)
     * @see #containerGeven
     */
    public synchronized Boolean isGenoegPlek(){
        if (this.containers.isEmpty() || this.containers.size() < 5){
            return true;
        }else {
            System.out.println(ColorsCoded.PRETTY_GREEN+"Kade:  container kan niet geplaats worden, de kade zit vol");
        }
        return false;
    }
 /* ===========================================================================================================
    *                                                                                                         *
    *                                           CONTAINER NEERZETTEN                                          *
    ===========================================================================================================
*/

    /**
     * Methode om een container op de kade te zetten.
     * @param container de container die neergezet wordt.
     * @return een container.
     * @exception IndexOutOfBoundsException,InterruptedException
     * @see #containerGeven
     * @see #isGenoegPlek
     */
    public synchronized ArrayList<Containers> containerNeerzetten(Containers container){

                try {
                    while (!isGenoegPlek()) {
                        wait();
                    }
                    this.containers.add(container);
                    System.out.println(ColorsCoded.PRETTY_GREEN+"kade:  container "+ container.getVolgnummer()+" ontvangen op plaast "+this.containers.indexOf(container));
                    System.out.println(ColorsCoded.PRETTY_GREEN+"kade:  er zijn "+this.containers.size() + " containers op de kade");
                    aantalContainerOntvangen++;
                    notifyAll();
                }catch (Exception e){}

        return this.containers;

    }

 /* ===========================================================================================================
    *                                                                                                         *
    *                                           CONTAINER GEVEN                                               *
    ===========================================================================================================
*/

    /**
     * Methode om een container aan een vrachtwagen te geven.
     * @return een container.
     * @exception IndexOutOfBoundsException,InterruptedException
     * @see #containerNeerzetten(Containers)
     * @see #isGenoegPlek
     */

    public synchronized Containers containerGeven(){

        Containers containers1 = null;
        try{
            while (this.containers.isEmpty()&&aantalContainerOntvangen<100){
                wait();
            }
            containers1 = this.containers.get(0);
            if (containers1.getClass().equals(GekoeldeContainers.class)){
                System.out.println(ColorsCoded.PRETTY_YELLOW+Thread.currentThread().getName()+"  koel element van container "+ containers1.getVolgnummer()+" wordt gekoppeld");
            }else if(containers1.getClass().equals(VerwarmdeContainers.class)) {
                System.out.println(ColorsCoded.PRETTY_YELLOW + Thread.currentThread().getName() + "  verwarmde element van container " + containers1.getVolgnummer() + " wordt gekoppeld");
            }else {
                System.out.println(ColorsCoded.PRETTY_YELLOW + Thread.currentThread().getName() + "  standaar container " + containers1.getVolgnummer() + " wordt geladen");
            }
            System.out.println(ColorsCoded.PRETTY_GREEN+"kade:  container "+ containers1.getVolgnummer() + " is gegeven");
            this.containers.remove(containers1);
            notifyAll();


        }catch (Exception e){}
        return containers1;
    }
    }


