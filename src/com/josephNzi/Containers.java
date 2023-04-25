package com.josephNzi;


/**
 * Containers is een abstract class die gerbruikt zal worden om verschilende type container te maken.
 * @author Joseph Nzi
 * @version %I% %G%
 * @since 1.0
 */

public abstract class Containers {
    protected int volgnummer;
/**
 * @param volgnummer een uniek container nummer om het te identificieren.
*/
    public Containers(int volgnummer) {
        this.volgnummer = volgnummer;

    }
    public Containers(){

    }

    public int getVolgnummer() {
        return volgnummer;
    }

    public void setVolgnummer(int volgnummer) {
        this.volgnummer = volgnummer;
    }




}
