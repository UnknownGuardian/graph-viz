import * as fs from "fs";
import * as path from "path";
const express = require("express");
const app = express();
const port = 3000;

const aestheticsOutputDir = path.join(
  __dirname,
  "..",
  "..",
  "generator",
  "output",
  "scores"
);

app.get("/", (req: any, res: any) => {
  res.send("You probably should hit <a href='/graphs'>/graphs</a>");
});
app.get("/graphs", (req: any, res: any) => {
  const files = fs.readdirSync(aestheticsOutputDir);
  let graphs = files.map(f => require(path.join(aestheticsOutputDir, f)));

  if (req.query.format != "legacy") {
    graphs = mapToUIFormat(graphs);
  }

  // hack for pretty print
  res.header("Content-Type", "application/json");
  res.send(JSON.stringify(graphs, null, 4));
  //res.json(graphs);
});

app.listen(port, () =>
  console.log(`Graph Viz Server listening on port ${port}!`)
);

function mapToUIFormat(graphs: any[]): any {
  const first = graphs[0];
  let obj: any = {};
  obj.graph = {
    nodes: first.graph.nodes.map((n: any) => ({ id: n.id })),
    edges: first.graph.edges.slice()
  };
  obj.visualizations = graphs.map(g => ({
    graphName: g.graphName,
    score: g.score,
    evaluations: g.evaluations,
    points: g.graph.nodes.map((n: any) => ({ x: n.x, y: n.y }))
  }));
  return obj;
}
