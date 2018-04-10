
package arbolgrafico;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ArbolBinario {

   Arbol miArbol = new Arbol();

    public ArbolBinario() {
    }

    public boolean insertar(String dato) {
        return (this.miArbol.agregar(dato));
    }
    
    public boolean respuesta(String r){
        return (this.miArbol.respuesta(r));
    }
    public void iniciar(){
        this.miArbol.iniciar();
    }
    
    public void Guardar(){
     javax.swing.JFileChooser jF1= new javax.swing.JFileChooser(); 
     FileNameExtensionFilter filter = new FileNameExtensionFilter(".dat","dat");
        jF1.setFileFilter(filter);
     String ruta = ""; 
        try
      {
         if(jF1.showSaveDialog(null)==jF1.APPROVE_OPTION){ 
         ruta = jF1.getSelectedFile().getAbsolutePath(); 
         } 
         final FileOutputStream fo = new FileOutputStream(ruta);
         final ObjectOutputStream oos = new ObjectOutputStream(fo);
         oos.writeObject(this.miArbol);
         oos.flush();
         oos.close();
      }
      catch (Exception ex)
      {
         // write stack trace to standard error
         ex.printStackTrace();
      }
    
    }
    
    public void abrir() {
        javax.swing.JFileChooser jF1= new javax.swing.JFileChooser(); 
        String ruta = ""; 
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".dat","dat");
        jF1.setFileFilter(filter);
        try{
            if(jF1.showOpenDialog(null)==jF1.APPROVE_OPTION){ 
                
             ruta = jF1.getSelectedFile().getAbsolutePath(); 
         		final FileInputStream fis = new FileInputStream(ruta);
         		final ObjectInputStream ois = new ObjectInputStream(fis);  
         		final Object deserializedObject = ois.readObject();
         		System.out.println("Tipo de objeto " + deserializedObject.getClass().getName());
         		if (deserializedObject instanceof Arbol){
            		this.miArbol = (Arbol) deserializedObject;
         		}
         		else
         		{
            		System.out.println("No se esperaba " + deserializedObject.getClass().getName());
         		}
         		ois.close();

        		
      		}
        }catch (Exception ex){
         	 	
      		}
        }
        
    

    

    public String preOrden() {
        ArrayList it = this.miArbol.preOrden();
        return (recorrido(it, "Recorrido PreOrden"));
    }

    public String inOrden() {
        ArrayList it = this.miArbol.inOrden();
        return (recorrido(it, "Recorrido InOrden"));
    }

    public String posOrden() {
        ArrayList it = this.miArbol.postOrden();
        return (recorrido(it, "Recorrido PosOrden"));
    }

    public String imprimirPorNiveles() {
        ArrayList it = this.miArbol.impNiveles();
        return (recorrido(it, "Imprimir Por niveles"));
    }

    public String darHojas() {
        ArrayList it = this.miArbol.getHojas();
        return (recorrido(it, "Hojas del Arbol"));
    }

   

    private String recorrido(ArrayList it, String msg) {
        int i = 0;
        String r = msg + "\n";
        while (i < it.size()) {
            r += "\t" + it.get(i).toString() + "\n";
            i++;
        }
        return (r);
    }
    
    public String CantidadNodos(){
        return this.miArbol.cantidadNodos();
    }
    
    public String CantidadHojas(){
        return this.miArbol.cantidadNodosHoja();
    }
    public String alturaArbol(){
        return this.miArbol.retornarAltura();
    }
   
    public String porNivel(){
       int r=this.miArbol.alturaArbol();
       String nivel = Integer.toString(r);
       // ArrayList it = this.miArbol.imprimirNivel();
       // return (recorrido(it, "Imprimir Por niveles en orden!!!"));
       return nivel;
    }
   
    public String ramaMayor() {
        ArrayList it = this.miArbol.ObtenerRamamayor();
        return (recorrido(it, "Rama(s) con mas valores"));
    }
    
    public void limpiar(){
        miArbol.Limpiar();
    }
   
    public boolean isFull(){
        return miArbol.isFull(miArbol.raiz);
    }
    
    public boolean perfecto(){
        return miArbol.isPerfect(miArbol.raiz);
    }
    public boolean degenerado(){
        return miArbol.isDegenerate(miArbol.raiz);
    }

    public JPanel getDibujo() {
        return this.miArbol.getdibujo();
    }
}
