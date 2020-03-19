package vizGenerator;

public class Main {

    public static void main(String[] args) {
        String graphToImport = args[0];
        String outputDir = args[1];
        String algorithm = args[2];
        int mode = Integer.parseInt(args[3]);

//        if (mode < -1) {
//            System.out.println("mode shouldn't be less than -1!");
//            System.exit(-1);
//        }

        System.out.println("Reading from " + graphToImport);
        System.out.println("Writing to XXXXXXXXX" + outputDir);
        System.out.println("using algorithm: " +  algorithm);
        System.out.println("mode is " + mode);

        switch (algorithm) {

            case "fa2":
                ForceAtlas2Gen forceAtlas2Gen = new ForceAtlas2Gen(5000,
                        graphToImport,
                        outputDir + "fa2_test1",
                        true,
                        false,
                        true,
                        1.0,
                        10.0,
                        false,
                        20.0,
                        0.2,
                        false,
                        1.2);

                forceAtlas2Gen.buildEnv();

                if (mode < 0) {
                    forceAtlas2Gen.randomizeLayoutSettings();
                } else if (mode > 0) {
                    forceAtlas2Gen.adjustSettingInNeighborhood(mode);
                }

                forceAtlas2Gen.generateViz();
                System.out.println("Made Force Atlas 2 Gen");

                break;

            case "fa":
                ForceAtlasGen forceAtlasGen = new ForceAtlasGen(
                        5000,
                        graphToImport,
                        outputDir + "fa_test1",
                        true,
                        100.0,
                        true,
                        5.0,
                        5.0,
                        false,
                        5.0,
                        10.0,
                        0.1,
                        200.0,
                        1.0,
                        0.1);
                forceAtlasGen.buildEnv();

                if (mode < 0) {
                    forceAtlasGen.randomizeLayoutSettings();
                } else if (mode > 0){
                    forceAtlasGen.adjustSettingInNeighborhood(mode);
                }

                forceAtlasGen.generateViz();
                System.out.println("Made Force Atlas 1 Gen");

                break;

            case "ft":
                FruchtermanGen fruchtermanGen = new FruchtermanGen(
                        5000,
                        graphToImport,
                        outputDir + "fruchterman_test1",
                        100.0);
                fruchtermanGen.buildEnv();

                if (mode < 0){
                    fruchtermanGen.randomizeLayoutSettings();
                } else if (mode > 0){
                    fruchtermanGen.adjustSettingInNeighborhood(mode);
                }

                fruchtermanGen.generateViz();
                System.out.println("Made fruchtermanGen Gen");

                break;

            case "yf":
                YifanHuGen yifanHuGen = new YifanHuGen(5000,
                        graphToImport,
                        outputDir + "yifanhu_test1",
                        30.0f,
                        0.3f,
                        true,
                        0.0001f,
                        20,
                        0.8f);
                yifanHuGen.buildEnv();

                if (mode < 0){
                    yifanHuGen.randomizeLayoutSettings();
                } else if (mode > 0){
                    yifanHuGen.adjustSettingInNeighborhood(mode);
                }

                yifanHuGen.generateViz();
                System.out.println("Made yifanHuGen Gen");

                break;

            case "oo":
                OpenOrdGen openOrdGen = new OpenOrdGen(5000,
                        graphToImport,
                        outputDir + "openord_test1",
                        25,
                        25,
                        25,
                        10,
                        15,
                        0.8f,
                        1300,
                        55643);
                openOrdGen.buildEnv();

                if (mode < 0){
                    openOrdGen.randomizeLayoutSettings();
                } else if (mode > 0){
                    openOrdGen.adjustSettingInNeighborhood(mode);
                }
                openOrdGen.generateViz();
                System.out.println("Made openOrdGen Gen");

                break;

        }

    }
}
