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

export function angleOfEdge(node1: Node, node2: Node) {
  const rad = Math.atan2(node1.y - node2.y, node1.x - node2.x);
  return Math.abs(radToDeg(rad));
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

// source: https://codereview.stackexchange.com/a/212678
export function gcd(arr: number[]): number {
  // Use spread syntax to get minimum of array
  const lowest = Math.min(...arr);

  for (let factor = lowest; factor > 1; factor--) {
    let isCommonDivisor = true;

    for (let j = 0; j < arr.length; j++) {
      if (arr[j] % factor !== 0) {
        isCommonDivisor = false;
        break;
      }
    }

    if (isCommonDivisor) {
      return factor;
    }
  }

  return 1;
}
