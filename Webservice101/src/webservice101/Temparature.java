/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice101;

/**
 *
 * @author george
 */
public class Temparature {
    
    public int id;
    public int ceclcius;
    public float ferenheit;
    public String dateModiefied;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCeclcius() {
        return ceclcius;
    }

    public void setCeclcius(int ceclcius) {
        this.ceclcius = ceclcius;
    }

    public float getFerenheit() {
        return ferenheit;
    }

    public void setFerenheit(float ferenheit) {
        this.ferenheit = ferenheit;
    }

    public String getDateModiefied() {
        return dateModiefied;
    }

    public void setDateModiefied(String dateModiefied) {
        this.dateModiefied = dateModiefied;
    }
    
}
