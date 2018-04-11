/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolgrafico;

import javax.swing.UIManager;


public class Main {

   
    public static void main(String[] args) {
        // TODO code application logic here
        grafico();
        Gui gui = new Gui();
        gui.setVisible(true);
        
        
    }
    private static void grafico(){
        try {
           javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
