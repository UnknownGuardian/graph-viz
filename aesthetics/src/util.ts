import { Node, Edge } from ".";

export function distanceBetweenNodes(n1: Node, n2: Node): number {
  return Math.sqrt((n1.x - n2.x) ** 2 + (n1.y - n2.y) ** 2);
}
export function degreeOfNode(edges: Array<Edge>, node: Node): number {
  return connectedEdges(edges, node).length;
}
export function connectedEdges(edges: Array<Edge>, node: Node): Array<Edge> {
  return edges.filter(e => e.node1 == node || e.node2 == node);
}

// in degrees, always positive between 0 and 180.
export function angleBetweenEdges(
  pivot: Node,
  node1: Node,
  node2: Node
): number {
  const v1 = { x: node1.x - pivot.x, y: node1.y - pivot.y };
  const v2 = { x: node2.x - pivot.x, y: node2.y - pivot.y };
  let angle = Math.atan2(v2.y, v2.x) - Math.atan2(v1.y, v1.x);

  if (angle > Math.PI) {
    angle -= 2 * Math.PI;
  } else if (angle <= -Math.PI) {
    angle += 2 * Math.PI;
  }

  // This could be a bug. I used to to bring numbers from [-PI, PI] to [0, PI]
  if (angle < 0) {
    angle = -angle;
  }

  return radToDeg(angle);
}

export function radToDeg(radians: number): number {
  return radians * (180 / Math.PI);
}
