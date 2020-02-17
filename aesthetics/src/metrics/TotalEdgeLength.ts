import { Metric } from "./Metric";

export class TotalEdgeLength extends Metric {
  public calculate(): number {
    return this.graph.edges.reduce(
      (current, edge) => current + edge.distance,
      0
    );
  }
}
