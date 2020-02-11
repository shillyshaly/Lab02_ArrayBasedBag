public class TestArea {
    public static void main (String[] args){

        int[] setA = new int[0];
        int[] setB = new int[0];
        ArraySetWithArray<String> set4 = new ArraySetWithArray<>();
        ArraySetWithArray<String> set5 = new ArraySetWithArray<>();
        set4.add("C");
        set4.add("A");
        set4.add("A");
        set4.add("A");
        set4.add("X");

        set5.add("A");
        set5.add("A");
        set5.add("A");
        set5.add("C");
        set5.add("X");

        set4.moveFirstToEnd();
        set4.displaySet();
        set4.moveFirstToEnd();
        set4.displaySet();

    }

}

