package LinkedList;

import java.util.*;

public class Template{
    // a templated Java singly linked list
    public class ListElement<T>{

        private ListElement<T> next;
        private T data;

        public ListElement(T value){
            data = value;
        }

        public ListElement<T> next(){
            return next;
        }

        public T value(){
            return data;
        }

        public void setNext(ListElement<T> elem){
            next = elem;
        }

        public void setValue(T value){
            data = value;
        }

        // track the head element
        public ListElement<Integer> insertInFront(ListElement<Integer> list, int data){
            ListElement<Integer> newHead = new ListElement<Integer>(data);
            newHead.setNext(list);
            return newHead;
        }

        public ListElement<Integer> find(ListElement<Integer> head, int data){
            ListElement<Integer> pointer = head;
            while(pointer != null && pointer.value() != data){
                pointer = pointer.next;
            }
            return pointer; // NULL or the data node
        }

        public ListElement<Integer> deleteNode(ListElement<Integer> list, int data){
            // store head node
            ListElement<Integer> pointer = list;
            ListElement<Integer> prev = null;

            // if head node holds the data to be deleted
            if (pointer != null && pointer.value() == data){
                list = pointer.next;
                return list;
            }

            // search for the data to be deleted, keep track of the previous node as we
            // need to change pointer.next
            while (pointer != null && pointer.value() != data){
                prev = pointer;
                pointer = pointer.next;
            }

            // if the key was not present in linked list
            if (pointer == null) {
                return list;
            }

            // unlink the node from linked list
            prev.next = pointer.next;
            return list;
            
        }
    }


}
