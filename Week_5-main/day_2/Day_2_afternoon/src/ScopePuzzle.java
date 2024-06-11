public class ScopePuzzle {
    public static void main(String[] args) {
        String hero = "Arthur";

//        if (true){
//            String hero = "Lancelot";
//            System.out.println(hero); // Point A
//        }

        System.out.println(hero); // Point B

        {
            hero = "Jack";
            System.out.println(hero); // Point C
        }

        System.out.println(hero); // Point D
    }
}
