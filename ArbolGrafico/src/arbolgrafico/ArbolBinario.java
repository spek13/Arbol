package arbolgrafico;
import java.util.ArrayList;
import javax.swing.JPanel;


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
       
       return nivel;
    }
   
    public String ramaMayor() {
        ArrayList it = this.miArbol.ObtenerRamamayor();
        return (recorrido(it, "Rama(s) con mas valores"));
    }
    
    public JPanel getDibujo() {
        return this.miArbol.getdibujo();
    }
}
