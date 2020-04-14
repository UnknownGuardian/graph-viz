import { Metric } from "./Metric";

export class EdgeLengthAverage extends Metric {
  public calculate(): number {
    let total = 0;
    this.graph.edges.forEach((e) => (total += e.distance));
    const averageLength = total / this.graph.edges.length;
    return averageLength;
  }
}
