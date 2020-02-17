import { expect } from "chai";
import { MinimumAngle } from "../../src/metrics";
import { Graph, preprocess } from "../../src";
import "mocha";

describe("@evaluations/MinimumAngle", () => {
  context("#calculate", () => {
    let minAngle15: Graph;
    let symmetric: Graph;
    beforeEach(() => {
      const fixtures = [
        require("../fixtures/minangle.json"),
        require("../fixtures/symmetric.json")
      ];
      const graphs = preprocess(fixtures);
      minAngle15 = graphs[0];
      symmetric = graphs[1];
    });

    it("calcuates the correct minimum angle when graph is 15 deg", () => {
      const minAngle = new MinimumAngle(minAngle15).calculate();
      expect(minAngle).to.be.closeTo(0.78, 0.01);
    });
    it("calcuates the correct minimum angle when graph is symmetric", () => {
      const minAngle = new MinimumAngle(symmetric).calculate();
      expect(minAngle).to.be.closeTo(1, 0.01);
    });
  });
});
