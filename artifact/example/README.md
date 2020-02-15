## Hooking up Aesthetics to artifact

1. Run `npm link` in the `./aesthetics` directory. This registers the directory as the `graph-viz-aesthetics` package local to your machine.
2. Run `npm link graph-viz-aesthetics` in the `./artifact/vue-cli-whatever-you-called-your-folder` directory to install this package in your vue app. (This literally just symlinks your other directory)
3. Review ./artifact/example/index.js to see how to include this. If you are using TS you can use

```
// Using Typescript:
import * as aesthetics from "graph-viz-aesthetics";

// or using JS
const aesthetics = require("graph-viz-aesthetics");
```

4. Know which functions are available.
   - `preprocess(GraphJSON)`: If your graph was just read from JSON, you can use `aesthetics.preprocess()` to parse it for you into the format that `evaluate()` expects.
   - `evaluate(Graph)`: If your graph data is already connected, then you can just call this format.

The format for both those data types (GraphJSON and Graph) are located in top 30ish lines of `index.ts` in the aesthetics program.

Return value is exact same as if you called the progmra from the command line:

```
[
 {
  "graphName": "Test Graph",
  "evaluations": [
   {
    "func": "randomEval",
    "score": 0.1728609746016181
   },
   {
    "func": "thresholdEdgeCountEval",
    "score": 0.6
   },
   {
    "func": "smallEdgeEval",
    "score": 0
   }
  ]
 }
]
```
