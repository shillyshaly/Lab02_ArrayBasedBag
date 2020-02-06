public class TestForArray {
    public static void main(String[] args){
        String[] s1 = {"A", "b", "C"};

        if (s1.length == 0){
            System.out.println("set is empty");
        }else {
            for (String s : s1){
                System.out.println(s);
            }
        }
    }
}
