package Tree;

import java.util.*;

public class MTree {
    String name;
    MTree parent;
    List<MTree> children = new ArrayList<>();

    public MTree(String name, MTree parent){
        this.name = name;
        this.parent = parent;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public MTree getParent(){
        return parent;
    }

    public void setParent(MTree parent){
        this.parent = parent;
    }

    public List<MTree> getChildren(){
        return children;
    }

    public void addChild(MTree child){
        children.add(child);
    }


    public static void recursion(MTree root){
        Deque<MTree> deque = new ArrayDeque<>();
        MTree node = root;
        deque.push(node);

        while (!deque.isEmpty()){
            node = deque.pop();
            System.out.println(node.getName());
            List<MTree> children = node.getChildren();
            for(int i=children.size()-1; i>=0; i--){
                deque.push(children.get(i));
            }
        }
    }
}

