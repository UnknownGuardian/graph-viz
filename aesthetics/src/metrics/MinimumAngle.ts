import { Metric } from "./Metric";
import { angleBetweenEdges } from "../util";
import { Node } from "../";

export class MinimumAngle extends Metric {
  calculate(): number {
    return 1 - this.getD();
  }

  private getD() {
    let total = 0;

    this.graph.nodes.map(node => {
      const ideal = this.idealMinimumAngle(node);
      const actual = this.actualMinumumAngle(node);
      total += (ideal - actual) / ideal;
    });

    return total / this.graph.nodes.length;
  }

  private idealMinimumAngle(node: Node): number {
    return 360 / node.degree;
  }
  private actualMinumumAngle(node: Node): number {
    const connectedNodes = node.connectedEdges.map(edge =>
      edge.node1 == node ? edge.node2 : edge.node1
    );
    let smallest = 360;

    for (let i = 0; i < connectedNodes.length - 1; i++) {
      const current = connectedNodes[i];
      for (let x = i + 1; x < connectedNodes.length; x++) {
        const other = connectedNodes[x];
        smallest = Math.min(smallest, angleBetweenEdges(node, current, other));
      }
    }
    return smallest;
  }
}
