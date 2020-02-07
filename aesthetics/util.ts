import { Node } from ".";

export function distanceBetweenNodes(n1: Node, n2: Node): number {
  return Math.sqrt((n1.x - n2.x) ** 2 + (n1.y - n2.y) ** 2)
}