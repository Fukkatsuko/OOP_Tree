package Tree;

import java.util.ArrayList;

public class BinTree {
    private static class BinTreeElement {
        public Comparable node;
        public BinTreeElement left;
        public BinTreeElement right;
    }
    private BinTreeElement root;

    public boolean Empty(){
        return root==null;
    }

    public boolean add(Comparable o){
        while(true) {
            try {
                root = add(o, root);
                break;
            }catch(ArrayStoreException e){
                continue;
            }
        }
        return true;
    }
    private BinTreeElement add(Comparable o,BinTreeElement elem){
        if(elem==null){
            elem=new BinTreeElement();
            elem.node =o;
            return elem;
        }
        else {
            //номер элементов не должен совпадать
            while(o.CompareTo(elem.node) == 0) {
                o.nextNumber();
                //исключение используется для сброса стека (рекурсия) и сортировки элемента по его новому номеру с начала дерева
                throw new ArrayStoreException();
            }
            if (o.CompareTo(elem.node) > 0) {
                //добавить справа
                elem.right = add(o, elem.right);
                return elem;
            } else {
                //добавить слева
                elem.left = add(o, elem.left);
                return elem;
            }
        }
    }
    private void add(BinTreeElement elem,boolean b){
        if(elem.left!=null) add(elem.left,true);
        if(elem.right!=null) add(elem.right,true);
        if(b) add(elem.node);
    }

    public Comparable delete(int number)throws NullPointerException {
        if (root == null) throw new NullPointerException();
        //объект, данные которого будут предоставлены в отчете об удалении
        Comparable object;
        //рекурсивный метод работает с детьми заданной точки, следовательно будет лучше вынести раьоту с корнем а данный метод
        if (root.node.getNumber() == number) {
            object = root.node;
            if (root.left == null) {
                if (root.right == null) root = null;
                else root = root.right;
            }
            else {
                if (root.right == null) {
                    if (root.left == null) root = null;
                    else root = root.left;
                }
                else {
                    if (root.left != null && root.right != null) {
                        try {
                            BinTreeElement element=deleteMin(root.right);
                            root.node = element.node;
                            add(element,false);
                        }catch(NullPointerException e){
                            //это значит, что левой ветки у правого узла нет, и он сам минимальный
                            root.right.left=root.left;
                            root=root.right;
                        }
                    }
                }
            }
            return object;
        }
        object=delete(number, root);
        return object;
    }
    private BinTreeElement deleteMin(BinTreeElement elem){
        if(elem.left.left==null){
            BinTreeElement find=elem.left;
            elem.left=null;
            return find;
        }
        return deleteMin(elem.left);
    }
    private Comparable delete(int number, BinTreeElement elem) throws NullPointerException{
        //это возвращаемый объект
        Comparable result=null;

        //необходимо идти вправо
        if(elem.node.getNumber() < number) {
            //если правый потомок подходит
            if (elem.right.node.getNumber() == number) {
                //правый узел-искомый элемент
                result = elem.right.node;

                //если у узла-потомка нет дочерних узлов, его требуется удалить
                if (elem.right.left == null && elem.right.right == null) elem.right = null;
                else {
                    if(elem.right.left!=null && elem.right.right!=null){
                        try {
                            BinTreeElement newElement = deleteMin(elem.right.right);
                            elem.right.node =newElement.node;
                            add(newElement,false);
                        }catch(NullPointerException e){
                            //это значит, что в левой ветке правого узла элемента нет элементов и он сам минимальный
                            elem.right.right.left=elem.right.left;
                            elem.right=elem.right.right;
                        }
                    }
                    else {
                        if (elem.right.left == null) elem.right = elem.right.right;
                        else {
                            if (elem.right.right == null)
                                elem.right = elem.right.left;
                        }
                    }
                }
            }
            else{
                result=delete(number,elem.right);
            }
        }
        //необходимо идти влево
        else {
            if (elem.left.node.getNumber() == number) {
                //левый узел-искомый элемент
                result = elem.left.node;

                //если у узла-потомка нет дочерних узлов, его требуется удалить
                if (elem.left.left == null && elem.left.right == null) elem.left = null;
                else {
                    if (elem.left.left!=null && elem.left.right!=null){
                        try{
                            BinTreeElement newElement = deleteMin(elem.left.right);
                            elem.left.node =newElement.node;
                            add(newElement,false);

                        }catch(NullPointerException e){
                            //это значит, что в левой ветке правого узла элемента нет элементов и он сам минимальный
                            elem.left.right.left=elem.left.left;
                            elem.left=elem.left.right;
                        }
                    }
                    else{
                        if(elem.left.right==null) elem.left=elem.left.left;
                        else{
                            if(elem.left.left==null) elem.left=elem.left.right;
                        }
                    }
                }
            }
            else{
                result=delete(number,elem.left);
            }
        }
        return result;
    }

    public Object[] output(){
        if(root!=null)
            return output(root);
        return new String[]{"Бинарное дерево не содержит элементов"};
    }
    private Object[] output(BinTreeElement elem) {
        ArrayList<String> result = new ArrayList<>();
        if (elem.left != null) {
            Object[] temp = output(elem.left);
            for (int i = 0; i < temp.length; i++) {
                result.add("    "+ temp[i]);
            }
        }
        result.add(String.valueOf(elem.node.getNumber()));
        if (elem.right != null) {
            Object[] temp = output(elem.right);
            for (int i = 0; i < temp.length; i++) {
                result.add("    " + temp[i]);
            }
        }
        return result.toArray();
    }

    public Object[] toArray(){
        if(root==null) throw new NullPointerException();
        return toArray(root);
    }
    private Object[] toArray(BinTreeElement element){
        ArrayList<String> result = new ArrayList<>();
        if (element.left != null) {
            Object[] temp = toArray(element.left);
            for (int i = 0; i < temp.length; i++) {
                result.add((String) temp[i]);
            }
        }
        result.add(String.valueOf(element.node.getNumber()));
        if (element.right != null) {
            Object[] temp = toArray(element.right);
            for (int i = 0; i < temp.length; i++) {
                result.add((String) temp[i]);
            }
        }
        return result.toArray();
    }

    public boolean search(int value) {
        if (FindWithParent(value,root)!=null) return true;
        else return false;
    }
    private BinTreeElement FindWithParent(int value, BinTreeElement parent) {
        BinTreeElement current = parent;

        while (current != null)
        {
            int result = current.node.getNumber();

            if (result==value){
                return current;
            }
            else if (result > value)
            {
                current = current.left;
            }
            else if (result < value)
            {
                current = current.right;
            }
            else
            {
                return null;
            }
        }
        return null;
    }

    public int height(){
        return height(root);
    }
    public int height(BinTreeElement tree){
        if (tree==null){
            return 0;
        }
        else {
            return 1+ Math.max(height(tree.left),height(tree.right));
        }
    }

    public BinTreeElement balancing(){
        root=balancing(root);
        return root;
    }
    private BinTreeElement balancing(BinTreeElement tree){
        if (bfactor(tree)==2){
            if (bfactor(tree.right)<0){
                tree.right=rotateRight(tree.right);
            }
            return rotateLeft(tree);
        }
        if (bfactor(tree)==-2){
            if(bfactor(tree.left)>0){
                tree.left=rotateLeft(tree.left);
            }
            return rotateRight(tree);
        }
        return tree;
    }

    private BinTreeElement rotateRight(BinTreeElement tree){
        BinTreeElement temp=tree.left;
        tree.left=temp.right;
        temp.right=tree;
        return temp;
    }
    private  BinTreeElement rotateLeft(BinTreeElement tree){
        BinTreeElement temp=tree.right;
        tree.right=temp.left;
        temp.left=tree;
        return temp;
    }

    private int bfactor(BinTreeElement tree){
        return height(tree.right)-height(tree.left);
    }
}

