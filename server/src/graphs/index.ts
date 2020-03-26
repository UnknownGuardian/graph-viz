import * as path from "path";
import * as chokidar from "chokidar";
import * as fs from "fs";
import "colors";
const exec = require("child_process").execSync;

const generator = path.join(__dirname, "..", "..", "..", "generator");
const aesthetics = path.join(__dirname, "..", "..", "..", "aesthetics");
const inputGraph = path.join(generator, "input", "graph_43_nodes.gexf");
const generationOutputDir = path.join(generator, "output");
const aestheticsOutputDir = path.join(generator, "scores");
const generatorProgram = path.join(
  generator,
  "gephi_generator",
  "out",
  "artifacts",
  "gephi_generator_jar",
  "gephi_generator.jar"
);

const sleep = (ms: number) => new Promise(resolve => setTimeout(resolve, ms));

function list(req: any, res: any) {
  let graphs = Object.values(cache);
  graphs.sort(alphabeticalSort);

  if (req.query.format != "legacy") {
    graphs = mapToUIFormat(graphs);
  }

  // hack for pretty print
  res.header("Content-Type", "application/json");
  res.send(JSON.stringify(graphs, null, 4));
}

async function remake(req: any, res: any) {
  // remake new viz
  const viz = req.query.graphName;
  const algorithm = viz.split(".json")[0];
  if (!["fa2", "fa", "ft", "yf", "oo", "all"].includes(algorithm)) {
    res.header("Content-Type", "application/json");
    res.json({ error: "No such algorithm" });
  }

  const cmd = `java -jar ${generatorProgram} ${inputGraph} ${generationOutputDir} ${algorithm}`;
  exec(cmd);

  // do gexf->json
  convert();

  //score
  score();

  // sleep just a little to let the watcher pick up files
  await sleep(100);

  //serve
  const path: string =
    Object.keys(cache).find(key => key.endsWith(algorithm + ".json")) || "";
  const graph = cache[path];
  const data = mapToVizFormat(graph);
  res.header("Content-Type", "application/json");
  res.send(JSON.stringify(data, null, 4));
}

function score() {
  const filtrationOutputDir = path.join(generator, "scores");
  const filtrationProgram = path.join(aesthetics, "dist", "index.js");
  const filterUnder = 0.5;

  console.log("\nFiltration".green.bold);
  console.log("\t Reading from:\t".yellow, generationOutputDir);
  console.log("\t Writing to: \t".yellow, filtrationOutputDir);
  console.log(`\t Filtering graphs that score below: \t`.yellow, filterUnder);
  console.log("\t Running commands:".yellow);
  const filtrationCommand = `node ${filtrationProgram} -d ${generationOutputDir} -o ${filtrationOutputDir} -t ${filterUnder}`;
  console.log("\t  ", filtrationCommand);
  exec(filtrationCommand);
}

function convert() {
  const files = fs
    .readdirSync(generationOutputDir)
    .filter(f => f.endsWith(".gexf"));
  console.log("\t Running commands:".yellow);
  const gexfToJSONProgram = path.join(
    generator,
    "scripts",
    "converter",
    "gexf_to_json.js"
  );
  files.forEach(file => {
    const filePath = path.join(generationOutputDir, file);
    const outputFilePath = filePath.replace(".gexf", ".json");
    const convertCommand = `node ${gexfToJSONProgram} ${filePath} > ${outputFilePath}`;
    console.log("\t  ", convertCommand);
    exec(convertCommand);
  });
}

function mapToUIFormat(graphs: any[]): any {
  const first = graphs[0];
  let obj: any = {};
  obj.graph = {
    nodes: first.graph.nodes.map((n: any) => ({ id: n.id })),
    edges: first.graph.edges.slice()
  };
  obj.visualizations = graphs.map(mapToVizFormat);
  return obj;
}
function mapToVizFormat(g: any): any {
  return {
    graphName: g.graphName,
    score: g.score,
    evaluations: g.evaluations,
    points: g.graph.nodes.map((n: any) => ({ id: n.id, x: n.x, y: n.y }))
  };
}
function alphabeticalSort(a: any, b: any) {
  if (a.graphName < b.graphName) return -1;
  if (a.graphName > b.graphName) return 1;
  return 0;
}

const cache: any = {};
function watch() {
  console.log("Watching for changes in", aestheticsOutputDir);
  chokidar
    .watch(aestheticsOutputDir, { usePolling: true })
    .on("unlink", path => delete cache[path])
    .on("add", path => (cache[path] = require(path)))
    .on("change", path => (cache[path] = require(path)));
}

export const graphs = {
  list,
  watch,
  remake
};
