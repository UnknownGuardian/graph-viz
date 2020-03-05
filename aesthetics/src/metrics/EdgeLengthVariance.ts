import { Metric } from "./Metric";

export class EdgeLengthVariance extends Metric {
  public calculate(): number {
    let total = 0;
    this.graph.edges.forEach(e => (total += e.distance));
    const averageLength = total / this.graph.edges.length;

    let variance = 0;
    this.graph.edges.forEach(e => {
      variance += (e.distance - averageLength) ** 2;
    });
    variance /= this.graph.edges.length - 1;

    return Math.sqrt(variance);
  }
}
