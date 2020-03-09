package vizGenerator;

public class Main {

    public static void main(String[] args) {

        ForceAtlas2Gen forceAtlas2Gen = new ForceAtlas2Gen(5000,
                "resources/org/gephi/toolkit/demos/graph_43_nodes.gexf",
                "forceatlas2_test1",
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

//        forceAtlas2Gen.buildEnv();
//        forceAtlas2Gen.generateViz();
//        forceAtlas2Gen.randomizeLayoutSettings();
//        forceAtlas2Gen.setExport_file_path("forceatlas2_test3");
//        forceAtlas2Gen.generateViz();

        ForceAtlasGen forceAtlasGen = new ForceAtlasGen(
                5000,
                "resources/org/gephi/toolkit/demos/graph_43_nodes.gexf",
                "fa_test1",
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
//        forceAtlasGen.buildEnv();
//        forceAtlasGen.generateViz();

        FruchtermanGen fruchtermanGen = new FruchtermanGen(
                5000,
                "resources/org/gephi/toolkit/demos/graph_43_nodes.gexf",
                "fruchterman_test1",
                100.0);

        fruchtermanGen.buildEnv();
        fruchtermanGen.generateViz();

        YifanHuGen yifanHuGen = new YifanHuGen(5000,
                "resources/org/gephi/toolkit/demos/graph_43_nodes.gexf",
                "yifanhu_test1",
                30.0f,
                0.3f,
                true,
                0.0001f,
                20,
                0.8f);
//        yifanHuGen.buildEnv();
//        yifanHuGen.script();

        OpenOrdGen openOrdGen = new OpenOrdGen(5000,
                "resources/org/gephi/toolkit/demos/graph_43_nodes.gexf",
                "openord_test1",
                25,
                25,
                25,
                10,
                15,
                0.8f,
                1300);
//        openOrdGen.script();
    }
}
