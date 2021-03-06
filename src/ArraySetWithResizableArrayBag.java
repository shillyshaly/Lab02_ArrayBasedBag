import java.util.*;
/**
 * A class that implements the ADT set by using a ResizableArrayBag.
 *
 * @author Jamie Hernandez
 * @version 2/4/2020
 */
public class ArraySetWithResizableArrayBag<T extends Comparable<? super T>> implements SetInterface<T> {
    private ResizableArrayBag<T> setOfEntries;

    public ArraySetWithResizableArrayBag() {
        //  TODO Project1 - DONE
        this.setOfEntries = new ResizableArrayBag<>();
    } // end default constructor

    public ArraySetWithResizableArrayBag(int capacity) {
        //TODO Project1 - DONE
        this.setOfEntries = new ResizableArrayBag<>(capacity);
    } // end of secondary constructor

    public ArraySetWithResizableArrayBag(T[] contents, int numberOfElements) {
        //TODO Project1 - DONE
        this(numberOfElements);
        for (int i = 0; i < numberOfElements; i++) {
            if (contents[i] != null) {
                this.add(contents[i]);
            }
        }
    }

    public void clear() {
        this.setOfEntries.clear();
    } // end clear

    public boolean add(T newEntry) {
        //TODO Project1 - DONE
        boolean success = false;
        if (newEntry != null && !this.setOfEntries.contains(newEntry)) {
            success = this.setOfEntries.add(newEntry);
        }
        return success;

    } // end add

    public boolean removeElement(T anEntry) {
        //TODO Project1 - DONE
        return this.setOfEntries.removeElement(anEntry);
    } // end remove

    public T remove() {
        //TODO Project1 - DONE

        return this.setOfEntries.remove(); //THIS IS A STUB
    } // end remove

    public boolean contains(T anEntry) {
        //TODO Project1 - DONE
        return this.setOfEntries.contains(anEntry);
    } // end contains

    public int getCurrentSize() {
        //TODO Project1 - DONE
        return this.setOfEntries.getCurrentSize();
    } // end getCurrentSize

    public boolean isEmpty() {
        //TODO Project1 - DONE
        return this.setOfEntries.isEmpty();
    } // end getLength

    public T[] toArray() {
        //TODO Project1 - DONE
        return this.setOfEntries.toArray();
    } // end toArray

    /**
     * Displays a set.
     * If the set is empty displays a message that the set is empty
     * If the set is not empty displays the number of elements and the content of the set
     */

    public void displaySet() {
        //TODO Project1 - DONE
        System.out.println("--> calling displaySet method to display results");
        if (!this.setOfEntries.isEmpty()) {
            System.out.print("The set contains " + this.setOfEntries.getCurrentSize() + " string(s): ");
            T[] tempSet = this.setOfEntries.toArray();
            for (T index : tempSet) {
                System.out.print(index + " ");
            }
        }
        else {
            System.out.printf("the set is empty\n\n");
        }
        System.out.println();
    } // end displaySet

    public static void main(String[] args) {
        String[] inputData = {"A", "B", "C", "D", "A", "C", "B", "B"};

        System.out.println("--> Creating set1 with the secondary constructor - capacity of " + inputData.length);
        SetInterface<String> set1 = new ArraySetWithResizableArrayBag<>(inputData.length);
        System.out.println("--> Adding elements from inputData: " + Arrays.toString(inputData) + " to set1");
        for (int i = 0; i < inputData.length; i++)
            set1.add(inputData[i]);
        System.out.println("--> Calling displaySet method to display elements in set1");
        set1.displaySet();
        System.out.println("--> Calling displaySet method to display elements in set1 one more time");
        set1.displaySet();
        System.out.println("\n--> Clearing set1");
        set1.clear();
        set1.displaySet();
        System.out.println("--> set1 isEmpty returns \n" + set1.isEmpty());
        System.out.println("--> The number of elements in set1 is \n" + set1.getCurrentSize());

        System.out.println("\n--> Creating set2 with default constructor");
        SetInterface<String> set2 = new ArraySetWithResizableArrayBag<>();

        // Initial capacity is 3
        System.out.println("--> set2 initially empty");
        set2.displaySet();

        System.out.println("\n--> Adding elements to set2");
        set2.add("A");
        set2.add("A");
        set2.add("B");
        set2.add("A");
        set2.add("C");
        set2.add("A");

        System.out.println("--> set2 after adding elements");
        set2.displaySet();

        System.out.println("\n--> Adding elements to set2");

        set2.add("V");
        set2.add("V");
        set2.add("T");
        set2.add("U");
        set2.add("V");
        set2.add("W");
        set2.add("X");
        set2.add("Y");
        set2.add("Z");
        System.out.println("\n--> set2 after adding more elements");
        set2.displaySet();

        System.out.println("\n--> set2 contains \"A\":\n" + set2.contains("A"));
        System.out.println("--> set2 contains \"E\":\n" + set2.contains("E"));

        System.out.println("\n--> Removing \"B\" from set2");
        set2.removeElement("B");
        System.out.println("--> After removing \"B\" from set2:");
        set2.displaySet();

        System.out.println("--> Removing random element from set2");
        System.out.println("--> After removing \"" + set2.remove()
                + "\" from set2:");
        set2.displaySet();

        System.out.println("\n--> Trying to remove \"Q\" from set2");
        set2.removeElement("Q");
        System.out.println("--> After trying to remove non-existent element \"Q\" from set2:");
        set2.displaySet();

        System.out.println("\n--> Adding 4 elements to set2");
        set2.add("K");
        set2.add("L");
        set2.add("M");
        set2.add("N");
        System.out.println("--> After adding 4 elements to set2:");
        set2.displaySet();

        System.out.println("--> Adding 1 element to set2");
        set2.add("O");
        System.out.println("--> After adding 1 more element to set2:");
        set2.displaySet();

        System.out.println("\n--> Creating set3 with the secondary constructor that takes an array as input");
        SetInterface<String> set3 = new ArraySetWithResizableArrayBag<>(inputData, inputData.length);
        set3.displaySet();
        System.out.println("--> Adding more elements to set3");
        set3.add("A");
        set3.add("B");
        set3.add("B");
        set3.add("A");
        set3.add("C");
        set3.add("C");
        set3.add("D");
        set3.add("E");
        set3.add("F");
        set3.add("G");
        set3.add("H");
        System.out.println("--> set3 after adding:");
        set3.displaySet();

        System.out.println("\nTrying to add null entry");
        String nullEntry = null;
        set3.add(nullEntry);
        System.out.println("--> set3 after adding:");
        set3.displaySet();
    }
} // end ArraySetWithResizableArrayBag
