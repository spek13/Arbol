package arbolgrafico;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
Implementa un arbol binario con caracteristicas de
conjunto, por lo tanto no puede tener elementos repetidos 
*/

public class TreeSet<E extends Comparable<E>> extends AbstractSet<E> {
    
	
	// define el nodo del arbol
    private static class Node<E> {
        public Node<E> left;
        public Node<E> right;
        public E info;
	
		// constructor del nodo
        public Node(E info) {
            this.info = info;
            this.left = null;
            this.right = null;
        }
    }
    
	
	// atributos de la clase
	
    private Node<E> root;
    private int count;
    
	// constructor del arbol
    public TreeSet() {
        root = null;
        count = 0;
    }
    
	// implementa el metodo add, sobreescribe el de la clase abstracta
    @Override
    public boolean add(E e) {        
        if (root == null) {                      
            root = new Node<>(e);
            count++;
            return true;
        } else {
            Node<E> p = root;
            while (true) {
                if (e.compareTo(p.info) == 0) {
                    return false;
                }
                if (e.compareTo(p.info) < 0) {
                    if (p.left == null) {
                        p.left = new Node<>(e);
                        count++;
                        return true;
                    } else {
                        p = p.left;
                    }
                } else {
                    if (p.right == null) {
                        p.right = new Node<>(e);
                        count++;
                        return true;
                    } else {
                        p = p.right;
                    }
                }
            }
        }        
    }
    
	// implementa el metodo contains, sobreescribe el de la clase abstracta
    @Override
    public boolean contains(Object obj) {
        
        if (obj == null
                || (root != null && obj.getClass() != root.info.getClass())) {
            return false;
        }

        @SuppressWarnings("unchecked")
        Comparable<E> cmp = (Comparable<E>) obj;
        Node<E> p = root;
        
        while (p != null) {            
            if (cmp.compareTo(p.info) == 0) {
                return true;
            }
            if (cmp.compareTo(p.info) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return false;
    }
    
	
	// recorrido inOrder, actualiza una lista con los nodos 
    private void inOrder(Node<E> root, List<E> lst) {
        if (root != null) {
            inOrder(root.left, lst);
            lst.add(root.info);
            inOrder(root.right, lst);
        }        
    }
    
	// recorrido inOrder, regresa una lista con los nodos
    public List<E> inOrderList() {
        List<E> lst = new ArrayList<>();
        inOrder(root, lst);
        return lst;
    }

	// recorrido preOrder, actualiza una lista con los nodos 
    private void preOrder(Node<E> root, List<E> lst) {
        if (root != null) {
            lst.add(root.info);
            preOrder(root.left, lst);            
            preOrder(root.right, lst);
        }        
    }
    
	// recorrido preOrder, regresa una lista con los nodos
    public List<E> preOrderList() {
        List<E> lst = new ArrayList<>();
        preOrder(root, lst);
        return lst;
    }
    
	// recorrido postOrder, actualiza una lista con los nodos 
    private void postOrder(Node<E> root, List<E> lst) {
        if (root != null) {            
            postOrder(root.left, lst);            
            postOrder(root.right, lst);
            lst.add(root.info);
        }        
    }
    
	// recorrido postOrder, regresa una lista con los nodos
    public List<E> postOrderList() {
        List<E> lst = new ArrayList<>();
        postOrder(root, lst);
        return lst;
    }
    
	// implementa el metodo iterator, sobreescribe el de la clase abstracta
    @Override
    public Iterator<E> iterator() {
        return inOrderList().iterator();
    }

	// implementa el metodo size, sobreescribe el de la clase abstracta	
    @Override
    public int size() {
        return count;
    }
    
	// implementa el metodo clear, sobreescribe el de la clase abstracta
    @Override
    public void clear() {
        root = null;
        count = 0;
    }
    Arbol miArbol = new Arbol();

    

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