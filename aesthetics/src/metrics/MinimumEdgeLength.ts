import { Metric } from "./Metric";

export class MinimumEdgeLength extends Metric {
  public calculate(): number {
    return Math.min(...this.graph.edges.map(e => e.distance));
  }
}
