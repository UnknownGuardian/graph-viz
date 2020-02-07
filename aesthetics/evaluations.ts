import { Graph } from ".";
import { maximumEdgeLength } from "./metrics";

/**
 * Evaluation Definitions
 * 
 * An evaluation function accepts a graph and returns a number [0-1]
 */
export type EvaluationFunction = (g: Graph) => number;
export type GraphEvaluation = { graphName: string; evaluations: Array<Evaluation> }
export type Evaluation = { func: string, score: number };


/** Just randomly give a [0-1] return */
export const randomEval: EvaluationFunction = function randomEval(g: Graph): number {
  return Math.random();
}

/** Check if there is a threshold number of edges */
export const thresholdEdgeCountEval: EvaluationFunction = function thresholdEdgeCountEval(g: Graph): number {
  const THRESHOLD = 5;
  const length = g.edges.length;
  if (length > THRESHOLD)
    return 0;
  return length / THRESHOLD;
}

export const smallEdgeEval: EvaluationFunction = function smallEdgeEval(g: Graph): number {
  const THRESHOLD = 15;
  const maxEdgeLength = maximumEdgeLength(g);
  if (maxEdgeLength > THRESHOLD)
    return 0;
  return maxEdgeLength / THRESHOLD;
}


