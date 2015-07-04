import Algorithms.Apriori;
import DataStructures.Database;


public class Test {
    //static String file = "/home/vedavyas/project/weka-3-6-12/data/weather.nominal.arff";
    //static String file = "/home/vedavyas/project/data/heart/cleveland/clev_nominalized.arff";
    //static String file = "/home/vedavyas/project/data/heart/cleveland/initial/data/clev_0_1.arff";
    //static String file = "/home/vedavyas/project/data/heart/cleveland/nominalized/clev_nominalized.arff";
    //static String file = "/home/vedavyas/project/data/heart/hungarian/hungarian_nominalized.arff";
    //static String file = "/home/vedavyas/project/Data/TestData.arff";
    
    public static void testDatabase(String file) throws Exception {
        Database database = new Database(file);
        System.out.println(database);
    }
    
    public static void testApriori(String arffFile) throws Exception {
        Database database = new Database(arffFile);
        Apriori apriori = new Apriori(database, 0.15f, 90f, true);
        apriori.runApriori();
    }
    
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        testApriori(args[0]);
        long end = System.currentTimeMillis();
        System.out.println("\n\n"+(end-start)+"ms");
    }
}
