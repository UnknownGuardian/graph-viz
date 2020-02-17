/**
 * Evaluation Definitions
 *
 * An evaluation function accepts a graph and returns a number [0-1]
 */
import { Graph } from "..";
export type EvaluationFunction = (g: Graph) => number;
export type GraphEvaluation = {
  graphName: string;
  evaluations: Array<Evaluation>;
};
export type Evaluation = { func: string; score: number };

// grab a list of the evaluation functions, but toss them into an array.
import * as funcs from "./functions";
const evalFunctions: EvaluationFunction[] = Object.values(funcs);
export { evalFunctions };
