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
public class MainClass {

    public static void main(String[] args) {

        Thread td = new Thread(new Webservice101());
        //while(true){
            //try{
            td.start();
            /*td.stop();
            Thread.sleep(10000);
            }catch(Exception e){
                System.out.println("Exception caught =>"+e.getMessage());
            }
        }*/

    }

}
