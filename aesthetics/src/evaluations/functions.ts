import { Graph } from "..";
import {
  MinimumAngle,
  EdgeOrthagonality,
  NodeOrthagonality,
  UpwardFlow
} from "../metrics";
import { EvaluationFunction, Evaluation } from ".";

/** Just randomly give a [0-1] return */
/*export const random: EvaluationFunction = function random(g: Graph): number {
  return Math.random();
};*/

/** Check if there is a threshold number of edges */
/*export const thresholdEdgeCount: EvaluationFunction = (
  g: Graph
): Evaluation => {
  const THRESHOLD = g.edges.length;
  const length = g.edges.length;
  return {
    name: "Edge Threshold",
    score: length / THRESHOLD,
    showEval: false,
    metric: {
      name: "Number of Edges",
      value: length
    }
  };
};*/

/*export const smallEdge: EvaluationFunction = (g: Graph): Evaluation => {
  const THRESHOLD = 100;
  const maxEdgeLength = new MaximumEdgeLength(g).calculate();
  let score = 1;
  if (maxEdgeLength > THRESHOLD) score = 0;
  else score = maxEdgeLength / THRESHOLD;

  return {
    name: "Has short edges",
    score,
    showEval: false,
    metric: {
      name: "Longest Edge",
      value: maxEdgeLength
    }
  };
};*/

export const minimumAngle: EvaluationFunction = (g: Graph): Evaluation => {
  const minAngle = new MinimumAngle(g).calculate();
  return {
    name: "Minimum Angle",
    score: 1 - minAngle,
    metric: {
      name: "Min Angle",
      value: minAngle
    }
  };
};

export const edgeOrth: EvaluationFunction = (g: Graph): Evaluation => {
  const orth = new EdgeOrthagonality(g).calculate();
  return {
    name: "Edge Orthagonality",
    score: orth,
    metric: {
      name: "Edge Orthagonality",
      value: orth
    }
  };
};

export const nodeOrth: EvaluationFunction = (g: Graph): Evaluation => {
  const orth = new NodeOrthagonality(g).calculate();
  return {
    name: "Node Orthagonality",
    score: orth,
    metric: {
      name: "Node Orthagonality",
      value: orth
    }
  };
};

export const upwardFlow: EvaluationFunction = (g: Graph): Evaluation => {
  const flow = new UpwardFlow(g).calculate();
  return {
    name: "Upward Flow",
    score: flow,
    metric: {
      name: "Upward Flow",
      value: flow
    }
  };
};

/*export const edgeLength: EvaluationFunction = (g: Graph): Evaluation => {
  const len = new TotalEdgeLength(g).calculate();
  return {
    name: "Total Edge Length",
    score: len,
    metric: {
      name: "Total Edge Length",
      value: len
    }
  };
};
*/