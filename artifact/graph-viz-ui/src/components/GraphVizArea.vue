<template>
  <div>
    <div class="vizArea">
      <div id="my_dataviz"></div>
    </div>
  </div>
</template>

<!-- Load d3.js -->
<!-- <script src="https://d3js.org/d3.v4.js"></script> -->

<script>
// import * as d3 from "d3"

export default {
  name: "GraphVizArea",
  props: {},
  data() {}
};

// const network = {
//     "nodes": [
//       {
//         "id": 1,
//         "x": 0,
//         "y": 0
//       },
//       {
//         "id": 2,
//         "x": 100,
//         "y": 0
//       },
//       {
//         "id": 3,
//         "x": 100,
//         "y": 100
//       },
//       {
//         "id": 4,
//         "x": 0,
//         "y": 100
//       }
//     ],
//     "edges": [
//       {
//         "n1": 1,
//         "n2": 2
//       },
//       {
//         "n1": 2,
//         "n2": 3
//       },
//       {
//         "n1": 3,
//         "n2": 4
//       },
//       {
//         "n1": 4,
//         "n2": 1
//       },
//       {
//         "n1": 3,
//         "n2": 1
//       }
//     ]
//   };

// // set the dimensions and margins of the graph
// //223,124 to middle
// //211,114 data
// var margin = { top: 0, right: 0, bottom: 0, left: 0 };
// var width = 400 - margin.left - margin.right;
// var height = 400 - margin.top - margin.bottom;

// // append the svg object to the body of the page
// var svg = d3
//   .select("#my_dataviz")
//   .append("svg")
//   .attr("width", width + margin.left + margin.right)
//   .attr("height", height + margin.top + margin.bottom)
//   .append("g")
//   .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

// console.log(network);

// // Initialize the links
// var link = svg
//   .selectAll("line")
//   .data(network.edges)
//   .enter()
//   .append("line")
//   .style("stroke", "#aaa")
//   .attr("x1", d => {
//     console.log(network.nodes, d);
//     const selected = network.nodes.find(n => n.id == d.n1).x;
//     console.log(selected);
//     return selected;
//   })
//   .attr("y1", d => network.nodes.find(n => n.id == d.n1).y)
//   .attr("x2", d => network.nodes.find(n => n.id == d.n2).x)
//   .attr("y2", d => network.nodes.find(n => n.id == d.n2).y);

// var node = svg
//   .selectAll("circle")
//   .data(network.nodes)
//   .enter()
//   .append("circle")
//   .attr("r", 15)
//   .attr("cx", d => d.x)
//   .attr("cy", d => d.y)
//   .style("fill", "#69b3a2");

// // draw in the middle of the graph
// var node = svg
//   .selectAll("circle2") // what to call this set of elements
//   .data([{}])
//   .enter()
//   .append("circle") // the type of element to draw (a circle or a line or maybe rect or something, not sure what they support)
//   .attr("r", 5)
//   .attr("cx", d => 50)
//   .attr("cy", d => 50)
//   .style("fill", "#69b3a2");

// window.nodes = node;
</script>

<style scoped>

.vizArea {
  height: 600px;
  width: 600px;
  background-color: #ffffff;
  border: 1px solid #dddddd;
  margin-top: 0px;
  /* align-content: center; */
  /* margin: auto; */
}
</style>
