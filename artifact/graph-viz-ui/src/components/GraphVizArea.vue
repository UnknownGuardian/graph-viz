<template>
  <div>
    <div class="vizArea">
      <svg :width="600" :height="600">
        <g>
          <circle
            v-for="n in nodes"
            :key="n.id"
            :r="2"
            :cx="n.x*2"
            :cy="n.y*2"
            fill="#ff00ff"
          />
          <line
            v-for="e in edges"
            :key="e.source.id+e.target.id"
            :x1="e.x1*2"
            :y1="e.y1*2"
            :x2="e.x2*2"
            :y2="e.y2*2"
            stroke="#aaa"
           />
        </g>
      </svg>
    </div>
  </div>
</template>

<script>
import * as d3 from "d3"

export default {
  name: "GraphVizArea",
  
  data() {
    return {
      graphJSON: {},
      nodes:[],
      edges:[],
      count: 0,
      simulation: null
    }
  },
  methods: {
    show(viz) {
      console.log("SHowing a viz", viz.graphName);
      this.nodes.forEach((node, index) => {
        node.x = viz.points[index].x + 150,
        node.y = viz.points[index].y + 150
      });
      this.edges.forEach(edge => {
        edge.x1 = edge.source.x,
        edge.y1 = edge.source.y
        edge.x2 = edge.target.x,
        edge.y2 = edge.target.y
      });
      this.$forceUpdate();
    },

    render(graph) {
      const edges = this.edges = graph.edges.map(d => Object.create(d));
      const nodes = this.nodes = graph.nodes.map(d => Object.create(d));


      this.edges.forEach(e => {
        e.source = nodes.find(n => n.id == e.source);
        e.target = nodes.find(n => n.id == e.target);
      });



      nodes.forEach(n => {
        n.x = 0
        n.y = 0
      })


      const svg = d3.select('#my_dataviz').append('svg')
        .attr("viewBox", [0, 0, 600, 600]);
        

      svg
        .append("g")
        .attr("stroke", "#999")
        .attr("stroke-opacity", 0.6)
        .selectAll("line")
        .data(edges)
        .join("line")
        //.attr("stroke-width", d => Math.sqrt(d.value))
        .attr("stroke", "#aaa")
        .attr("x1", d => d.source.x)
        .attr("y1", d => d.source.y)
        .attr("x2", d => d.target.x)
        .attr("y2", d => d.target.y);

      const scale = d3.scaleOrdinal(d3.schemeCategory10);
      const color = d => scale(d.group);

      const node = svg
        .append("g")
        .selectAll("circle")
        .data(nodes)
        .join("circle")
        .attr("x", d=>d.x)
        .attr("y", d=>d.y)
        .attr("fill", color)//.attr("fill", "#69b3a2");

      node.append("title")
      .text(d => d.id);

      window.d3 = d3;
    }
  },
};
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
