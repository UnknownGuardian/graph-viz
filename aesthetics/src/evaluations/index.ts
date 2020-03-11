/**
 * Evaluation Definitions
 *
 * An evaluation function accepts a graph and returns a number [0-1]
 */
import { Graph, GraphJSON } from "..";
export type EvaluationFunction = (g: Graph) => Evaluation;
export type GraphEvaluation = {
  graphName: string;
  graph: GraphJSON;
  score: number;
  evaluations: Array<Evaluation>;
};
export type Evaluation = {
  name: string;
  score: number;
  weight: number;
};

// grab a list of the evaluation functions, but toss them into an array.
export * from "./functions";
