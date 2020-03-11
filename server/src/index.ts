import * as fs from "fs";
import * as path from "path";
const express = require("express");
const app = express();
const port = 3000;

const aestheticsOutputDir = path.join(
  __dirname,
  "..",
  "..",
  "generator",
  "output",
  "scores"
);

app.get("/", (req: any, res: any) => {
  res.send("You probably should hit /graphs");
});
app.get("/graphs", (req: any, res: any) => {
  const files = fs.readdirSync(aestheticsOutputDir);
  let graphs = files.map(f => require(path.join(aestheticsOutputDir, f)));
  console.log("Sending ", graphs);
  res.json(graphs);
});

app.listen(port, () => console.log(`Example app listening on port ${port}!`));
