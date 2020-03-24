import express from "express";
import { graphs } from "./graphs";

const app = express();
const cors = require("cors");
const port = 3000;

app.use(cors());
app.get("/", (req: any, res: any) => res.send("<a href='/graphs'>/graphs</a>"));
app.get("/graphs", graphs.list);
app.get("/graphs/remake", graphs.remake);
app.listen(port, start);

function start() {
  console.log(`Using port ${port}!`);
  graphs.watch();
}
