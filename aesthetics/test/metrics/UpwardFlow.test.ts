import { expect } from "chai";
import { UpwardFlow } from "../../src/metrics";
import { Graph, preprocess } from "../../src";
import "mocha";

describe("@evaluations/UpwardFlow", () => {
    context("#calculate", () => {
        let upOnly: Graph;
        let upMix: Graph;
        beforeEach(() => {
            const fixtures = [
                require("../fixtures/upward_only.json"),
                require("../fixtures/upward_side.json")
            ];
            const graphs = preprocess(fixtures);
            upOnly = graphs[0];
            upMix = graphs[1];
        });

        it("calcuates the correct upward flow on only up graph", () => {
            const flow = new UpwardFlow(upOnly).calculate();
            expect(flow).to.be.eq(1);
        });
        it("calcuates the correct upward flow on half up graph", () => {
            const flow = new UpwardFlow(upMix).calculate();
            expect(flow).to.be.eq(0.5);
        });
    });
});
