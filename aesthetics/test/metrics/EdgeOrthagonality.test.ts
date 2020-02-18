import { expect } from "chai";
import { EdgeOrthagonality } from "../../src/metrics";
import { Graph, preprocess } from "../../src";
import "mocha";

describe("@evaluations/EdgeOrthagonality", () => {
  context("#calculate", () => {
    let edgeorth: Graph;
    beforeEach(() => {
      const fixtures = [
        require("../fixtures/edgeorth.json"),
        require("../fixtures/symmetric.json")
      ];
      const graphs = preprocess(fixtures);
      edgeorth = graphs[0];
    });

    it("calcuates the correct average edge orthagonality", () => {
      const avgEdgeOrth = new EdgeOrthagonality(edgeorth).calculate();
      expect(avgEdgeOrth).to.be.closeTo(0.556, 0.01);
    });
  });
});
