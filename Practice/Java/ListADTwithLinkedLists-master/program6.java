/*
Kyle Lehtinen - Program 6 - Using Linked Lists to get stuff done with a List ADT
*/

import java.util.*;

class Link{
    public Object data;
    public Link next;
}

class Inventory{
    private String _itemName;
    private int _quantity;
    private double _price;
    
    public Inventory(String itemName,int quantity,double price){
        _itemName = itemName;
        _quantity = quantity;
        _price = price;
    }
    
    public String getItem(){
        return _itemName;
    }
    
    public int getQuantity(){
        return _quantity;
    }
    
    public double getPrice(){
        return _price;
    }
    
    public boolean equals(Object other){
        return (other instanceof Inventory);
    }
    
    public String toString(){
        return "Item Name = "+_itemName+"\nQuantity = "+_quantity+"\nPrice = "
                +_price+"\n";
    }   
}

class List{
    private Link _head;
    private Link _tail;
    private int _count;
    
    public List(){
        _head = null;
        _tail = null;
        _count = 0;
    }
    
    public boolean isEmpty(){
        return (_head == null);
    }
    
    public void insertAtPos(Object item,int position){
        //immediate exit conditions
        if(position > (_count+1) || position < 1) return;
        
        //supporting variables
        int currCount = 1;
        Link curr = _head;
        Link prev = null;
        Link save = null;
        //pass given item to new link
        Link nLink = new Link();
        nLink.data = item;
        //add to front
        if (position == 1){
            nLink.next = _head;
            _head = nLink;
            _count++;
            if(_count == 1)
                _tail = _head;
        } //add to end
        else if (position == _count + 1){
            _tail.next = nLink;
            nLink.next = null;
            _tail = nLink;
            _count++;
        }//add to back
        else if (position == _count){
            for(int i = 0; i < position - 1; i++){
                prev = curr;
                curr = curr.next;
            }
            prev.next = nLink;
            nLink.next = curr;
            _tail = curr;
            _count++;
        }//add to all others between
        else{
            for(int i = 0; i < position-1; i++){
                prev = curr;
                curr = curr.next;
            }
            prev.next = nLink;
            nLink.next = curr;
            _count++;
        }

        
            /*
        //prechecks to confirm given position and scenario is valid
        if(position > (_count+1) || position < 1) return;
        
        Link curr = _head;
        Link prev = _head;
        Link save = null;
        int currCount = 1;
        //create new link for given data
        Link nLink = new Link();
        nLink.data = item;
        nLink.next = null;
        
        //logic for insert at given position
        if (position == (_count + 1) && _count != 0){
            while (currCount < _count){
                curr = curr.next;
                currCount++;
            }
            save = curr;
            curr.next = nLink;
            _tail = curr.next;
            _count++;
            
            /*_tail = nLink;
            _head = _tail;
            _count++;*
        } else if (position == 1 && _count == 0){
            _head = nLink;
            _tail = _head;
            _count++;
        } else {
            curr = _head;
            while (currCount < position){
                prev = curr;
                curr = curr.next;
                currCount++;
            }
            save = curr;
            prev.next = nLink;
            if (position == 1)
                _head = prev.next;
            prev = prev.next;
            prev.next = save;
            _count++;
        }  */
    }
    
    public void insertAtEnd(Object item){
        if (isEmpty()){
            Link nLink = new Link();
            nLink.data = item;
            nLink.next = null;
            //_tail = nLink;
            _head = nLink;
            _tail = _head;
            _count++;
        }
        else{
            Link nLink = new Link();
            nLink.data = item;
            nLink.next = null;
            _tail.next = nLink;
            _tail = _tail.next;
            _count++;
        }
    }
    /////could maybe be more efficient
    public void deleteRange(int rangeStart,int rangeEnd){
        //immediate exit conditions
        if(rangeStart > _count || rangeStart < 1) return;
        
        int currCount = 1;
        Link curr = _head;
        Link prev = _head;
        Link preStart = null;
        
        if (rangeStart == 1){
            if (rangeEnd == _count){
                _head = null;
                _tail = null;
                _count = 0;
            }
            else{
                while (currCount <= rangeEnd){
                    prev = curr;
                    curr = curr.next;
                    currCount++;
                    _count--;
                }
                _head = curr;
            }
        }
        else if (rangeStart == _count){
            _head = null;
            _tail = null;
            _count = 0;
        }
        else{
            while (currCount < rangeStart){
                prev = curr;
                curr = curr.next;
                currCount++;
            }
            preStart = prev;
            if (rangeEnd > _count){
                preStart.next = null;
                _tail = preStart;
                _count = _count - (_count - currCount);
                return;
            }
            for(int i = 0; i < (rangeEnd - rangeStart); i++){
                prev = curr;
                curr = curr.next;
            }
            preStart.next = curr.next;
            _count = _count - (rangeEnd - rangeStart);
        }
    }
   
    public void deleteItem(Object item){
        Link curr = _head;
        Link prev = _head;
        
        while(curr != null){
            if(curr == _head){
                if(curr.data.equals(item)){
                    _head = _head.next;
                    _count--;
                }
                prev = curr;
                curr = curr.next;
            }
            else{
                if(curr.data.equals(item)){
                    prev.next = curr.next;
                    if(curr == _tail)
                        _tail = prev;
                    _count--;
                    curr = curr.next;   
                }
                else{
                    prev = curr;
                    curr = curr.next;
                }
            }
        }
    }
    
    public Object retrieve(int position){
        if(position > _count) return null;
        
        Link curr = _head;
        Object result;
        int currCount = 1;
        
        while (currCount < position){
            curr = curr.next;
            currCount++;
        }
        result = curr.data;
        return result;
    }
    //needs to be List of int via wrapper class? Not sure what that means...
    public String find(Object item){
        int currCount = 1;
        String s = "";
        Link curr = _head;
        
        while (curr != null){
            if (curr.data.equals(item))
                s += (int) currCount + ", ";
            curr = curr.next;
            currCount++;
        }
        return s;
    }
    
    public int getSize(){
        return _count;
    }
    
    public String toString(){
        String s = "";
        s += "\nList Contents\n------LIST TOP------\n";
        for (Link sp = _head; sp != null; sp = sp.next){
            s += sp.data + "\n";
        }
        s += "-----LIST BOTTOM-----";
        return s;
    }
}

public class program6 {
    
    public static Scanner keyb = new Scanner(System.in);
    
    public static void main(String[] args) {
        /*//For Testing
        List test = new List();
        test.insertAtPos("OneAtOne", 1);
        System.out.print(test+"\n");
        test.insertAtPos("TwoAtTwo",2);
        System.out.print(test+"\n");
        test.insertAtEnd("ThreeAtEnd");
        System.out.print(test+"\n");
        //test.insertAtEnd("FourAtEnd");
        //System.out.print(test+"\n");
        test.insertAtPos("InjectAtThree", 3);
        //System.out.print(test.getSize()+"\n");
        System.out.print(test);
        */
        int listSelect = 0;
        int position, rangeStart, rangeEnd, opSelect;
        double totalValue = 0;
        Inventory tempA, tempB;
        String sFind;
        Object nItem;
        List sList = new List();
        List invListA = new List();
        List invListB = new List();
        
        System.out.print("List Creator and Inventory Manager\n");
        System.out.print("\nLists created.");
        
        do{
            System.out.print("\nPlease select which list you would like to work "
                + "with:\n1. String List\n2. Inventory List A\n3. Inventory "
                + "List B\n4. Quit Program\nSelection: ");
            listSelect = keyb.nextInt();
            while(listSelect < 1 || listSelect > 4){
                System.out.print("\nSorry, that is an invalid selection. Please "
                        + "make a selection from the choices provided above: ");
                listSelect = keyb.nextInt();
            } 
            
            if(listSelect != 4){
                do{
                    displayOptions();
                    opSelect = keyb.nextInt();
                    while (opSelect < 1 || opSelect > 10){
                        System.out.print("Sorry, that is an invalid selection. "
                                + "Please choose from one of the options above: ");
                        opSelect = keyb.nextInt();
                    }

                    if(opSelect == 1){
                        System.out.print("\nPlease enter the position in the "
                                + "list where you would like to add the entry: ");
                        position = keyb.nextInt();

                        if (listSelect == 1){
                            nItem = (String) getItem(listSelect);
                            sList.insertAtPos(nItem, position);
                        }
                        else{
                            nItem = (Inventory) getItem(listSelect);
                            if(listSelect == 2)
                                invListA.insertAtPos(nItem,position);
                            else
                                invListB.insertAtPos(nItem, position);
                        }
                    } 
                    else if(opSelect == 2){
                        if(listSelect == 1){
                            nItem = (String) getItem(listSelect);
                            sList.insertAtEnd(nItem);
                        }
                        else if (listSelect == 2){
                            nItem = (Inventory) getItem(listSelect);
                            invListA.insertAtEnd(nItem);
                        }
                        else{
                            nItem = (Inventory) getItem(listSelect);
                            invListB.insertAtEnd(nItem);
                        }
                    }
                    else if (opSelect == 3){
                        System.out.print("\nPlease enter the start range in the"
                                + " list to delete: ");
                        rangeStart = keyb.nextInt();
                        System.out.print("\nPlease enter the end range in the "
                                + "list "
                                + "to delete: ");
                        rangeEnd = keyb.nextInt();

                        if(listSelect == 1){
                            sList.deleteRange(rangeStart, rangeEnd);
                            System.out.print("\nOperation Complete!\n");
                        }
                        else if(listSelect == 2){
                            invListA.deleteRange(rangeStart, rangeEnd);
                            System.out.print("\nOperation Complete!\n");
                        }
                        else{
                            invListB.deleteRange(rangeStart, rangeEnd);
                            System.out.print("\nOperation Complete!\n");
                        }
                    }
                    else if (opSelect == 4){
                        if (listSelect == 1){
                            nItem = (String) getItem(listSelect);
                            sList.deleteItem(nItem);
                        }
                        else if (listSelect == 2){
                            nItem = getItem(listSelect);
                            invListA.deleteItem(nItem);
                        }
                        else{
                            nItem = getItem(listSelect);
                            invListB.deleteItem(nItem);
                        }
                    }
                    else if (opSelect == 5){
                        System.out.print("\nPlease enter the position in the "
                                + "list "
                                + "you would like to retrieve: ");
                        position = keyb.nextInt();
                        if (listSelect == 1){
                            sList.retrieve(position);
                        }
                        else if (listSelect == 2){
                            System.out.print(invListA.retrieve(position));
                        }
                        else{
                            System.out.print(invListB.retrieve(position));;
                        }
                    }
                    else if (opSelect == 6){
                        System.out.print("Please enter the item to be searched "
                                + "for: ");
                        sFind = keyb.next();
                        if (listSelect == 1){
                            sList.find(sFind);
                        }
                        else if (listSelect == 2){
                            invListA.find(sFind);
                        }
                        else{
                            invListB.find(sFind);
                        }
                    }
                    else if (opSelect == 7){
                       if (listSelect == 1){
                            sList.getSize();
                        }
                        else if (listSelect == 2){
                            invListA.getSize();
                        }
                        else{
                            invListB.getSize();
                        } 
                    }
                    else if (opSelect == 8){
                        if (listSelect == 1){
                            System.out.print(sList);
                        }
                        else if (listSelect == 2){
                            System.out.print(invListA);
                        }
                        else{
                            System.out.print(invListB);
                        } 
                    }
                    else if (opSelect == 9){
                        if (listSelect == 1){
                            System.out.print("\nSorry, this option is only "
                                    + "available for inventory lists.\n");
                        }
                        else if (listSelect == 2){
                            if(invListA.isEmpty()){
                                System.out.print("\nThere are no items in the "
                                        + "list.\n");
                            } else {
                            for (int i = 0; i < invListA.getSize();i++){
                                tempA = (Inventory) invListA.retrieve(i);
                                totalValue = totalValue + 
                                        (tempA.getQuantity() * tempA.getPrice());
                            }
                            System.out.print("\nThe total value of Inventory List"
                                    + " A is $"+totalValue+".\n");
                            }
                        }
                        else{
                            if(invListB.isEmpty()){
                                System.out.print("\nThere are no items in the "
                                        + "list.\n");
                            } else {
                            for (int i = 0; i < invListA.getSize();i++){
                                tempB = (Inventory) invListB.retrieve(i);
                                totalValue = totalValue + 
                                        (tempB.getQuantity() * tempB.getPrice());
                            }
                            System.out.print("\nThe total value of Inventory List"
                                    + " B is $"+totalValue+"\n");
                            }
                        }
                    }
                    System.out.print("\n");

                } while (opSelect != 10);
            }
        } while (listSelect != 4);
        
        System.out.print("\nThank you for using this program. Have a great "
                + "day!");   
    }
    
    public static void displayOptions(){
        System.out.print("\nPlease make a selction from one of the choices "
                + "below: \n1. Insert: Insert item in to list at a specified "
                + "position.\n2. Add to End: Insert item at end of the list."
                + "\n3. Delete Range: Delete range of items in the list.\n4. "
                + "Delete Specific Item: Delete all occurrences of a specified "
                + "item.\n5. Retrieve: Return item found at given position of "
                + "the list.\n6. Find Occurences: Find and return all positions "
                + "a given item is found.\n7. Check Size: Show the current size "
                + "of the list.\n8. Show List: Show all items currently in the "
                + "list.\n9. Compute total inventory value (inventory lists only)"
                + ".\n10. Go back.\nSelection: ");
    }
    
    public static Object getItem(int listSelect){
        String sItem;
        int quantity;
        double price;
        Object result = null;
        
        if(listSelect == 1){
            System.out.print("\nPlease enter the item to be added to the list: ");
            sItem = keyb.next();
            result = (String) sItem;
        }
        else{
            System.out.print("\nPlease enter the name of the new inventory "
                    + "entry: ");
            sItem = keyb.next();
            System.out.print("\nPlease enter the quantity of items: ");
            quantity = keyb.nextInt();
            System.out.print("\nPlease enter the price per item: ");
            price = keyb.nextDouble();
            
            result = new Inventory(sItem,quantity,price);
        }
        
        System.out.print("\nOperation Complete!");
        return result;
    }
}