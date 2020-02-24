import { Metric } from "./Metric";

export class UpwardFlow extends Metric {
  calculate(): number {
    return this.getFlow();
  }

  private getFlow() {
    let total = 0;
    const direction = { x: 0, y: 1 }
    this.graph.edges.forEach(edge => {
      // dot product
      const edgeVector = {
        x: edge.node1.x - edge.node2.x,
        y: edge.node1.y - edge.node2.y
      }

      //console.log("EDGE:", edge.node1.id, "--", edge.node2.id, "is ", edgeVector)

      // don't care about direction (up vs. down)
      if (Math.abs(edgeVector.y) > 0) {
        total++;
      }
    })

    return total / this.graph.edges.length
  }

}
