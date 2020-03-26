package vizGenerator;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String graphToImport = args[0];
        String outputDir = args[1];
        String algorithm = args.length > 2 ? args[2] : "all";
        int mode = args.length == 4 ? Integer.parseInt(args[3]) : 0;

//        if (mode < -1) {
//            System.out.println("mode shouldn't be less than -1!");
//            System.exit(-1);
//        }

        System.out.println("Reading from " + graphToImport);
        System.out.println("Writing to " + outputDir);
        System.out.println("using algorithm: " +  algorithm);
        System.out.println("mode is " + mode);


        ArrayList<Generation> gs = new ArrayList<>();
        if(algorithm.equals("fa2") || algorithm.equals("all")) {
            gs.add(new ForceAtlas2Gen(5000,
                    graphToImport,
                    outputDir + "/fa2",
                    true,
                    false,
                    true,
                    1.0,
                    10.0,
                    false,
                    20.0,
                    0.2,
                    false,
                    1.2));
        }
        if(algorithm.equals("fa") || algorithm.equals("all")) {
            gs.add(new ForceAtlas2Gen(5000,
                    graphToImport,
                    outputDir + "/fa",
                    true,
                    false,
                    true,
                    1.0,
                    10.0,
                    false,
                    20.0,
                    0.2,
                    false,
                    1.2));
        }
        if(algorithm.equals("ft") || algorithm.equals("all")) {
            gs.add(new FruchtermanGen(5000,
                    graphToImport,
                    outputDir + "/ft",
                    100.0));
        }
        if(algorithm.equals("yf") || algorithm.equals("all")) {
            gs.add(new YifanHuGen(5000,
                    graphToImport,
                    outputDir + "/yf",
                    30.0f,
                    0.3f,
                    true,
                    0.0001f,
                    20,
                    0.8f));
        }
        if(algorithm.equals("oo") || algorithm.equals("all")) {
            gs.add(new OpenOrdGen(5000,
                    graphToImport,
                    outputDir + "/oo",
                    25,
                    25,
                    25,
                    10,
                    15,
                    0.8f,
                    1300,
                    55643));
        }

        for(Generation g : gs) {
            g.buildEnv();
            if (mode < 0) {
                g.randomizeLayoutSettings();
            } else if (mode > 0) {
                g.adjustSettingInNeighborhood(mode);
            }
            g.generateViz();
        }
    }
}
