package Tree;


import java.io.IOException;
//import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner in;
    private  static  BinTree tree = new BinTree();

    public static void main(String[] args) {
        in = new Scanner(System.in);

        while (true) {
            System.out.println("________Меню________");
            System.out.println("output - Вывод массива элементов на экран");
            System.out.println("search - Поиск нужного элемента");
            System.out.println("insert - Добавить новый элемент");
            System.out.println("delete - Удалить элемент по номеру");
            System.out.println("height - Высота дерева");
            System.out.println("sorting - Сортировка массива");
            System.out.println("balancing - Балансирови дерева");
            System.out.println("Выберете команду");
            String code = "";
            code = in.nextLine();
            switch (code) {
                case "output":
                    Object[] string = tree.output();
                    for (Object s : string) {
                        System.out.println(s);
                    }
                    System.out.println("Нажмите Enter, чтобы продолжить...");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "search":
                    Object[] temp=tree.toArray();
                    if(temp.length==0){
                        System.out.println("Дерево не содержит элементов");
                        break;
                    }
                    int indEl;
                    while (true){
                        System.out.println("Введите индекс элемента");
                        try {
                            indEl=Integer.parseInt(in.nextLine());
                            if(indEl>0)break;
                        } catch (NumberFormatException e){
                            System.out.println("Некорректное число");
                        }
                    }

                    if (tree.search(indEl)){
                        System.out.println("Элемент существует");
                    }
                    else{
                        System.out.println("Элемента не существует");
                    }
                    System.out.println("Нажмите Enter, чтобы продолжить...");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "insert":
                    insert();
                    break;
                case "delete":
                    if(!tree.Empty()) delete();
                    else System.out.println("Дерево не содержит элементов");
                    break;
                case "height":
                    System.out.println(tree.height());
                    System.out.println("Нажмите Enter, чтобы продолжить...");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "sorting":
                    sorting();
                    System.out.println("Нажмите Enter, чтобы продолжить...");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "balancing":
                    balancing();
                    System.out.println("Нажмите Enter, чтобы продолжить...");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Команда введена неверно. Повторите попытку");
                    System.out.println("Нажмите Enter, чтобы продолжить...");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            System.out.println("");
        }
    }

    private static void insert(){
        int index;
        boolean m=true;

        System.out.println("Введите индекс числа: ");
        String temp=in.nextLine();
        while (m) {
            try {
                index=Integer.parseInt(temp);
                Node n=new Node(index);
                if (tree.search(index)){System.out.println("Элемент уже существует");}
                else {tree.add(n);}
                //break;
            } catch (NumberFormatException ex) {
                System.out.println("Некорректное число");
            }
            System.out.println(" ");
            System.out.println("Если хотите вернуться к основному меню, введите yes ");
            System.out.println("Если хотите продолжит ввод элементов, введите no ");
            String choice = "";
            choice = in.nextLine();
            switch (choice) {
                case "yes":
                    m=false;
                    break;
                case "no":
                    System.out.println("Введите индекс числа: ");
                    temp=in.nextLine();
                    break;
                default:
                    System.out.println("Команда введена неверно. Повторите попытку");
                    break;
            }
        }


    }

    private static void delete(){
        int n=0;
        boolean m=true;
        System.out.println("Введите индекс элемента: ");
        while (m){
            try{
                n=Integer.parseInt(in.nextLine());
                System.out.println("Удален элемент " + tree.delete(n));
                //break;
            } catch (NumberFormatException e){
                System.out.println("Некорректное число");
            } catch (NullPointerException e){
                System.out.println("Элемента с таким индексом не существует");
            }
            System.out.println(" ");
            System.out.println("Если хотите вернуться к основному меню, введите yes ");
            System.out.println("Если хотите продолжит выбор удаляемого элемента, введите no ");
            String choice = "";
            choice = in.nextLine();
            switch (choice) {
                case "yes":
                    m=false;
                    break;
                case "no":
                    System.out.println("Введите индекс элемента: ");
                    break;
                default:
                    System.out.println("Команда введена неверно. Повторите попытку");
                    break;
            }
            System.out.println("");
        }
    }

    private static void sorting(){
        System.out.println("Введите количество элементов в массиве:");
        int count;
        count=Integer.parseInt(in.nextLine());
        int[] mas=new int[count];
        System.out.println("Введите элементы: ");
        for(int i=0;i<count;i++)
            mas[i]=Integer.parseInt(in.next());
        BinTree masTree = new BinTree();
        for(int i=0;i<count;i++){
            masTree.add(new Node(mas[i]));
        }
        Object[] string = masTree.toArray();
        for (Object s : string) {
            System.out.println(s);
        }

    }

    private static void balancing(){
        tree.balancing();
        Object[] string = tree.output();
        for (Object s : string) {
            System.out.println(s);
        }
    }
}