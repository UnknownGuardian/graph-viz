import { Graph } from "..";
import {
  MinimumAngle,
  EdgeOrthagonality,
  NodeOrthagonality,
  UpwardFlow,
  EdgeCrossings,
  MinimumEdgeLength,
  EdgeLengthVariance,
  EdgeLengthAverage,
} from "../metrics";
import { EvaluationFunction, Evaluation } from ".";

export const edgeCrossings: EvaluationFunction = (g: Graph): Evaluation => {
  const crossings = new EdgeCrossings(g).calculate();
  return {
    name: "Edge Crossings",
    score: 1 - crossings,
    weight: 0.29,
  };
};

export const minEdgeLength: EvaluationFunction = (g: Graph): Evaluation => {
  const threshold = 10;
  const value = new MinimumEdgeLength(g).calculate();
  const score = value < threshold ? 1 : 0;
  return {
    name: "Minimum Edge Length",
    score,
    weight: 0.1,
    value,
  };
};
export const averageEdgeLength: EvaluationFunction = (g: Graph): Evaluation => {
  // https://www.analyzemath.com/parabola/three_points_para_calc.html
  // want around 100
  const threshold = 10;
  const value = new EdgeLengthAverage(g).calculate();
  const func = Math.min(1, 0.0001 * value ** 2 - 0.02 * value + 1);
  const score = 1 - func;
  console.log("AVERAGE EDGE LENGTH", value, score);
  return {
    name: "Average Edge Length",
    score,
    weight: 0.19,
    value,
  };
};
export const edgeLengthVariance: EvaluationFunction = (
  g: Graph
): Evaluation => {
  const value = new EdgeLengthVariance(g).calculate();
  const score = Math.max(0, 1 - value / 50); // 1 / value;
  return {
    name: "Edge Length Variance",
    score,
    weight: 0.2,
    value,
  };
};
export const minimumAngle: EvaluationFunction = (g: Graph): Evaluation => {
  const minAngle = new MinimumAngle(g).calculate();
  return {
    name: "Minimum Angle",
    score: 1 - minAngle,
    weight: 0.1,
  };
};

export const edgeOrth: EvaluationFunction = (g: Graph): Evaluation => {
  const orth = new EdgeOrthagonality(g).calculate();
  return {
    name: "Edge Orthagonality",
    score: orth,
    weight: 0.1,
  };
};

export const nodeOrth: EvaluationFunction = (g: Graph): Evaluation => {
  const orth = new NodeOrthagonality(g).calculate();
  return {
    name: "Node Orthagonality",
    score: orth,
    weight: 0.01,
  };
};

export const upwardFlow: EvaluationFunction = (g: Graph): Evaluation => {
  const flow = new UpwardFlow(g).calculate();
  return {
    name: "Upward Flow",
    score: flow,
    weight: 0.01,
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
