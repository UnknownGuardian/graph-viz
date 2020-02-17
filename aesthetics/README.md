# Aesthetic Evaluator

Written in Typescript.

## Installation

Download NodeJS. Then install dependencies by running `npm i`. Then, compile the program by running `npm start`.

## Running

```sh
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

## Testing

Run `npm test`

### Writing your own test

Add to `test/` your custom tests, which are files that use `mocha` and need to end in the extension `.test.ts`.

## Adding your own Evaluations and Metrics

### Metrics

Metrics have been moved to classes since it is likely each will have multiple functions with it. This is for convenience.

1. Inside `src/metrics/`, add a new class which extends `Metric` and override the `calculate()` method. See `MinimumAngle.ts` for an example.
2. After creating your new class, add the following line of code inside of `src/metrics/index.ts`.

```typescript
export * from "./YOUR_CLASS_NAME_HERE";
```

Then you can use it inside of your evaluation functions by newing it up like so:

```typescript
export const minimumAngle: EvaluationFunction = function minimumAngle(
  g: Graph
): number {
  const minAngle = new MinimumAngle(g).calculate();
  return 1 - minAngle;
};
```

### Evaluations

Evaluations have been moved to `src/evaluations/functions.ts`. If your function is easy, just inline it there. If not, capture the functions in a custom Metric class, then new up your Metric class.

There are no requirements for function names anymore.
