/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kike
 */
import java.util.Scanner;

class Nodo {
    int key, height;
    Nodo left, right;

    Nodo(int d) {
        key = d;
        height = 1;
    }
}

class ArbolAVL {
    Nodo root;

    int height(Nodo N) {
        if (N == null)
            return 0;
        return N.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    Nodo rightRotate(Nodo y) {
        Nodo x = y.left;
        Nodo T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Nodo leftRotate(Nodo x) {
        Nodo y = x.right;
        Nodo T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    int getBalance(Nodo N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    Nodo insert(Nodo nodo, int key) {
        if (nodo == null)
            return (new Nodo(key));

        if (key < nodo.key)
            nodo.left = insert(nodo.left, key);
        else if (key > nodo.key)
            nodo.right = insert(nodo.right, key);
        else
            return nodo;

        nodo.height = 1 + max(height(nodo.left), height(nodo.right));

        int balance = getBalance(nodo);

        if (balance > 1 && key < nodo.left.key)
            return rightRotate(nodo);

        if (balance < -1 && key > nodo.right.key)
            return leftRotate(nodo);

        if (balance > 1 && key > nodo.left.key) {
            nodo.left = leftRotate(nodo.left);
            return rightRotate(nodo);
        }

        if (balance < -1 && key < nodo.right.key) {
            nodo.right = rightRotate(nodo.right);
            return leftRotate(nodo);
        }

        return nodo;
    }

    Nodo balance(Nodo nodo) {
        if (nodo == null)
            return null;
        nodo.left = balance(nodo.left);
        nodo.right = balance(nodo.right);

        int balance = getBalance(nodo);
        if (balance > 1) {
            if (getBalance(nodo.left) < 0) {
                nodo.left = leftRotate(nodo.left);
            }
            return rightRotate(nodo);
        } else if (balance < -1) {
            if (getBalance(nodo.right) > 0) {
                nodo.right = rightRotate(nodo.right);
            }
            return leftRotate(nodo);
        }
        return nodo;
    }

    void preOrder(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.key + " ");
            preOrder(nodo.left);
            preOrder(nodo.right);
        }
    }

    void inOrder(Nodo nodo) {
        if (nodo != null) {
            inOrder(nodo.left);
            System.out.print(nodo.key + " ");
            inOrder(nodo.right);
        }
    }

    void postOrder(Nodo nodo) {
        if (nodo != null) {
            postOrder(nodo.left);
            postOrder(nodo.right);
            System.out.print(nodo.key + " ");
        }
    }
        void printLeaves(Nodo nodo) {
        if (nodo == null)
            return;
        if (nodo.left == null && nodo.right == null) {
            System.out.print(nodo.key + " ");
            return;
        }
        if (nodo.left != null) {
            printLeaves(nodo.left);
        }
        if (nodo.right != null) {
            printLeaves(nodo.right);
        }
    }

    public static void main(String[] args) {
        ArbolAVL tree = new ArbolAVL();
        Scanner sc = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n---MENU ARBOL AVL UMG---");
            System.out.println("1. Añadir Nodos al arbol");
            System.out.println("2. Verificar si el arbol esta balanceado");
            System.out.println("3. Balancear el arbol");
            System.out.println("4. Preorden");
            System.out.println("5. Postorden");
            System.out.println("6. Inorder");
            System.out.println("7. Mostrar Nodos Hojas del Arbol");
            System.out.println("0. Salir del programa");

            System.out.print("\nSelecciona una opcion: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Ingresa el numero de elementos que quieres agregar: ");
                    int n = sc.nextInt();
                    System.out.println("Ingresa los elementos:");
                    for (int i = 0; i < n; i++) {
                        int num = sc.nextInt();
                        tree.root = tree.insert(tree.root, num);
                    }
                    break;
                case 2:
                    int balance = tree.getBalance(tree.root);
                    if (balance > 1 || balance < -1) {
                        System.out.println("El arbol no esta balanceado.");
                    } else {
                        System.out.println("El arbol esta balanceado.");
                    }
                    break;
                case 3:
                    tree.root = tree.balance(tree.root);
                    System.out.println("Arbol balanceado.");
                    break;
                case 4:
                    System.out.print("Preorden arbol AVL: ");
                    tree.preOrder(tree.root);
                    break;
                case 5:
                    System.out.print("Postorden arbol AVL: ");
                    tree.postOrder(tree.root);
                    break;
                case 6:
                    System.out.print("Inorden arbol AVL: ");
                    tree.inOrder(tree.root);
                    break;
                case 7:
                    System.out.print("Nodos hoja del arbol: ");
                    tree.printLeaves(tree.root);
                    break;
                case 0:
                    System.out.println("Saliendo del programa....");
                    break;
                default:
                    System.out.println("Opcion Invalida.");
                    break;
            }
        } while (option != 0);
    }
}


        
