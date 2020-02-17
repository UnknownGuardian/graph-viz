import { Graph } from "../";

export abstract class Metric {
  protected graph: Graph;
  constructor(g: Graph) {
    this.graph = g;
  }
  public abstract calculate(): number;
}
