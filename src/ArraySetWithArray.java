import com.sun.org.apache.bcel.internal.generic.IAND;
import sun.security.util.ArrayUtil;
import sun.security.util.ManifestDigester;

import java.util.Arrays;
/**
 * A class that implements the ADT set by using a resizable array.
 * The array is never full.
 *
 * @author Jamie Hernandez
 * @version 2/4/2020
 */
public class ArraySetWithArray<T extends Comparable<? super T>> implements SetInterface<T>
{
    private T[] setOfEntries;
    private int numberOfEntries;
    private boolean initialized;
    private static final int DEFAULT_CAPACITY = 3; // Initial capacity of array
    private static final int MAX_CAPACITY = 10000;

    /**
     * Creates an empty array whose initial capacity is 3.
     */
    public ArraySetWithArray()
    {
        //TODO Project2 - DONE
        this(DEFAULT_CAPACITY);
    } // end default constructor

    /**
     * Creates an empty array having a given initial capacity.
     *
     * @param capacity The integer capacity desired.
     */
    public ArraySetWithArray(int capacity)
    {
        //TODO Project2 - DONE
        T[] tempBag = (T[]) new Comparable<?>[capacity];
        this.setOfEntries = tempBag;
        this.numberOfEntries = 0;
        this.initialized = true;
    } // end constructor

    /**
     * Creates an array containing given entries.
     *
     * @param contents An array of objects.
     */
    public ArraySetWithArray(T[] contents, int numberOfEntries)
    {
        //TODO Project2 - DONE (use add to check for null and duplicates)
        // calls this.add to check for null entries and duplicated elements
        this(numberOfEntries);
        for (int i = 0; i < numberOfEntries; i++){
            if (contents[i] != null){
                this.add(contents[i]);
            }
        }
    } // end constructor

    /**
     * Throws an exception if the client requests a capacity that is too large.
     */
    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a set whose capacity exceeds " +
                    "allowed maximum of " + MAX_CAPACITY);
    } // end checkCapacity

    /**
     * Throws an exception if receiving object is not initialized.
     */
    private void checkInitialization()
    {
        if (!this.initialized)
            throw new SecurityException("Uninitialized object used " +
                    "to call an ArraySetWithArray method.");
    } // end checkInitialization

    /**
     * Adds a new entry to this array, rejecting duplicates and null entries.
     *
     * @param newEntry The object to be added as a new entry.
     * @return true if the addition is successful, or
     *         false in case of null entry or duplicate to be added.
     */
    public boolean add(T newEntry)
    {
        //TODO Project2 - DONE
        boolean success = false;
        ensureCapacity();
        if (newEntry != null && !this.contains(newEntry)){
            this.setOfEntries[this.numberOfEntries] = newEntry;
            this.numberOfEntries++;
            success = true;
        }
        return success; //THIS IS A STUB
    } // end add

    /**
     * Checks if the set is full; if it is full doubles its size
     */
    private void ensureCapacity()
    {
        //TODO Project2 - DONE
        int newSize = 2 * this.setOfEntries.length;
        if (this.setOfEntries.length == this.numberOfEntries){
            this.setOfEntries = Arrays.copyOf(this.setOfEntries,newSize);
        }
    } // end ensureCapacity

    /**
     * Retrieves all entries that are in this array.
     *
     * @return A newly allocated array of all the entries.
     */
    public T[] toArray()
    {
        //TODO Project2 - DONE
        T[] result = (T[]) new Comparable<?>[this.numberOfEntries];
        for (int i = 0; i < this.numberOfEntries; i++){
            result[i] = this.setOfEntries[i];
        }
        return result; //THIS IS A STUB
    } // end toArray

    /**
     * Sees whether this array is empty.
     *
     * @return True if this array is empty, or false if not.
     */
    public boolean isEmpty()
    {
        //TODO Project2 - DONE
        return this.numberOfEntries == 0;
    } // end isEmpty

    /**
     * Gets the number of entries currently in this array.
     *
     * @return The integer number of entries currently in the array.
     */
    public int getCurrentSize()
    {
        //TODO Project2 - DONE
        return this.numberOfEntries;
    } // end getCurrentSize

    /**
     * Tests whether this array contains a given entry.
     *
     * @param anEntry The entry to locate.
     * @return True if the array contains anEntry, or false if not.
     */
    public boolean contains(T anEntry)
    {
        //TODO Project2 - DONE
        checkInitialization();
        // utilize getIndexOf method
        return getIndexOf(anEntry) >= 0;
    } // end contains

    /**
     * Locates a given entry within the array set.
     * Returns the index of the entry, if located,
     * or -1 otherwise.
     * Precondition: checkInitialization has been called.
     */
    private int getIndexOf(T anEntry)
    {
        // TODO Project 2 - DONE
        int index = -1;
        boolean it = true;
        for (int i = 0; it && (i < this.numberOfEntries); i++){
            if (anEntry.equals(this.setOfEntries[i])){
                it = false;
                index = i;
            }
        }
        return index;
    } // end getIndexOf

    /**
     * Removes all entries from this array.
     */
    public void clear()
    {
        //TODO Project2 - DONE
        checkInitialization();
        while (!isEmpty()){
            remove();
        }
    } // end clear

    /**
     * Removes one unspecified entry from this set.
     *
     * @return Either the removed entry if the removal
     * was successful, or null if not.
     */
    public T remove()
    {
        //TODO Project2 - DONE
        checkInitialization();
        return removeEntry(this.numberOfEntries - 1);
    } // end remove

    /**
     * Removes one occurrence of a given entry from this array.
     *
     * @param anEntry The entry to be removed.
     * @return true if the removal was successful, or false if not.
     */
    public boolean removeElement(T anEntry)
    {
        //TODO Project2 - DONE
        checkInitialization();
        T removed = removeEntry(getIndexOf(anEntry));
        return anEntry.equals(removed);
    } // end removeElement

    /**
     * Removes and returns the array entry at a given index.
     *
     * @param givenIndex index of the element to be removed
     * @return remove an element or null if no such entry exists
     */
    private T removeEntry(int givenIndex)
    {
        //TODO Project2 - DONE
        T remove = null;
        if (!isEmpty() && (givenIndex >= 0)){
            remove = this.setOfEntries[givenIndex];
            this.numberOfEntries--;
            this.setOfEntries[givenIndex] = this.setOfEntries[this.numberOfEntries];
            this.setOfEntries[this.numberOfEntries] = null;
        }
        return remove;
    } // end removeEntry

    //
    // +++++++++++++++++++  NEW METHODS  +++++++++++++++++++++++++++
    //

    /**
     * Displays all entries in the set.
     * If the set is empty displays a message that the set is empty and display the capacity
     * if the set is not empty displays the number of elements, capacity and the content of the set
     */
    public void displaySet()
    {
        //TODO Project2 - DONE
        // do not call toArray as this method has direct access to this.setOfEntries array
        if (!this.isEmpty()){
            System.out.print("The set capacity is " + this.setOfEntries.length + "; it contains " +
                    this.getCurrentSize() + " string(s): ");
            for (int i = 0; i < this.getCurrentSize(); i++){
                System.out.print(this.setOfEntries[i] + " ");
            }
            System.out.println();
        }
    } // end displaySet

    /**
     * Checks if the given set called other is the same as this set
     *
     * @param o the other set to be compared with
     * @return true both sets are the same
     */
    public boolean equals(Object o)
    {
        boolean same = true;
        if (this == o)
            same = true;
        else if (o == null || getClass() != o.getClass())
            same = false;
        else
        {
            ArraySetWithArray<T> otherSet = (ArraySetWithArray<T>) o;
            //TODO Project2 - DONE
            // one return statement per method please

            // first compare number of entries in both sets///
            // only if the number of entries is the same///
            //      use a regular for loop to compare elements
            //      stop the loop as soon as the first unequal pair is found

            //BEGIN PSEUDO CODE:
            // if setOfEntries length equals otherSet length
            //      for (setOfEntries length)
            //          if setOfEntries index does not equal otherSet index
            //              stop loop
            //
            T[] otherArr = otherSet.toArray();
            if (this.numberOfEntries == otherSet.numberOfEntries){
                for (int i = 0; i < this.numberOfEntries; i++){
                    if (this.setOfEntries[i] != otherArr[i]){
                        same = false;
                        break;
                    }
                }
            }
        }
        return same;
    }

    /**
     * Removes the largest entry from the this.setOfEntries
     *
     * @return - null if the set is empty
     *           or the largest element
     */
    public T removeMax()
    {
        // TODO Project2 - DONE
        // utilize removeEntry(givenIndex) method
        // one return statement per method please
        // utilize compareTo method

        //BEGIN PSEUDO CODE:
        //declare max set to 0
        //for (this.setOfEntries length)
        //      compare max to each element
        //      set max to largest number found
        //return removeEntry(max)
        T temp = null;
        for (int i = 0; i < this.numberOfEntries - 1; i++){
            if ((this.setOfEntries[i].compareTo(this.setOfEntries[i + 1]) > 0)){
                temp = this.setOfEntries[i];
            }else{
                temp = this.setOfEntries[i + 1];
            }
        }
        removeEntry(getIndexOf(temp));
        return temp;
    } // end removeMax

    /**
     * Removes all entries from this.setOfEntries that are larger than a given entry.
     *
     * @param anEntry the entry to be checked against
     */
    public void removeAllLarger(T anEntry)
    {
        // TODO Project2 - DONE
        // must utilize only one loop that starts with the last element
        // utilize removeEntry(givenIndex) method
        // utilize compareTo method

        //BEGIN PSEUDO CODE:
        //declare larger set to 0
        //for (this.setOfEntries length)
        //      if (compare larger to this.setOfEntries index)
        //          set larger to larger number
        //          call removeEntry(larger)
        int index = 0;
        for (int i = this.numberOfEntries - 1; i >= 0; i--){
            if (this.setOfEntries[i].compareTo(anEntry) > 0){
                index = this.getIndexOf(this.setOfEntries[i]);
                removeEntry(index);
            }
        }

    } // end removeAllLarger

    /**
     * Replaces the last entry in this setOfEntries with a given object.
     *
     * @param replacement the given object
     * @return the original entry in the setOfEntries that was replaced
     *         or null if the set is empty or if the replacement is a duplicate
     */
    public T replace(T replacement)
    {
        // TODO Project 2 - DONE
        // one return statement per method please

        //declare original set to last element in array
        //if (setOfEntries length equals 0 or replacement is equal to original)
        //      set original to null
        //else
        //      set this.setOfEntries last index to replacement
        //return original
//        T original = this.setOfEntries[this.numberOfEntries - 1];
        T original = this.isEmpty()? this.setOfEntries[this.numberOfEntries] : this.setOfEntries[this.numberOfEntries -1];

        if (this.isEmpty() || this.contains(replacement)){
            original = null;
        }else{
            this.setOfEntries[this.numberOfEntries - 1] = replacement;
        }

        return original; // THIS IS A STUB
    } // end replace

    /**
     * Creates a new set that combines the contents of this set and
     * a second given set without affecting the original two sets.
     *
     * @param otherSet the given set
     * @return a setOfEntries that is the union of the two sets
     */
    public ArraySetWithArray<T> union(ArraySetWithArray<T> otherSet)
    {
        // TODO Project 2 - DONE
        // one return statement per method please

        //BEGIN PSEUDO CODE:
        //create new array with length of both sets
        //for (length of newly created array)
        //      traverse and add each element of first array
        //      traverse and add each element of second array
        //return new array
        int size = this.setOfEntries.length + otherSet.setOfEntries.length;
        ArraySetWithArray<T> unionSet = new ArraySetWithArray<>(size);
        T[] thisArr = this.toArray();
        T[] otherArr = otherSet.toArray();

        for (T it : thisArr){
            unionSet.add(it);
        }
        for (T it : otherArr){
            unionSet.add(it);
        }
        return unionSet; // THIS IS A STUB
    } // end union

    /**
     * Creates a new set that contains those objects that occur in both this
     * set and otherSet without affecting the original two sets.
     *
     * @param otherSet the given set
     * @return a set that is the intersection of the two sets
     */
    public ArraySetWithArray<T> intersection(ArraySetWithArray<T> otherSet)
    {
        // TODO Project 2 - DONE
        // one return statement per method please
        // do NOT call contains, utilize getIndexOf(anEntry) instead

        //BEGIN PSEUDO CODE:
        //create a new array with length of both sets
        //for (length of newly created array)
        //      for (traverse second array)
        //          compare each element to each element of other array
        //          if (there is a match)
        //              add to new array
        //return new array

        ArraySetWithArray<T> intersectSet = new ArraySetWithArray<>();

        for (int i = 0; i < this.numberOfEntries; i++){
            for (int j = 0; j < otherSet.numberOfEntries; j++){
                if (this.setOfEntries[i] == otherSet.setOfEntries[j]){
                    intersectSet.add(this.setOfEntries[i]);
                }
            }
        }
        return intersectSet;
    } // end intersection

    /**
     * Creates a new set of objects that would be left in this set
     * after removing those that also occur in a second given set
     * without affecting the original two sets.
     *
     * @param otherSet the given set
     * @return a set that is the difference of the two sets
     */
    public ArraySetWithArray<T> difference(ArraySetWithArray<T> otherSet)
    {
        // TODO Project 2 - DONE
        // one return statement per method please
        // do NOT call contains, removeEntry(givenIndex) methods instead

        //BEGIN PSEUDO CODE:
        //create a new array with length of both sets
        //for (length of newly created array)
        //      for (traverse second array)
        //          compare each element to each element of other array
        //          if (there is a match)
        //              call removeEntry() for match
        //          else
        //              add to new array
        //return new array

        ArraySetWithArray<T> diffSet = new ArraySetWithArray<>();
        boolean found = false;

        for(int i = 0; i < this.numberOfEntries; i++) {
            for(int j = 0; j < otherSet.numberOfEntries; j++) {
                if(this.setOfEntries[i] == otherSet.setOfEntries[j]){
                    found = true;
                }
            }
            if(!found){
                diffSet.add(this.setOfEntries[i]);
            }
        }

        return diffSet;
    } // end difference

    /**
     * Creates a new set of objects that are in this set and are less than a given object.
     *
     * @param anEntry a given object
     * @return a new set of objects that are in this set and are less than anObject
     */
    public ArraySetWithArray<T> getAllLessThan(T anEntry)
    {
        // TODO Project 2 - DONE
        // one return statement per method please
        // utilize compareTo method

        //BEGIN PSEUDO CODE:
        //create a new array with length equal to both
        //declare smaller set to 0
        //for (this.setOfEntries length)
        //      if (compare setOfEntries index to anEntry)
        //          set smaller to smaller number
        //          add smaller to new array
        //return new array
        ArraySetWithArray<T> lessThan = new ArraySetWithArray<>();

        for (int i = 0; i < this.numberOfEntries; i++){
            if (this.setOfEntries[i].compareTo(anEntry) < 0){
                lessThan.add(this.setOfEntries[i]);
            }
        }

        return lessThan; // THIS IS A STUB
    } // end getAllLessThan

    /**
     * Checks if all the elements of the given set are also included in the other set
     *
     * @param otherSet set to check
     * @return returns true if all the elements of the given set are also included in the other set
     */
    public boolean isSubset(ArraySetWithArray<T> otherSet) {
        // TODO Project 2 - debugging
        // one return statement per method please
        // utilize difference method

        //BEGIN PSEUDO CODE: ???
        //if (use difference to check if length of otherset equals 0)
        int count = 0;
        boolean isSub = false;

        for (int i = 0; i < this.numberOfEntries; i++) {
            for (int j = 0; j < otherSet.numberOfEntries; j++) {
                if (this.setOfEntries[i] == otherSet.setOfEntries[j]) {
                    count++;
                    break;
                }
            }
        }
        isSub = count == otherSet.numberOfEntries;

        return isSub; // THIS IS A STUB
    }

    /**
     * Move the first element to be the last in the set by rotating the elements
     */
    public void moveFirstToEnd()
    {
        // TODO Project2 - DONE
        if (!this.isEmpty()){
            T temp = this.setOfEntries[0];
            this.setOfEntries[0] = this.setOfEntries[this.numberOfEntries - 1];
            this.setOfEntries[this.numberOfEntries - 1] = temp;
        }
    }


    public static void main(String[] args)
    {
        System.out.println("\n\u001B[35m\u001B[1mRUNNING TEST CASES FOR METHODS INCLUDED IN THE SetInterface plus displaySet\u001B[0m");
        String[] inputData = {"A", "B", "C", "D", "A", "C", "B", "B"};

        System.out.println("--> Creating set1 with the secondary constructor - capacity of " + inputData.length);
        SetInterface<String> set1 = new ArraySetWithArray<>(inputData.length);
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
        SetInterface<String> set2 = new ArraySetWithArray<>();

        // Initial capacity is 3
        System.out.println("--> set2 initially empty, capacity should be 3:");
        set2.displaySet();

        System.out.println("\n--> Adding elements to set2");
        set2.add("A");
        set2.add("A");
        set2.add("B");
        set2.add("A");
        set2.add("C");
        set2.add("A");

        System.out.println("--> set2 after adding elements, capacity should be 3:");
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
        System.out.println("\n--> set2 after adding more elements which should have triggered resizing, and the capacity should be 12:");
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
        SetInterface<String> set3 = new ArraySetWithArray<>(inputData, inputData.length);
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

        System.out.println("\n\u001B[35m\u001B[1mRUNNING TEST CASES FOR METHODS NOT INCLUDED IN THE SetInterface plus displaySet\u001B[0m");
        // the subsequent sets must be declared as ArraySetWithArray instead of SetInterface since we will be
        // testing methods that are not defined in the SetInterface
        ArraySetWithArray<String> set4 = new ArraySetWithArray<>(3);
        ArraySetWithArray<String> set5 = new ArraySetWithArray<>();

        set4.add("C");
        set4.add("A");
        set4.add("A");
        set4.add("A");
        set4.add("X");

        System.out.println("Created set4:");
        set4.displaySet();
        System.out.println("Created set5:");
        set5.displaySet();
        System.out.println("After removing the last element " + set4.remove() + " from set4, it contains:");
        set4.displaySet();


        // testing equals
        System.out.println("\n\u001B[35m\u001B[1m***Testing equals method***\u001B[0m");
        System.out.println("Are set4 and set5 equal? --> " + (set4.equals(set5) ? "YES" : "NO"));
        System.out.println("Are set5 and set4 equal? --> " + (set5.equals(set4) ? "YES" : "NO"));
        set5.add("A");
        set5.add("A");
        set5.add("A");
        set5.add("C");
        set5.add("X");
        System.out.println("set5:");
        set5.displaySet();
        System.out.println("Are set4 and set5 equal? --> " + (set4.equals(set5) ? "YES" : "NO"));
        System.out.println("Removed " + set5.remove() + " from set5:");
        set5.displaySet();
        System.out.println("Are set4 and set5 equal now? --> " + (set4.equals(set5) ? "YES" : "NO"));
        ArraySetWithArray<String> setCopyOfSet4 = new ArraySetWithArray<>(set4.toArray(), set4.getCurrentSize());
        System.out.println("Created setCopyOfSet4:");
        setCopyOfSet4.displaySet();
        System.out.println("Are set4 and setCopyOfSet4 equal? --> " + (set4.equals(setCopyOfSet4) ? "YES" : "NO"));

        set4.clear();
        set4.add("C");
        set4.add("A");
        set4.add("A");
        set4.add("X");
        set4.add("A");

        set5.clear();
        set5.add("A");
        set5.add("B");
        set5.add("B");
        set5.add("A");
        set5.add("C");
        set5.add("C");
        set5.add("D");
        System.out.println("\n\u001B[35m\u001B[1m***Testing union, intersection, difference, removeMax, getAllLessThan and isSubset methods***\u001B[0m");
        System.out.println("set4:");
        set4.displaySet();
        System.out.println("set5:");
        set5.displaySet();
        // testing union
        System.out.println("\n\u001B[35m\u001B[1m***Testing union method***\u001B[0m");
        ArraySetWithArray<String> everything = set4.union(set5);
        System.out.println("The union of set4 and set5 is:");
        everything.displaySet();

        // testing removeMax
        System.out.println("\n\u001B[35m\u001B[1m***Testing removeMax method***\u001B[0m");
        String largest = everything.removeMax();
        System.out.println("Removed the largest element \"" + largest + "\" from the union set; the current content is:");
        everything.displaySet();
        everything.clear();
        largest = everything.removeMax();
        if (largest == null)
            System.out.println("The set is empty and removeMax returned null - CORRECT");
        else
            System.out.println("The set is empty bur removeMax returned did not return null - INCORRECT");


        // testing intersection
        System.out.println("\n\u001B[35m\u001B[1m***Testing intersection method***\u001B[0m");
        ArraySetWithArray<String> commonItems = set4.intersection(set5);
        System.out.println("The intersection of set4 and set5 is:");
        commonItems.displaySet();

        // testing difference
        System.out.println("\n\u001B[35m\u001B[1m***Testing difference method***\u001B[0m");
        ArraySetWithArray<String> leftOver = set4.difference(set5);
        System.out.println("The difference of set4 and set5 is:");
        leftOver.displaySet();

        leftOver = set5.difference(set4);
        System.out.println("The difference of set5 and set4 is:");
        leftOver.displaySet();

        // testing getAllLessThan
        System.out.println("\n\u001B[35m\u001B[1m***Testing getAllLessThan method***\u001B[0m");
        ArraySetWithArray<String> smaller = set4.getAllLessThan("Z");
        System.out.println("The following entries in set4 are smaller than \"Z\":");
        smaller.displaySet();

        smaller = set5.getAllLessThan("C");
        System.out.println("The following entries in set5 are smaller than \"C\":");
        smaller.displaySet();


        // testing subset
        System.out.println("\n\u001B[35m\u001B[1m***Testing isSubset method***\u001B[0m");
        System.out.println("Is set4 a subset of set4 ? --> " + (set4.isSubset(set4) ? "YES" : "NO"));
        System.out.println("Is set4 a subset of set5 ? --> " + (set4.isSubset(set5) ? "YES" : "NO"));
        ArraySetWithArray<String> emptySet = new ArraySetWithArray<>();
        System.out.println("Is an empty set a subset of set5 ? --> " + (emptySet.isSubset(set5) ? "YES" : "NO"));
        System.out.println("Is set5 a subset of an empty set ? --> " + (set5.isSubset(emptySet) ? "YES" : "NO"));
        ArraySetWithArray<String> set6 = new ArraySetWithArray<>();
        ArraySetWithArray<String> set7 = new ArraySetWithArray<>();
        set6.add("A");
        set6.add("B");
        set6.add("C");
        System.out.println("Created set6:");
        set6.displaySet();

        set7.add("B");
        set7.add("C");
        set7.add("A");
        System.out.println("Created set7:");
        set7.displaySet();

        System.out.println("Is set6 a subset of set7 ? --> " + (set6.isSubset(set7) ? "YES" : "NO"));
        set7.add("Z");
        System.out.println("Is set6 a subset of set7 after adding \"Z\" to it ? --> " + (set6.isSubset(set7) ? "YES" : "NO"));
        System.out.println("Is set7 a subset of set6 ? --> " + (set7.isSubset(set6) ? "YES" : "NO"));
        System.out.println("Adding  \"Z\" to set6 twice");
        set6.add("Z");
        set6.add("Z");
        System.out.println("set6:");
        set6.displaySet();
        System.out.println("set7:");
        set7.displaySet();
        System.out.println("Is set6 a subset of set7 ? --> " + (set6.isSubset(set7) ? "YES" : "NO"));

        set4.clear();
        set4.add("X");
        set4.add("A");
        set4.add("B");
        set4.add("X");
        set4.add("D");
        set4.add("C");
        set4.add("A");

        // testing replace
        System.out.println("\n\u001B[35m\u001B[1m***Testing replace method***\u001B[0m");
        System.out.println("set4:");
        set4.displaySet();
        System.out.println("Trying to replace the last element with \"C\"");
        if (set4.replace("C") == null)
            System.out.println("CORRECT - returned null since no replacement was made");
        else
            System.out.println("INCORRECT - replacement should not be made");
        System.out.println("Now set4 contains:");
        set4.displaySet();
        System.out.println("Trying to replace the last element with \"X\"");
        if (set4.replace("X") == null)
            System.out.println("CORRECT - returned null since no replacement was made");
        else
            System.out.println("INCORRECT - replacement should not be made");
        System.out.println("Now set4 contains:");
        set4.displaySet();
        System.out.println("Trying to replace the last element with \"Z\"");
        if (set4.replace("Z") == null)
            System.out.println("INCORRECT - replacement should be made");
        else
            System.out.println("CORRECT - replacement was made");
        System.out.println("Now set4 contains:");
        set4.displaySet();
        System.out.println("\nCalling replace on emptySet");
        String replaced = emptySet.replace("X");
        if (replaced == null)
            System.out.println("The set is empty and replace returned null - CORRECT");
        else
            System.out.println("The set is empty but replace did not return null - INCORRECT");
        System.out.println("Now emptySet contains:");
        emptySet.displaySet();

        // testing removeAllLarger
        System.out.println("\n\u001B[35m\u001B[1m***Testing removeAllLarger method***\u001B[0m");
        System.out.println("set4:");
        set4.displaySet();
        System.out.println("Removing all that are larger than \"Z\"");
        set4.removeAllLarger("Z");
        System.out.println("After removing all that are larger than \"Z\" set4 contains:");
        set4.displaySet();
        System.out.println("Removing all that are larger than \"D\"");
        set4.removeAllLarger("D");
        System.out.println("After removing all that are larger than \"D\" set4 contains:");
        set4.displaySet();
        System.out.println("Removing all that are larger than \"B\"");
        set4.removeAllLarger("B");
        System.out.println("After removing all that are larger than \"B\" set4 contains:");
        set4.displaySet();

        // testing moveFirstToEnd
        System.out.println("\n\u001B[35m\u001B[1m***Testing moveFirstToEnd method***\u001B[0m");
        set4.clear();
        System.out.println("set4:");
        set4.displaySet();
        System.out.println("Trying empty set");
        set4.moveFirstToEnd();
        System.out.println("set4 contains now:");
        set4.displaySet();
        System.out.println("adding \"A\" to set4");
        set4.add("A");
        set4.displaySet();
        System.out.println("Trying 1-element set");
        set4.moveFirstToEnd();
        System.out.println("set4 contains now:");
        set4.displaySet();
        System.out.println("adding \"B\" to set4");
        set4.add("B");
        set4.displaySet();
        System.out.println("Trying 2-element set");
        set4.moveFirstToEnd();
        System.out.println("set4 contains now:");
        set4.displaySet();
        System.out.println("adding three more elements to set4");
        set4.add("X");
        set4.add("Y");
        set4.add("Z");
        set4.displaySet();
        System.out.println("Trying multi-element set");
        set4.moveFirstToEnd();
        System.out.println("set4 contains now:");
        set4.displaySet();
    }
} // end ArraySetWithArray
