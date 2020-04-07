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
import org.gephi.layout.plugin.forceAtlas.ForceAtlasLayout;
import org.gephi.layout.plugin.fruchterman.FruchtermanReingold;
import org.gephi.preview.api.PreviewController;
import org.gephi.preview.api.PreviewModel;
import org.gephi.preview.api.PreviewProperty;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.gephi.statistics.plugin.GraphDistance;
import org.openide.util.Lookup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Random;

public class FruchtermanGen extends Generation {

    private Double gravity;

    private FruchtermanReingold layout;

    public FruchtermanGen(int nb_steps, String import_file_path, String export_file_path, Double gravity) {
        super(nb_steps, import_file_path, export_file_path);
        this.gravity = gravity;

        this.layout = new FruchtermanReingold(null);
        this.layout.setGraphModel(graphModel);
        this.layout.resetPropertiesValues();
    }

    @Override
    public void setVizProperties(){
        layout.setArea((float) graph.getNodeCount() * 10);
        layout.setGravity(gravity);
    }

    @Override
    public void startLayouting(){
        layout.initAlgo();
        for (int i = 0; i < nb_steps; i++) {
            layout.goAlgo();
        }
        layout.endAlgo();
    }

    @Override
    public void randomizeLayoutSettings(){
        Random rd = new Random();
        this.setGravity(rd.nextDouble() * 300);
    }

    @Override
    public void adjustSettingInNeighborhood(int distance){
        double temp_gravity = getGravity();
        temp_gravity = adjustDoubleParam(temp_gravity, distance, 0.1, 300.0, 0.0);
        setGravity(temp_gravity);
    }

    @Override
    public void readConfig() {
        String directory = "config/ft.txt";
        Path configPath = Paths.get(directory);
        String config = null;

        try {
            config = Files.readString(configPath);
        } catch (IOException e) {
            System.out.println("an error occurred during reading Fruchterman configuration file.");
            e.printStackTrace();
        }

        FruchtermanConfig fruchtermanConfig = JsonDecoder.decode(config, FruchtermanConfig.class);

        setGravity(fruchtermanConfig.getGravity());
    }

    @Override
    public void writeConfig() {
        FruchtermanConfig fruchtermanConfig = new FruchtermanConfig(this);
        String config = JsonEncoder.encode(fruchtermanConfig);

        String directory = "config/ft.txt";
        Path configPath = Paths.get(directory);

        try {
            Files.write(configPath, Collections.singleton(config));
        } catch (IOException e) {
            System.out.println("an error occurred during writing Fruchterman configuration file.");
            e.printStackTrace();
        }
    }

    public Double getGravity() {
        return gravity;
    }

    public void setGravity(Double gravity) {
        this.gravity = gravity;
    }
}
