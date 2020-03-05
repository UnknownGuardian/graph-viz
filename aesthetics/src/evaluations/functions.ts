import { Graph } from "..";
import {
  MinimumAngle,
  EdgeOrthagonality,
  NodeOrthagonality,
  UpwardFlow,
  EdgeCrossings,
  MinimumEdgeLength,
  EdgeLengthVariance
} from "../metrics";
import { EvaluationFunction, Evaluation } from ".";

export const minEdgeLength: EvaluationFunction = (g: Graph): Evaluation => {
  const threshold = 5;
  const value = new MinimumEdgeLength(g).calculate();
  const score = value > threshold ? 1 : 0;
  return {
    name: "Minimum Edge Length",
    score,
    weight: 0.1
  };
};
export const edgeLengthVariance: EvaluationFunction = (
  g: Graph
): Evaluation => {
  const value = new EdgeLengthVariance(g).calculate();
  const score = 1 / value;
  return {
    name: "Minimum Edge Length",
    score,
    weight: 0.1
  };
};
export const minimumAngle: EvaluationFunction = (g: Graph): Evaluation => {
  const minAngle = new MinimumAngle(g).calculate();
  return {
    name: "Minimum Angle",
    score: 1 - minAngle,
    weight: 0.1
  };
};

export const edgeOrth: EvaluationFunction = (g: Graph): Evaluation => {
  const orth = new EdgeOrthagonality(g).calculate();
  return {
    name: "Edge Orthagonality",
    score: orth,
    weight: 0.1
  };
};

export const nodeOrth: EvaluationFunction = (g: Graph): Evaluation => {
  const orth = new NodeOrthagonality(g).calculate();
  return {
    name: "Node Orthagonality",
    score: orth,
    weight: 0.2
  };
};

export const upwardFlow: EvaluationFunction = (g: Graph): Evaluation => {
  const flow = new UpwardFlow(g).calculate();
  return {
    name: "Upward Flow",
    score: flow,
    weight: 0.01
  };
};
export const edgeCrossings: EvaluationFunction = (g: Graph): Evaluation => {
  const crossings = new EdgeCrossings(g).calculate();
  return {
    name: "Edge Crossings",
    score: 1 - crossings,
    weight: 0.39
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
