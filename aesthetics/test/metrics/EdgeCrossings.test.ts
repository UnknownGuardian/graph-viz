import { expect } from "chai";
import { EdgeCrossings } from "../../src/metrics";
import { Graph, preprocess } from "../../src";
import "mocha";

describe("@evaluations/EdgeCrossings", () => {
  context("#calculate", () => {
    let edgeNoCross: Graph;
    let edge3Cross: Graph;
    beforeEach(() => {
      const fixtures = [
        require("../fixtures/edge_no_crossing.json"),
        require("../fixtures/edge_3_crossing.json")
      ];
      const graphs = preprocess(fixtures);
      edgeNoCross = graphs[0];
      edge3Cross = graphs[1];
    });

    it("calcuates the correct number of edge crossings percentage when there are 2", () => {
      const crossings = new EdgeCrossings(edge3Cross).calculate();
      expect(crossings).to.eq(0.5);
    });
    it("calcuates the correct number of edge crossings percentage when there are 0", () => {
      const crossings = new EdgeCrossings(edgeNoCross).calculate();
      expect(crossings).to.eq(0);
    });
  });
});
