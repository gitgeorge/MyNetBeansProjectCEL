/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice101;

import java.util.ArrayList;

/**
 *
 * @author george
 */
public class Webservice101 implements Runnable {

    DatabaseConnect dbc;

    ArrayList<Temparature> list;

    Temparature tt;

    int temp, celc;

    public Webservice101() {

        dbc = new DatabaseConnect();

        list = dbc.temparature();
    }

    private static String celsiusToFahrenheit(java.lang.String celsius) {

        com.w3schools.webservices.TempConvert service = new com.w3schools.webservices.TempConvert();

        com.w3schools.webservices.TempConvertSoap port = service.getTempConvertSoap();

        return port.celsiusToFahrenheit(celsius);
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Starting");
                for (int i = 0; i <= list.size() - 1; i++) {
                    
                    tt = (Temparature) list.get(i);

                    temp = tt.getId();

                    celc = tt.getCeclcius();

                    System.out.println("fetched temp in celcius " + celc);

                    System.out.println("converted temp is " + celsiusToFahrenheit("" + celc));

                    String ups = celsiusToFahrenheit("" + celc);

                    int foo = Integer.parseInt(ups);

                    dbc.updateTemp(foo, i + 1);

                }
                
                if(){
                
                }
                
            } catch (Exception e) {
                System.out.println("Exception caught =>" + e.getMessage());
            }
        }
        
    }

}
