package vizGenerator;

import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.UndirectedGraph;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.exporter.preview.PNGExporter;
import org.gephi.io.exporter.spi.GraphExporter;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.preview.api.PreviewController;
import org.gephi.preview.api.PreviewModel;
import org.gephi.preview.api.PreviewProperty;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.gephi.statistics.plugin.GraphDistance;
import org.openide.util.Lookup;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Generation {

    protected ProjectController pc;
    protected Workspace workspace;
    protected ImportController importController;
    protected Container container;
    protected GraphModel graphModel;
    protected UndirectedGraph graph;
    protected GraphDistance distance;
    protected PreviewController previewController;
    protected PreviewModel previewModel;
    protected ExportController ec;
    protected int nb_steps;
    protected String import_file_path;
    protected String export_file_path;

    Generation(int nb_steps, String import_file_path, String export_file_path){

        this.nb_steps = nb_steps;
        this.import_file_path = import_file_path;
        this.export_file_path = export_file_path;

        //Init a project - and therefore a workspace
        this.pc = Lookup.getDefault().lookup(ProjectController.class);
        this.pc.newProject();
        this.workspace = pc.getCurrentWorkspace();

        //Get controllers and models
        this.importController = Lookup.getDefault().lookup(ImportController.class);

        this.graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
        this.graph = graphModel.getUndirectedGraph();

        this.distance = new GraphDistance();

        this.previewController = Lookup.getDefault().lookup(PreviewController.class);
        this.previewModel = previewController.getModel();

        this.ec = Lookup.getDefault().lookup(ExportController.class);
    }

    public void script () {
        import_graph();
        verifyImportation();
        setNodeSize();
        setVizProperties();
        startLayouting();
        centralizeViz();
        setEdgeProperties();
        svgExport();
        gexfExport();
        pngExport();
    }

    public void buildEnv(){
        import_graph();
        verifyImportation();
        setNodeSize();
    }

    public void generateViz(){
        setVizProperties();
        startLayouting();
        centralizeViz();
        setEdgeProperties();
        //svgExport();
        gexfExport();
//        pngExport();
    }

    public void import_graph(){
        try {
            File file = new File(import_file_path);
            container = importController.importFile(file);
            container.getLoader().setEdgeDefault(EdgeDirectionDefault.UNDIRECTED);   //Force DIRECTED
            container.getLoader().setAllowAutoNode(false);  //Don't create missing nodes
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        //Append imported data to GraphAPI
        importController.process(container, new DefaultProcessor(), workspace);
    }

    public void verifyImportation(){
        System.out.println("Nodes: " + graph.getNodeCount());
        System.out.println("Edges: " + graph.getEdgeCount());
    }

    public void setNodeSize(){
        for (Node n: graph.getNodes()) {
            n.setSize(1.0f);
        }
    }

    public void setVizProperties(){

    }

    public void startLayouting(){

    }

    public void centralizeViz() {
        distance.setDirected(true);
        distance.execute(graphModel);
    }

    public void setEdgeProperties(){
        previewModel.getProperties().putValue(PreviewProperty.EDGE_CURVED, Boolean.FALSE);
        previewModel.getProperties().putValue(PreviewProperty.EDGE_THICKNESS, 1.0f);
        previewModel.getProperties().putValue(PreviewProperty.ARROW_SIZE, 0.0f);
    }

    public void svgExport(){
        try {
//            ec.exportFile(new File(export_file_path + ".pdf"));
            ec.exportFile(new File(export_file_path + ".svg"));
            System.out.println("exporting svg file is done.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void gexfExport(){
        GraphExporter graphExporter = (GraphExporter) ec.getExporter("gexf");
        graphExporter.setExportVisible(true);
        graphExporter.setWorkspace(workspace);
        try {
            ec.exportFile(new File(export_file_path + ".gexf"), graphExporter);
            System.out.println("exporting gexf file is done.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void pngExport(){
        PNGExporter pngExporter = (PNGExporter) ec.getExporter("png");
        pngExporter.setWorkspace(workspace);
        try {
            ec.exportFile(new File(export_file_path + ".png"), pngExporter);
//            ec.exportFile(new File(export_file_path + ".pdf"));
            System.out.println("exporting png file is done.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void randomizeLayoutSettings(){

    }

    public void adjustSettingInNeighborhood(int distance){

    }

    public void readConfig(String configDir){

    }

    public void writeConfig(String configDir){

    }

    public double adjustDoubleParam(double param, int distance, double scalar, double max, double min){
        double tempParam;
        double amount = distance * scalar;
        double halfAmount = amount * 0.5;

        do {
            tempParam = param + Math.random() * amount - halfAmount;
        } while((tempParam > max) || (tempParam < min));

        return tempParam;
    }

    public Integer adjustIntParam(Integer param, int distance, Integer scalar, Integer max, Integer min){
        Integer tempParam;
        Integer amount = distance * scalar;
        Random random = new Random();

        do {
            if (random.nextBoolean()) {
                tempParam = param + amount;
            }else {
                tempParam = param - amount;
            }
        } while((tempParam > max) || (tempParam < min));

        return tempParam;
    }

    public Float adjustFloatParam(Float param, int distance, Float scalar, Float max, Float min){
        Float tempParam;
        Float amount = distance * scalar;
        Float halfAmount = amount * 0.5f;
        Random random = new Random();

        do {
            tempParam = param + random.nextFloat() * amount - halfAmount;
        } while((tempParam > max) || (tempParam < min));

        return tempParam;
    }

    public int getNb_steps() {
        return nb_steps;
    }

    public void setNb_steps(int nb_steps) {
        this.nb_steps = nb_steps;
    }

    public String getImport_file_path() {
        return import_file_path;
    }

    public void setImport_file_path(String import_file_path) {
        this.import_file_path = import_file_path;
    }

    public String getExport_file_path() {
        return export_file_path;
    }

    public void setExport_file_path(String export_file_path) {
        this.export_file_path = export_file_path;
    }
}

