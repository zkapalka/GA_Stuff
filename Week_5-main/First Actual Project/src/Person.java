public class Person {

    String firstName = "Joe";
    String lastName;
    int age;
    String nickName;
    String address = "417 blah blah";

    public static void main(String[] args) {

        Person joe = new Person();
       Job joeJob = new Job();
       joeJob.jobTitle = "Aspiring SWE";
       Address joeAddress = new Address();


        joe.firstName = "Joe";
        joeAddress.state = "Tx";
        joeAddress.street = "124 Main Street";
        joeAddress.zipCode = "11111";





        Car joeCar = new Car();
        joeCar.model = "Toyota";
        System.out.println(joeCar.model);
        System.out.println(joeJob.jobTitle);
        System.out.println(joeAddress.street + ' ' + joeAddress.state + ' '+ joeAddress.zipCode);




}
}



