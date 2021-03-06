import { Metric } from "./Metric";
import "colors";
import { Graph, Node } from "..";

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
    /*console.log(
      `EDGE CROSSINGS IS ${crossings} / ${total} OR ${total2} WHICH IS ${crossings} / ${total} OR ${crossings} / ${total2}`
    );*/
    return crossings / total2;
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
    this.createRadialVizualization();
    return this.getRadialCrossings();
  }

  createRadialVizualization(): void {
    // clone, redistribute points
    const nodes = this.graph.nodes as any[];

    let degree = 360 / nodes.length;
    let r = 100;
    let offset = 0;

    let index = 0;
    for (let i = 0; i <= 360; i += degree) {
      let radians = ((i + offset) * Math.PI) / 180;
      let x = r * Math.cos(radians);
      let y = r * Math.sin(radians);
      nodes[index]["x2"] = x;
      nodes[index]["y2"] = y;
      index++;
    }
  }

  getRadialCrossings(): number {
    let crossings = 0;
    for (let x = 0; x < this.graph.edges.length - 1; x++) {
      const e = this.graph.edges[x];
      for (let i = x + 1; i < this.graph.edges.length; i++) {
        const e2 = this.graph.edges[i];

        const sharesNode =
          e.node1 == e2.node1 ||
          e.node1 == e2.node2 ||
          e.node2 == e2.node1 ||
          e.node2 == e2.node2;

        const crosses: boolean = this.intersects(
          (e.node1 as any)["x2"],
          (e.node1 as any)["y2"],
          (e.node2 as any)["x2"],
          (e.node2 as any)["y2"],
          (e2.node1 as any)["x2"],
          (e2.node1 as any)["y2"],
          (e2.node2 as any)["x2"],
          (e2.node2 as any)["y2"]
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
