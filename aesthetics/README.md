# Aesthetic Evaluator
Written in Typescript.

## Installation
Download NodeJS. Then install dependencies by running `npm i`. Then, compile the program by running `npm start`.

## Running
```
node dist/index.js -d ./test/graphs -o ./test/output
```

Which produces the output:
```
Aesthetics Evaluation
        Loading from  /home/matt/Documents/673/graph-viz/aesthetics/test/graphs
        Found 1 graph(s)
        Preprocessed 1 graph(s)
        Evaluating graphs with 3 evaluator(s)
        Writing to /home/matt/Documents/673/graph-viz/aesthetics/test/output
        Wrote 1 evaluations(s)
```
#### Options:
`-d, --dir <directory>` which is where the (input JSON) graphs are located at.

`-o, --outdir <directory>` which is where the (out JSON) graphs will be written to.

## Adding your own Evaluations and Metrics

`metrics.ts` contains functions that operate on a graph and return whatever data you need. They are not evaluation functions.
`evaluations.ts` contains functions that operate on a graph and return a number from 0 to 1.
`util.ts` contains functions that are generic and might be shared between multiple files.
`index.ts` is the driver for the application.


In `evaluations.ts` add a function that follows this syntax, replacing XXXXXXX with your function name. **These function names must end with "Eval" to be picked up by the program.**

```
export const XXXXXXXEval: EvaluationFunction = function XXXXXXXEval(g: Graph): number {
  // implementation...
}
```