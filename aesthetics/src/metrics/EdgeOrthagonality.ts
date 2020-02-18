import { Metric } from "./Metric";
import { angleOfEdge } from "../util";
import { Edge } from "../";
import "colors";

export class EdgeOrthagonality extends Metric {
  calculate(): number {
    return 1 - this.getD();
  }

  private getD() {
    let total = 0;

    for (let i = 0; i < this.graph.edges.length; i++) {
      const edge = this.graph.edges[i];
      total += this.angularDeviationFromHorizonalOrVertical(edge);
    }

    return total / this.graph.edges.length;
  }

  private angularDeviationFromHorizonalOrVertical(e: Edge): number {
    const theta = angleOfEdge(e.node1, e.node2);
    const deviationFactor = [theta, Math.abs(90 - theta), 180 - theta];
    return Math.min(...deviationFactor) / 45;
  }
}
