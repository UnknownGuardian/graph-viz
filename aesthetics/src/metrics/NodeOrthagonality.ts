import { Metric } from "./Metric";
import { gcd } from "../util";

export class NodeOrthagonality extends Metric {
  calculate(): number {
    const transform = this.getGCD();

    const top = this.getTop();
    const bottom = this.getBottom();
    const left = this.getLeft();
    const right = this.getRight();

    let height = Math.floor((top - bottom) / transform);
    let width = Math.floor((right - left) / transform);

    // the availble grid-point intersections
    let a = (width + 1) * (height + 1);

    // fix for small graphs where the compression is so much, too hard to get good
    while (a < this.graph.nodes.length) {
      width++;
      height++;
      a = (width + 1) * (height + 1);
    }

    return this.graph.nodes.length / a;
  }

  private getTop() {
    return Math.max(...this.graph.nodes.map((n) => n.y));
  }
  private getBottom() {
    return Math.min(...this.graph.nodes.map((n) => n.y));
  }
  private getLeft() {
    return Math.min(...this.graph.nodes.map((n) => n.x));
  }
  private getRight() {
    return Math.max(...this.graph.nodes.map((n) => n.x));
  }

  // of the set of vertical and horizontal pixel
  // differences between all geometrically adjacent nodes.
  private getGCD() {
    const differences: number[] = [];
    // or you could just loop through the edges so you don't dupe

    const precision = 1;
    this.graph.edges.forEach((edge) => {
      let x = Math.round(Math.abs(edge.node1.x - edge.node2.x) * precision);
      let y = Math.round(Math.abs(edge.node1.y - edge.node2.y) * precision);

      differences.push(x);
      differences.push(y);
    });

    const noZeroDifferences = differences.filter((x) => x != 0);
    return gcd(noZeroDifferences) / precision;
  }
}
