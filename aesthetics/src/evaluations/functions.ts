import { Graph } from "..";
import { MinimumAngle, MaximumEdgeLength } from "../metrics";
import { EvaluationFunction, Evaluation } from ".";

/** Just randomly give a [0-1] return */
/*export const random: EvaluationFunction = function random(g: Graph): number {
  return Math.random();
};*/

/** Check if there is a threshold number of edges */
export const thresholdEdgeCount: EvaluationFunction = (
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
};

export const smallEdge: EvaluationFunction = (g: Graph): Evaluation => {
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
};

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
