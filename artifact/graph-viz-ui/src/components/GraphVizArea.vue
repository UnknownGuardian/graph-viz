<template>
  <div>
    <div class="vizArea">
      <div id="my_dataviz"></div>
    </div>
  </div>
</template>

<script>
import * as d3 from "d3"

export default {
  name: "GraphVizArea",
  props: {},
  
  data() {
    return {
      graphJSON: {},
      nodes:[],
      count: 0,
      simulation: null
    }
  },
  async mounted() {
    this.graphJSON = await d3.json('./graph2.json')
    this.render();
  },
  methods: {
    getGraphJSON() {
      const nodes = [];
      d3.selectAll('circle').each(node => {
        nodes.push({
          id: node.id,
          x:node.x,
          y:node.y
        })
      })
      const edges = this.graphJSON.links.map(link => ({
        n1: link.source.id,
        n2: link.target.id
      }))

      const json = {nodes, edges}
      return json;
    },
    tryAnother() {
      console.log("Trying another");
      this.nodes.forEach(n => {
        n.x = Math.random() * 200 - 100 + 300
        n.y = Math.random() * 200 - 100 + 300
      })
      this.simulation.alpha(1).restart();
    },
    render() {
      const links = this.graphJSON.links.map(d => Object.create(d));
      const nodes = this.nodes = this.graphJSON.nodes.map(d => Object.create(d));

      nodes.forEach(n => {
        delete n.group;
        n.x = Math.random() * 200 - 100 + 300
        n.y = Math.random() * 200 - 100 + 300
      })

      links.forEach(l => {
        delete l.value
      })


      const svg = d3.select('#my_dataviz').append('svg')
        .attr("viewBox", [0, 0, 600, 600]);
        
      this.simulation = d3
        .forceSimulation(nodes)
        .force("link", d3.forceLink(links).id(d => d.id))
        .force("charge", d3.forceManyBody())
        .force("center", d3.forceCenter(600 / 2, 600 / 2))


      const link = svg
        .append("g")
        .attr("stroke", "#999")
        .attr("stroke-opacity", 0.6)
        .selectAll("line")
        .data(links)
        .join("line")
        //.attr("stroke-width", d => Math.sqrt(d.value))
        .attr("stroke", "#aaa");

      const scale = d3.scaleOrdinal(d3.schemeCategory10);
      const color = d => scale(d.group);

      const node = svg
        .append("g")
        .selectAll("circle")
        .data(nodes)
        .join("circle")
        .attr("r", 5)
        .attr("fill", color)//.attr("fill", "#69b3a2");

      node.append("title")
      .text(d => d.id);

      // draw in the middle of the graph
      /*const center = svg
        .selectAll("circle2") // what to call this set of elements
        .data([{}])
        .enter()
        .append("circle") // the type of element to draw (a circle or a line or maybe rect or something, not sure what they support)
        .attr("r", 5)
        .attr("cx", () => 50)
        .attr("cy", () => 50)
        .style("fill", "#69b3a2");
      */
      this.count = 0;
      this.simulation.on("tick", () => {
        link
            .attr("x1", d => d.source.x)
            .attr("y1", d => d.source.y)
            .attr("x2", d => d.target.x)
            .attr("y2", d => d.target.y);


        node
            .attr("cx", d => d.x)
            .attr("cy", d => d.y);

        this.count++;
        if(this.count > 120){
          this.simulation.stop();
          console.log("simulation ended early");
          const json = this.getGraphJSON();
          this.$emit('eval', json);
          this.count = 0;
        }
      });


      this.simulation.on('end', () => {
        console.log("simulation ended");
        const json = this.getGraphJSON();
        this.$emit('eval', json);
      });

      window.d3 = d3;
    }
  },
};


// // set the dimensions and margins of the graph
// //223,124 to middle
// //211,114 data
// var margin = { top: 0, right: 0, bottom: 0, left: 0 };
// var width = 400 - margin.left - margin.right;
// var height = 400 - margin.top - margin.bottom;

// // append the svg object to the body of the page


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
