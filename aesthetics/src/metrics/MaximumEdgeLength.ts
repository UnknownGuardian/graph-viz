import { Metric } from "./Metric";

export class MaximumEdgeLength extends Metric {
  public calculate(): number {
    return Math.max(...this.graph.edges.map(e => e.distance));
  }
}
