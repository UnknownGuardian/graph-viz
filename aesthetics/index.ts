/**
 * The data structure of the graphs as they are imported from JSON
 */
export type GraphJSON = {
  fileName: string;
  nodes: Array<{ id: number; x: number; y: number }>
  edges: Array<{ n1: number; n2: number; }>
}


/**
 * Graph Definitions
 * 
 * A graph is made up of an array of nodes and edges
 * Nodes have an id, x, y
 * Edges have the ids of both nodes they connect
 *  
 */

export type Graph = {
  name: string;
  nodes: Array<Node>;
  edges: Array<Edge>
}
export type Node = {
  id: number;
  x: number;
  y: number;
}
export type Edge = {
  node1: Node;
  node2: Node;
  distance: number;
}










function createGraphsFromDir(dir: string): Array<Graph> {
  const actualDir = path.join(__dirname, '..', dir)
  console.log("\tLoading from ".green, actualDir)

  const files = fs.readdirSync(actualDir);
  console.log(`\tFound ${files.length} graph(s)`.green);

  // load them into memory
  const data: Array<GraphJSON> = files.map(file => {
    const d = require(path.join(actualDir, file));
    return { ...d, fileName: file }
  });

  const graphs = preprocess(data);
  console.log(`\tPreprocessed ${files.length} graph(s)`.green);
  return graphs;
}

function preprocess(graphs: Array<GraphJSON>): Array<Graph> {
  return graphs.map(g => {
    const parsed: Graph = {
      name: g.fileName,
      nodes: g.nodes.map(node => ({ ...node })), // shallow copy
      edges: []
    };
    parsed.edges = g.edges.map(edge => {
      const node1: Node = parsed.nodes.find(node => node.id == edge.n1) as Node
      const node2: Node = parsed.nodes.find(node => node.id == edge.n2) as Node
      const distance = distanceBetweenNodes(node1, node2);
      return { node1, node2, distance };
    })
    return parsed;
  })
}

function evaluate(g: Array<Graph>): Array<GraphEvaluation> {
  const exportedEvaluations: Array<Function> = Object.values(require("./evaluations"));
  const functions: Array<any> = exportedEvaluations.filter((x: Function) => x.name.endsWith('Eval'));
  console.log(`\tEvaluating graphs with ${functions.length} evaluator(s)`.green)
  return g.map((graph): GraphEvaluation => {
    return {
      graphName: graph.name,
      evaluations: functions.map((evalFunction): Evaluation => ({
        func: evalFunction.name,
        score: evalFunction(graph)
      }))
    }
  })
}

function writeEvaluations(dir: string, e: Array<GraphEvaluation>) {
  const actualDir = path.join(__dirname, '..', dir)
  console.log("\tWriting to".green, actualDir)
  e.forEach(evaluation => {
    fs.writeFileSync(path.join(actualDir, evaluation.graphName), JSON.stringify(evaluation, null, '  '));
  })
  console.log(`\tWrote ${e.length} evaluations(s)`.green);
}





import "colors";
import * as fs from "fs";
import * as path from "path"
import * as commander from "commander";
import { distanceBetweenNodes } from "./util";
import { Evaluation, GraphEvaluation } from "./evaluations"


const program = new commander.Command();
program
  .version('0.1')
  .option('-d, --dir <dir>', 'the directory of the graphs to eval')
  .option('-o, --outdir <dir>', 'the directory of the graphs to write to')
  .parse(process.argv);

console.log("Aesthetics Evaluation".yellow);

const graphs = createGraphsFromDir(program.dir);
const evaluations = evaluate(graphs);
writeEvaluations(program.outdir, evaluations)