package source;

public class Encoder {
    private String s;

    public Encoder(String input){
        this.s = input;
    }

    public String urlEncode(){
        String r0 = this.s;
        String r1 = r0.replaceAll("'","%27");
        String r2 = r1.replaceAll(" ","%20");
        String r3 = r2.replaceAll("#","%23");
        String r4 = r3.replaceAll("<","%3c");
        String r5 = r4.replaceAll(">","%3e");
        //String r6 = r5.replaceAll("%","%25");
        return r5;
    }
}
