import { Graph } from "..";
import { MinimumAngle, MaximumEdgeLength } from "../metrics";
import { EvaluationFunction } from ".";

/** Just randomly give a [0-1] return */
export const random: EvaluationFunction = function random(g: Graph): number {
  return Math.random();
};

/** Check if there is a threshold number of edges */
export const thresholdEdgeCount: EvaluationFunction = function thresholdEdgeCount(
  g: Graph
): number {
  const THRESHOLD = 5;
  const length = g.edges.length;
  if (length > THRESHOLD) return 0;
  return length / THRESHOLD;
};

export const smallEdge: EvaluationFunction = function smallEdge(
  g: Graph
): number {
  const THRESHOLD = 15;
  const maxEdgeLength = new MaximumEdgeLength(g).calculate();
  if (maxEdgeLength > THRESHOLD) return 0;
  return maxEdgeLength / THRESHOLD;
};

export const minimumAngle: EvaluationFunction = function minimumAngle(
  g: Graph
): number {
  const minAngle = new MinimumAngle(g).calculate();
  return 1 - minAngle;
};
