package vizGenerator;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        String graphToImport = args[0];
        String outputDir = args[1];
        String configDir = args[2];
        String algorithm = args.length > 3 ? args[3] : "all";
        int mode = args.length > 4? Integer.parseInt(args[4]) : 0;

        System.out.println("Reading from " + graphToImport);
        System.out.println("Writing to " + outputDir);
        System.out.println("Configs from" + configDir);
        System.out.println("using algorithm: " +  algorithm);
        System.out.println("mode is " + mode);

        ArrayList<Generation> gs = new ArrayList<>();
        if(algorithm.equals("fa2") || algorithm.equals("all")) {
            gs.add(new ForceAtlas2Gen(10000,
                    graphToImport,
                    outputDir + "/fa2",
                    true,
                    false,
                    true,
                    0.0,
                    8.0,
                    false,
                    15.0,
                    0.1,
                    false,
                    1.2));
        }
        if(algorithm.equals("fa") || algorithm.equals("all")) {
            gs.add(new ForceAtlasGen(10000,
                    graphToImport,
                    outputDir + "/fa",
                    true,
                    150.0,
                    true,
                    10.0,
                    5.0,
                    false,
                    5.0,
                    10.0,
                    0.1,
                    200.0,
                    1.0,
                    0.1));
        }
        if(algorithm.equals("ft") || algorithm.equals("all")) {
            gs.add(new FruchtermanGen(10000,
                    graphToImport,
                    outputDir + "/ft",
                    200.0));
        }
        if(algorithm.equals("yf") || algorithm.equals("all")) {
            gs.add(new YifanHuGen(10000,
                    graphToImport,
                    outputDir + "/yf",
                    25.0f,
                    0.15f,
                    true,
                    0.0001f,
                    20,
                    0.8f));
        }
        if(algorithm.equals("oo") || algorithm.equals("all")) {
            gs.add(new OpenOrdGen(10000,
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

            g.readConfig(configDir);

            if (mode < 0) {
                g.randomizeLayoutSettings();
            } else if (mode > 0) {
                g.adjustSettingInNeighborhood(mode);
            }

            g.writeConfig(configDir);

            g.generateViz();

//            OpenOrdConfig config = new OpenOrdConfig(g);
//            String s = JsonEncoder.encode(config);
//            System.out.println(s);
//
//            Path path = Paths.get("oocig.txt");
//            Files.write(path, Collections.singleton(s));
//
//            String ss = Files.readString(path);
//            System.out.println(ss);
//            OpenOrdConfig config2 = JsonDecoder.decode(ss, OpenOrdConfig.class);
//            System.out.println(config2);

//            String config = JsonEncoder.encode(g);
//            System.out.println(config);
        }
    }
}
