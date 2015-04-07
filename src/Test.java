import Algorithms.Apriori;
import DataStructures.Database;


public class Test {
    //static String file = "/home/vedavyas/project/weka-3-6-12/data/weather.nominal.arff";
    static String file = "/home/vedavyas/project/data/heart/cleveland/clev_nominalized.arff";
    //static String file = "/home/vedavyas/project/data/heart/cleveland/clev_nominalized.arff";
    //static String file = "/home/vedavyas/project/data/heart/cleveland/clev_nominalized.arff";
    //static String file = "/home/vedavyas/project/Data/TestData.arff";
    
    public static void testDatabase() throws Exception {
        Database database = new Database(file);
        System.out.println(database);
    }
    
    public static void testApriori() throws Exception {
        Database database = new Database(file);
        Apriori apriori = new Apriori(database, 0.1f, 80f, true);
        apriori.runApriori();
    }
    
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        testApriori();
        long end = System.currentTimeMillis();
        System.out.println("\n\n"+(end-start)+"ms");
    }
}
