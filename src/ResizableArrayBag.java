import java.util.Arrays;
/**
 * A class that implements a bag of objects by using an array.
 * The bag is never full.
 *
 * @version 9/2/2019
 */
public class ResizableArrayBag<T extends Comparable<? super T>> implements BagInterface<T>
{
    private T[] bag; // Cannot be final due to dynamic resizing of a bag
    private int numberOfEntries;
    private boolean initialized;
    private static final int DEFAULT_CAPACITY = 3; // Initial capacity of bag
    private static final int MAX_CAPACITY = 10000;

    /**
     * Creates an empty bag whose initial capacity is DEFAULT_CAPACITY.
     */
    public ResizableArrayBag()
    {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    /**
     * Creates an empty bag having a given initial capacity.
     *
     * @param initialCapacity The integer capacity desired.
     */
    public ResizableArrayBag(int initialCapacity)
    {
        checkCapacity(initialCapacity);

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempBag = (T[]) new Comparable<?>[initialCapacity]; // Unchecked cast
        this.bag = tempBag;
        this.numberOfEntries = 0;
        this.initialized = true;
    } // end constructor

    /**
     * Creates a bag containing given entries.
     *
     * @param contents An array of objects.
     */
    public ResizableArrayBag(T[] contents, int numberOfElements)
    {
        this(numberOfElements);
        for (int i = 0; i < numberOfElements; i++)
        {
            if (contents[i] != null)
            {
                this.bag[this.numberOfEntries] = contents[i];
                this.numberOfEntries++;
            }
        }
    } // end constructor

    /**
     * Adds a new entry to this bag.
     *
     * @param newEntry The object to be added as a new entry.
     * @return True.
     */
    public boolean add(T newEntry)
    {
        checkInitialization();
        if (isArrayFull())
        {
            doubleCapacity();
        }

        this.bag[this.numberOfEntries] = newEntry;
        this.numberOfEntries++;

        return true;
    } // end add

    /**
     * Retrieves all entries that are in this bag.
     *
     * @return A newly allocated array of all the entries in this bag.
     */
    public T[] toArray()
    {
        checkInitialization();

        // The cast is safe because the new array contains null entries.
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Comparable<?>[this.numberOfEntries]; // Unchecked cast
        for (int index = 0; index < this.numberOfEntries; index++)
        {
            result[index] = this.bag[index];
        }

        return result;
        //return Arrays.copyOf(this.bag, this.numberOfEntries);

    } // end toArray

    /**
     * Sees whether this bag is empty.
     *
     * @return True if this bag is empty, or false if not.
     */
    public boolean isEmpty()
    {
        return this.numberOfEntries == 0;
    } // end isEmpty

    /**
     * Gets the current number of entries in this bag.
     *
     * @return The integer number of entries currently in this bag.
     */
    public int getCurrentSize()
    {
        return this.numberOfEntries;
    } // end getCurrentSize

    /**
     * Counts the number of times a given entry appears in this bag.
     *
     * @param anEntry The entry to be counted.
     * @return The number of times anEntry appears in this ba.
     */
    public int getFrequencyOf(T anEntry)
    {
        checkInitialization();
        int counter = 0;

        for (int index = 0; index < this.numberOfEntries; index++)
        {
            if (anEntry.equals(this.bag[index]))
            {
                counter++;
            }
        }

        return counter;
    } // end getFrequencyOf

    /**
     * Tests whether this bag contains a given entry.
     *
     * @param anEntry The entry to locate.
     * @return True if this bag contains anEntry, or false otherwise.
     */
    public boolean contains(T anEntry)
    {
        checkInitialization();
        return getIndexOf(anEntry) > -1; // or >= 0
    } // end contains

    // Locates a given entry within the array bag.
    // Returns the index of the entry, if located,
    // or -1 otherwise.
    // Precondition: checkInitialization has been called.
    private int getIndexOf(T anEntry)
    {
        int where = -1;
        boolean stillLooking = true;

        for (int index = 0; stillLooking && (index < this.numberOfEntries); index++)
        {
            if (anEntry.equals(this.bag[index]))
            {
                stillLooking = false;
                where = index;
            }
        }

        return where;
    } // end getIndexOf

    /**
     * Removes all entries from this bag.
     */
    public void clear()
    {
        checkInitialization();
        while (!isEmpty())
            remove();
    } // end clear

    /**
     * Removes one unspecified entry from this bag, if possible.
     *
     * @return Either the removed entry, if the removal
     * was successful, or null.
     */
    public T remove()
    {
        checkInitialization();
        return removeEntry(this.numberOfEntries - 1);
    } // end remove

    /**
     * Removes one occurrence of a given entry from this bag.
     *
     * @param anElement The entry to be removed.
     * @return True if the removal was successful, or false if not.
     */
    public boolean removeElement(T anElement)
    {
        checkInitialization();
        int index = getIndexOf(anElement);
        T result = removeEntry(index);
        return anElement.equals(result);
    } // end removeElement


    // Removes and returns the entry at a given index within the array.
    // If no such entry exists, returns null.
    // Precondition: 0 <= givenIndex < numberOfEntries.
    // Precondition: checkInitialization has been called.
    private T removeEntry(int givenIndex)
    {
        T result = null;

        if (!isEmpty() && (givenIndex >= 0))
        {
            result = this.bag[givenIndex];                          // Entry to remove
            this.numberOfEntries--;
            this.bag[givenIndex] = this.bag[this.numberOfEntries];  // Replace entry to remove with last entry
            this.bag[this.numberOfEntries] = null;                  // Remove reference to last entry
        }

        return result;
    } // end removeEntry

    // Returns true if the array bag is full, or false if not.
    private boolean isArrayFull()
    {
        return this.numberOfEntries >= this.bag.length;
    } // end isArrayFull

    // Doubles the size of the array bag.
    // Precondition: checkInitialization has been called.
    private void doubleCapacity()
    {
        int newLength = 2 * this.bag.length;
        checkCapacity(newLength);
        this.bag = Arrays.copyOf(this.bag, newLength);
    } // end doubleCapacity

    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a bag whose capacity exceeds " +
                    "allowed maximum of " + MAX_CAPACITY);
    } // end checkCapacity

    // Throws an exception if receiving object is not initialized.
    private void checkInitialization()
    {
        if (!this.initialized)
            throw new SecurityException("Uninitialized object used " +
                    "to call an ArrayBag method.");
    } // end checkInitialization
} // end ResizableArrayBag
