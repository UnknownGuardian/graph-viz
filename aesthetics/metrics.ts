import { Graph } from ".";

/**
 * Metric Definitions
 * 
 * Helper functions to calculate some statistic about the graph.
 */
export type MetricFunction = (g: Graph) => any;



export const edgeLength: MetricFunction = function edgeLength(g: Graph): Number {
  return g.edges.reduce((current, edge) => current + edge.distance, 0);
}

export const maximumEdgeLength: MetricFunction = function maximumEdgeLength(g: Graph): Number {
  return Math.max(...g.edges.map(e => e.distance));
}

