
import "colors";
import * as path from "path"
import * as commander from "commander";
import * as fs from "fs";
const exec = require("child_process").execSync;


const sleep = (ms: number) => new Promise(resolve => setTimeout(resolve, ms));
async function run() {
  const program = new commander.Command();
  program
    .version("0.1")
    .option("-d, --dir <dir>", "the directory of the graphs to eval")
    .option("-o, --outdir <dir>", "the directory of the graphs to write to")
    .parse(process.argv);

  console.log("Graph Viz Driver".yellow);


  // Generator
  const inputDir = path.join(__dirname, "..", "..", "generator", "input", "data");
  const generationOutputDir = path.join(__dirname, "..", "..", "generator", "output", "data");
  console.log("\nGenerator".green.bold)
  console.log("\t Reading from: \t".yellow, inputDir)
  console.log("\t Writing to: \t".yellow, generationOutputDir);
  console.log("\t TODO: execute program".red.bold);
  await sleep(1000);

  // Change output of Generator to JSON format.
  console.log("\t Converting .gexf to .json".yellow);
  const files = fs.readdirSync(generationOutputDir).filter(f => f.endsWith(".gexf"));
  console.log("\t Running commands:".yellow);
  const gexfToJSONProgram = path.join(__dirname, "..", "..", "generator", "output", "gexf_to_json.js");
  files.forEach(file => {
    const filePath = path.join(generationOutputDir, file);
    const outputFilePath = filePath.replace('.gexf', '.json')
    const convertCommand = `node ${gexfToJSONProgram} ${filePath} > ${outputFilePath}`
    console.log("\t  ", convertCommand)
    exec(convertCommand)
  })



  // Filtration
  const filtrationOutputDir = path.join(__dirname, "..", "..", "aesthetics", "output", "data");
  const filtrationProgram = path.join(__dirname, "..", "..", "aesthetics", "dist", "index.js");

  console.log("\nFiltration".green.bold)
  console.log("\t Reading from:\t".yellow, inputDir)
  console.log("\t Writing to: \t".yellow, filtrationOutputDir);
  console.log("\t Running commands:".yellow);
  const filtrationCommand = `node ${filtrationProgram} -d ${generationOutputDir} -o ${filtrationOutputDir}`
  console.log("\t  ", filtrationCommand)
  exec(filtrationCommand)






  // Run Vue

}


run();
