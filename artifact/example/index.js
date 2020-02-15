//import * as aesthetics from "graph-viz-aesthetics";
// same as
const aesthetics = require("graph-viz-aesthetics");

const someGraph = {
  fileName: "Test Graph",
  nodes: [
    {
      id: 1,
      x: 0,
      y: 0
    },
    {
      id: 2,
      x: 100,
      y: 0
    },
    {
      id: 3,
      x: 100,
      y: 100
    }
  ],
  edges: [
    {
      n1: 1,
      n2: 2
    },
    {
      n1: 2,
      n2: 3
    },
    {
      n1: 3,
      n2: 1
    }
  ]
};

const graphJSON = [someGraph];
const graphs = aesthetics.preprocess(graphJSON);
const res = aesthetics.evaluate(graphs);
console.log("Success Evaluating:", JSON.stringify(res, null, " "));
