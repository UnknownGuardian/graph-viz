const fs = require("fs");
const parser = require("xml2json");

if (!process.argv[2]) {
  throw new Error("Need the path to the graph you are going to change");
}
const xml = fs.readFileSync(process.argv[2], "UTF-8");
const json = JSON.parse(parser.toJson(xml));

const graph = json.gexf.graph;
const nodes = graph.nodes.node;
const edges = graph.edges.edge;

const obj = {
  nodes: nodes.map(node => ({
    id: node.id,
    x: parseFloat(node["viz:position"].x),
    y: parseFloat(node["viz:position"].y)
  })),
  edges: edges.map(edge => ({
    source: edge.source,
    target: edge.target
  }))
};

console.log(JSON.stringify(obj, null, "  "));
