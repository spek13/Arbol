import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
}