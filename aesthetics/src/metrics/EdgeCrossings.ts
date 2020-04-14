import { Metric } from "./Metric";
import "colors";

export class EdgeCrossings extends Metric {
  calculate(): number {
    let crossings = 0;
    let total = 0;
    let total2 = this.getMaxCrossingApprox();
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
    console.log(
      `EDGE CROSSINGS IS ${crossings} / ${total} OR ${total2} WHICH IS ${crossings} / ${total} OR ${crossings} / ${total2}`
    );
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

  getMaxCrossingApprox() {
    const edges = this.makeCompleGraph();
    const points = this.createRadialVizualization();
    const radialCrossings = this.getRadialCrossings(edges, points);
    return radialCrossings;
  }

  makeCompleGraph(): SEdge[][] {
    const edges: SEdge[][] = [];
    this.graph.nodes.forEach((n) => {
      const arr: SEdge[] = [];
      this.graph.nodes.forEach((n2) => arr.push({ index: 0, weight: 1 }));
      edges.push(arr);
    });
    return edges;
  }

  createRadialVizualization(): CGPoint[] {
    const nodes = this.graph.nodes;
    const points: CGPoint[] = [];
    let degree = 360 / nodes.length;
    let r = 100;
    let offset = 0;

    for (let i = 0; i <= 360; i += degree) {
      let radians = ((i + offset) * Math.PI) / 180;
      let x = r * Math.cos(radians);
      let y = r * Math.sin(radians);
      points.push({ x, y });
    }

    return points;
  }

  getRadialCrossings(edges: SEdge[][], points: CGPoint[]): number {
    let crossings = 0;
    for (let x = 0; x < edges.length - 1; x++) {
      const e = this.graph.edges[x];
      for (let i = x + 1; i < edges.length; i++) {
        const e2 = edges[i];

        const sharesNode =
          e.node1 == e2.node1 ||
          e.node1 == e2.node2 ||
          e.node2 == e2.node1 ||
          e.node2 == e2.node2;

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

        if (crosses && !sharesNode) {
          crossings++;
        }
      }
    }
    return crossings;
  }
}

type SEdge = {
  index: number;
  weight: number;
};

type CGPoint = {
  x: number;
  y: number;
};
