import { expect } from "chai";
import { NodeOrthagonality } from "../../src/metrics";
import { Graph, preprocess } from "../../src";
import "mocha";

describe("@evaluations/NodeOrthagonality", () => {
  context("#calculate", () => {
    let nodeorth: Graph;
    beforeEach(() => {
      const fixtures = [
        require("../fixtures/nodeorth.json"),
      ];
      const graphs = preprocess(fixtures);
      nodeorth = graphs[0];
    });

    it("calcuates the correct node orthagonality", () => {
      const avgNodeOrth = new NodeOrthagonality(nodeorth).calculate();
      expect(avgNodeOrth).to.be.closeTo(0.5, 0.01);
    });
  });
});
