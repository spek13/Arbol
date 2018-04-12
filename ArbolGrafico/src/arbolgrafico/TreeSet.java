package arbolgrafico;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
Implementa un arbol binario con caracteristicas de
conjunto, por lo tanto no puede tener elementos repetidos 
*/

public class TreeSet<E extends Comparable<E>> extends AbstractSet<E> implements Serializable{
        final static long serialVersionUID = 1;
    Nodo raiz;
    int cant;
    int altura;
    String nova, piv;
   

    public Nodo getRaiz() {
        return this.raiz;
    }

    public void setRaiz(Nodo r) {
        this.raiz = r;
    }

    

    public boolean agregar(String dato) {
        Nodo nuevo = new Nodo(dato, null, null);
        insertar(nuevo, raiz);
        return true;
    }
  //metedo para insertar datos 
    public void insertar(Nodo nuevo, Nodo pivote) {
        nova= (String) nuevo.getDato();
        piv= (String) pivote.getDato();
        if (this.raiz == null) {
            raiz = nuevo;
        } else {
            
            if (nova.compareToIgnoreCase(piv)<0) {
                if (pivote.getIzq() == null) {
                    pivote.setIzq(nuevo);
                } else {
                    insertar(nuevo, pivote.getIzq());
                }
            } else {
                if (pivote.getDer() == null) {
                    pivote.setDer(nuevo);
                } else {
                    insertar(nuevo, pivote.getDer());
                }
            }
        }
    }
    
    public void iniciar(){
        nodoNuevo ();
        }
   
    
    public boolean respuesta (String r) {
    String resp = r;
      while (true) {
	  if (resp.equals("si")) return true;
	  if (resp.equals("no")) return false;
          if (resp.equalsIgnoreCase("d")) return true;
           resp=JOptionPane.showInputDialog("La respuesta debe ser si, no"); 
	  //System.out.println("La respuesta debe ser si, no, no se");
      }
  }
    //metodo para ingesar nuevo elemento al arbol
    private void nodoNuevo () {
        String nuevoN = JOptionPane.showInputDialog("introduce nombre");
        Nodo nuevo = new Nodo(nuevoN,null,null);
        if(raiz==null){
            raiz = new Nodo(nuevoN,null,null);
        }else insertar(nuevo,raiz);
    }
     
        
    //cantidad de nodos del arbol
    public String cantidadNodos() {
        cant = 0;
        cantidadNodos(raiz);
        return ""+cant;
    }

    private void cantidadNodos(Nodo reco) {
        if (reco != null) {
            cant++;
            cantidadNodos(reco.getIzq());
            cantidadNodos(reco.getDer());
        }
    }
    
    //cantidad nodos hoja
    
    public String cantidadNodosHoja() {
        cant = 0;
        cantidadNodosHoja(raiz);
        return ""+cant;
    }
    //metodo para calcular la cantidad de hojas
      private void cantidadNodosHoja(Nodo reco) {
        if (reco != null) {
            if (reco.getIzq() == null && reco.getDer() == null) {
                cant++;
            }
            cantidadNodosHoja(reco.getIzq());
            cantidadNodosHoja(reco.getDer());
        }
    }

     //altura del arbol
     public String retornarAltura() {
        retornarAltura(raiz, 0);
        return ""+altura;
    }

    private void retornarAltura(Nodo reco, int nivel) {
        if(reco==null){
            altura=-1;
        }else {
            alturaArbol(reco.getIzq(), nivel + 1);
            if (nivel > altura) {
                altura = nivel;
            }
            alturaArbol(reco.getDer(), nivel + 1);
        }
    }
    
    //imprimir ordenado con niveles
 
    String[] niveles;

    public int alturaArbol() {
        altura = 0;
        alturaArbol(raiz, 0);
        return altura;
    }

    private void alturaArbol(Nodo pivote, int nivel) {
        if (pivote != null) {
            alturaArbol(pivote.getIzq(), nivel + 1);
            if (nivel > altura) {
                altura = nivel;
            }
            alturaArbol(pivote.getDer(), nivel + 1);
        }
    }

    public ArrayList imprimirNivel() {
        niveles = new String[altura + 1];
        ArrayList l=new ArrayList();
        imprimirNivel(raiz, 0);
        for (int i = 0; i < niveles.length; i++) {
            l.add(niveles[i] + " ");
            //System.out.println(niveles[i] + " ");
        }
        return l;
    }
      public void imprimirNivel(Nodo pivote, int nivel2) {
        if (pivote != null) {
            niveles[nivel2] = pivote.getDato() + ", " + ((niveles[nivel2] != null) ? niveles[nivel2] : "");
            imprimirNivel(pivote.getDer(), nivel2 + 1);
            imprimirNivel(pivote.getIzq(), nivel2 + 1);
        }
    }
      
      
    //Obtener el numero de ramas
    int numeroRamas = 0;
    public ArrayList<String>ObtenerRamamayor(){
        obtenernumeroRamas(this.raiz, 0);
        return ObtenerRamamayor(this.raiz, 0, "", new ArrayList<String>());
    }
    public void obtenernumeroRamas(Nodo pivote, int contador) {
        if (pivote != null) {
            contador++;
            obtenernumeroRamas(pivote.getIzq(), contador);
            obtenernumeroRamas(pivote.getDer(), contador);
        }
        if (contador > this.numeroRamas) {
            this.numeroRamas = contador;
        }
    }

     public ArrayList<String> ObtenerRamamayor(Nodo pivote, int contador, String dato, ArrayList lista){
        if (pivote != null ) {
            dato+=pivote.getDato()+",";
            contador ++;
            lista=ObtenerRamamayor(pivote.getIzq(), contador, dato, lista);
            lista=ObtenerRamamayor(pivote.getDer(), contador, dato, lista);
            
            if (contador == this.numeroRamas) {
                lista.add(dato);
            }
        }
        return lista;
    }
    
    
       //imprimir preorden
    public ArrayList preOrden() {
        ArrayList l=new ArrayList();
        preOrden(raiz,l);
        return l;
    }

    private void preOrden(Nodo reco, ArrayList l) {
        if (reco != null) {
            l.add(reco.getDato() + " ");
            preOrden(reco.getIzq(),l);
            preOrden(reco.getDer(),l);
        }
    }
    //imprimir InOrden
    public ArrayList inOrden() {
        ArrayList l=new ArrayList();
        inOrden(raiz,l);
        return l;
    }

    private void inOrden(Nodo reco,ArrayList l) {
        if (reco != null) {
            inOrden(reco.getIzq(),l);
            l.add(reco.getDato() + " ");
            inOrden(reco.getDer(),l);
        }
    }

//imprimir post orden
    public ArrayList postOrden() {
        ArrayList l=new ArrayList();
        postOrden(raiz,l);
        return l;
    }

    private void postOrden(Nodo reco, ArrayList l) {
        if (reco != null) {
            postOrden(reco.getIzq(),l);
            postOrden(reco.getDer(),l);
            l.add(reco.getDato() + " ");
        }
    }
    
    //con nivel
       public ArrayList impNiveles() {
        ArrayList l=new ArrayList();
        impNiveles(raiz, 1,l);
        return l;
    }

    private void impNiveles(Nodo reco, int nivel,ArrayList l) {
        if (reco != null) {
            impNiveles(reco.getIzq(), nivel + 1, l);
            l.add(reco.getDato() + " Nivel: (" + nivel + ") ");
            impNiveles(reco.getDer(), nivel + 1, l);
        }
    }
    
    //hojas
    public ArrayList getHojas() {
        ArrayList l = new ArrayList();
        getHojas(this.raiz, l);
        return (l);
    }

    private void getHojas(Nodo r, ArrayList l) {
        if (r != null) {
            if (this.esHoja(r)) {
                l.add(r.getDato());
            }
            getHojas(r.getIzq(), l);
            getHojas(r.getDer(), l);
        }

    }
    protected boolean esHoja(Nodo x) {
        return (x != null && x.getIzq() == null && x.getDer() == null);
    }
     //metodo para limpiar el arbol
    public void Limpiar(){
        raiz=null;
    }
    
    //metodo para saber si el arbol esta lleno 
    public boolean isFull(Nodo node){
        if(node == null)
        return true;
        if(node.izq == null && node.der == null )
            return true;
        if((node.izq!=null) && (node.der!=null))
            return (isFull(node.izq) && isFull(node.der));
        return false;
    }
    
    public int profundidad(Nodo node){
        int d=0;
        while (node != null)
        d++;
        node = node.izq;
        return d;
    }
    
    //metodo para saber si el arbol es perfecto 
    public boolean isPerfectRec(Nodo root,int d, int level){

        if(root==null) return true;
        
        if(root.izq==null && root.der==null){
            return (d==level+1);
        }
        
        if(root.izq==null || root.der==null){
            return false;
        }
        return isPerfectRec(root.izq, d, level+1) &&
           isPerfectRec(root.der, d, level+1);
    }
    
    public boolean isPerfect(Nodo root){
        int d = profundidad(root);
        return isPerfectRec(root, d,0);
    }
   //metodo para saber si el arbol es degenerado 
   public boolean isDegenerate(Nodo root){
       if (root.izq != null) {
        if (root.der != null) {
            return false; // not degenerate, has two children
        } else {
            return isDegenerate(root.izq);
        }
    } else {
        if (root.der != null) {
            return isDegenerate(root.der);
        } else {
            return true; // we arrived at the bottom without seeing any node with two children
        }
    }
   }
    
    //draw arbol
     public JPanel getdibujo() {
         
         
        return new ArbolExpresionGrafico(this);
    }
	
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
    


    

    public boolean insertar(String dato) {
        return (this.agregar(dato));
    }
    
//    public boolean respuesta(String r){
//        return (this.miArbol.respuesta(r));
//    }
//    public void iniciar(){
//        this.iniciar();
//    }
    
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
         oos.writeObject(this);
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
         		//if (deserializedObject instanceof){
            		//this = (this) deserializedObject;
         		//}
         		//else
         		//{
            		//System.out.println("No se esperaba " + deserializedObject.getClass().getName());
         		//}
         		ois.close();

        		
      		}
        }catch (Exception ex){
         	 	
      		}
        }
        
    

    

//    public String preOrden() {
//        ArrayList it = this.miArbol.preOrden();
//        return (recorrido(it, "Recorrido PreOrden"));
//    }
//
//    public String inOrden() {
//        ArrayList it = this.inOrden();
//        return (recorrido(it, "Recorrido InOrden"));
//    }

    public String posOrden() {
        ArrayList it = this.postOrden();
        return (recorrido(it, "Recorrido PosOrden"));
    }

    public String imprimirPorNiveles() {
        ArrayList it = this.impNiveles();
        return (recorrido(it, "Imprimir Por niveles"));
    }

    public String darHojas() {
        ArrayList it = this.getHojas();
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
        return this.cantidadNodos();
    }
    
    public String CantidadHojas(){
        return this.cantidadNodosHoja();
    }
//    public String alturaArbol(){
//        return this.retornarAltura();
//    }
   
    public String porNivel(){
       int r=this.alturaArbol();
       String nivel = Integer.toString(r);
       // ArrayList it = this.miArbol.imprimirNivel();
       // return (recorrido(it, "Imprimir Por niveles en orden!!!"));
       return nivel;
    }
   
    public String ramaMayor() {
        ArrayList it = this.ObtenerRamamayor();
        return (recorrido(it, "Rama(s) con mas valores"));
    }
    
    public void limpiar(){
        Limpiar();
    }
   
    public boolean isFull(){
        return this.isFull(raiz);
    }
    
    public boolean perfecto(){
        return this.isPerfect(raiz);
    }
    public boolean degenerado(){
        return this.isDegenerate(raiz);
    }

    public JPanel getDibujo() {
        return this.getdibujo();
    }

}