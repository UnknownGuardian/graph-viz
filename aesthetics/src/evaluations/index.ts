/**
 * Evaluation Definitions
 *
 * An evaluation function accepts a graph and returns a number [0-1]
 */
import { Graph } from "..";
export type EvaluationFunction = (g: Graph) => Evaluation;
export type GraphEvaluation = {
  graphName: string;
  evaluations: Array<Evaluation>;
};
export type Evaluation = {
  name: string;
  score: number;
  metric?: {
    name: string;
    value: number;
  };
  [key: string]: any;
};

// grab a list of the evaluation functions, but toss them into an array.
import * as funcs from "./functions";
const evalFunctions: EvaluationFunction[] = Object.values(funcs);
export { evalFunctions };
