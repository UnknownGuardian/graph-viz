import "colors";
import * as path from "path";
import * as commander from "commander";
import * as fs from "fs";
const exec = require("child_process").execSync;
import { emptyDirSync } from "fs-extra";

const sleep = (ms: number) => new Promise((resolve) => setTimeout(resolve, ms));
async function run() {
  const program = new commander.Command();
  program
    .version("0.2")
    .option("-g, --graph <gexf file>", "the directory of the graphs to eval")
    .parse(process.argv);

  console.log("Graph Viz Driver".yellow);

  // Generator
  const generator = path.join(__dirname, "..", "..", "generator");
  const inputGraph =
    program.graph || path.join(generator, "input", "graph_43_nodes.gexf");
  const generationOutputDir = path.join(generator, "output");
  const generationConfigDir = path.join(generator, "gephi_generator", "config");

  if (!fs.existsSync(inputGraph)) {
    throw new Error(`No such graph bro exists at ${inputGraph}`);
  }
  //emptyDirSync(generationOutputDir);

  const generatorProgram = path.join(
    generator,
    "gephi_generator",
    "out",
    "artifacts",
    "gephi_generator_jar",
    "gephi_generator.jar"
  );
  const gephiCommand = `java -jar ${generatorProgram} ${inputGraph} ${generationOutputDir} ${generationConfigDir}`;
  console.log("\nGenerator".green.bold);
  console.log("\t Reading graph: \t".yellow, inputGraph);
  console.log("\t Writing to: \t\t".yellow, generationOutputDir);
  console.log("\t Running commands:\t".yellow, gephiCommand);
  exec(gephiCommand);
  await sleep(1000);

  // Change output of Generator to JSON format.
  console.log("\t Converting .gexf to .json".yellow);
  const files = fs
    .readdirSync(generationOutputDir)
    .filter((f) => f.endsWith(".gexf"));
  console.log("\t Running commands:".yellow);
  const gexfToJSONProgram = path.join(
    generator,
    "scripts",
    "converter",
    "gexf_to_json.js"
  );
  files.forEach((file) => {
    const filePath = path.join(generationOutputDir, file);
    const outputFilePath = filePath.replace(".gexf", ".json");
    const convertCommand = `node ${gexfToJSONProgram} ${filePath} > ${outputFilePath}`;
    console.log("\t  ", convertCommand);
    exec(convertCommand);
  });

  // Filtration
  const filtrationOutputDir = path.join(generator, "scores");
  const filtrationProgram = path.join(
    __dirname,
    "..",
    "..",
    "aesthetics",
    "dist",
    "index.js"
  );
  const filterUnder = 0.2;

  //emptyDirSync(filtrationOutputDir);

  console.log("\nFiltration".green.bold);
  console.log("\t Reading from:\t".yellow, generationOutputDir);
  console.log("\t Writing to: \t".yellow, filtrationOutputDir);
  console.log(`\t Filtering graphs that score below: \t`.yellow, filterUnder);
  console.log("\t Running commands:".yellow);
  const filtrationCommand = `node ${filtrationProgram} -d ${generationOutputDir} -o ${filtrationOutputDir} -t ${filterUnder}`;
  console.log("\t  ", filtrationCommand);
  exec(filtrationCommand);
}

run();
