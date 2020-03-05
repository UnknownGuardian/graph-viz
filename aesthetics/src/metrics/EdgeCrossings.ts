import { Metric } from "./Metric";
import "colors";

export class EdgeCrossings extends Metric {
  calculate(): number {
    let crossings = 0;
    let total = 0;
    for (let x = 0; x < this.graph.edges.length - 1; x++) {
      const e = this.graph.edges[x];
      for (let i = x + 1; i < this.graph.edges.length; i++) {
        const e2 = this.graph.edges[i];

        const crosses: boolean = this.intersects(
          e.node1.x,
          e.node1.y,
          e.node2.x,
          e.node2.y,
          e2.node1.x,
          e2.node1.y,
          e2.node2.x,
          e2.node2.y
        );

        if (crosses) {
          crossings++;
        }
        total++;
      }
    }
    return crossings / total;
  }

  // returns true iff the line from (a,b)->(c,d) intersects with (p,q)->(r,s)
  intersects(
    a: number,
    b: number,
    c: number,
    d: number,
    p: number,
    q: number,
    r: number,
    s: number
  ) {
    var det, gamma, lambda;
    det = (c - a) * (s - q) - (r - p) * (d - b);
    if (det === 0) {
      return false;
    } else {
      lambda = ((s - q) * (r - a) + (p - r) * (s - b)) / det;
      gamma = ((b - d) * (r - a) + (c - a) * (s - b)) / det;
      return 0 < lambda && lambda < 1 && 0 < gamma && gamma < 1;
    }
  }
}
