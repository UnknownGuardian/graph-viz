<template>
  <div id="app">
    <div class="topBar">
      <div>
        <h1 class="title is-4 alignleft">Visualization Generator</h1>
        <h2 class="subtitle is-6 alignright" style="margin-top: 8px">Version 0.1</h2>
      </div>
      <div style="clear: both;"></div>

      <button class="button is-dark is-small">Generate, then filter</button>
      <button class="button is-light is-small">Random</button>
    </div>
    <!-- <h1 class="title is-4">Visualization Generator</h1>
    <h2 class="subtitle is-6">Version 0.0</h2>-->

    <div class="main">
      <GraphVizArea @eval="evaluate" ref="viz" />
    </div>

    <div class="sidenav">
      <h1 class="title is-6" style="padding-top: 16px; padding-left: 8px;">Total Score: {{score}}</h1>
      
      <h1 class="title is-6" style="padding-top: 16px; padding-left: 8px;">Evaluators</h1>
      <table class="table is-hoverable is-fullwidth">
        <tr v-for="ev in evaluations" :key="ev.name">
          <td>{{ ev.name }}</td>
          <td>{{ ev.score }}</td>
        </tr>
      </table>

      <h1 class="title is-6" style="padding-top: 16px; padding-left: 8px;">Metrics</h1>
      <table class="table is-hoverable is-fullwidth">
        <tr v-for="m in metrics" :key="m.name" >
          <td>{{ m.name}}</td>
          <td>{{ m.value }}</td>
        </tr>
      </table>
    </div>

  </div>
</template>

<script>

import GraphVizArea from "./components/GraphVizArea.vue";
import { preprocess as preprocessAesthetics, evaluate as evalAesthetics } from "graph-viz-aesthetics"


export default {
  name: "App",
  components: {
    GraphVizArea
  },
  data() {
    return {
      score: 0,
      metrics:[{name:"XX", value:0}],
      evaluations:[{name:"YY", score:0}]
    };
  },
  methods: {
    evaluate(json) {
      const graphs = preprocessAesthetics([json]);
      const evaluation = evalAesthetics(graphs)[0]
      const results = evaluation.evaluations;
      this.evaluations = results.map(e => ({name:e.name, score:e.score}))
      this.metrics = results.map(e => ({name:e.metric.name, value:e.metric.value}))

      this.score = this.evaluations.reduce((a, b) => (a + b.score), 0) / this.evaluations.length
      this.$refs.viz.tryAnother();
    }
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; */
  color: #222222;
  margin-top: 0px;
  /* display: inline-block; */
}

.alignleft {
	float: left;
}
.alignright {
	float: right;
}

td {
  widows: 300px;
}

tr {
  font-size: 13px;
}
.content {
  display: inline-block;
}

.button {
  margin-right: 16px;
}

.sidenav {
  height: 600px; /* Full-height: remove this if you want "auto" height */
  width: 300px; /* Set the width of the sidebar */
  /* position: fixed; Fixed Sidebar (stay in place on scroll) */
  z-index: 1; /* Stay on top */
  top: 0; /* Stay at the top */
  left: 0;
  background-color: #fcfcfc;
  overflow-x: hidden; /* Disable horizontal scroll */
  float: left;
  border-top: 1px solid #dddddd;
  border-right: 1px solid #dddddd;
  border-bottom: 1px solid #dddddd;
  /* padding: 16px; */
  /* padding-top: 20px; */
}

.main {
  margin-top: 0px;
  /* margin-left: 200px; Same as the width of the sidebar */
  padding: 0px;
  padding-left: 16px;
  float: left;
}

p {
  font-size: 14px;
  margin-top: 0;
}

.topBar {
  padding: 16px;
  width: 932px;
}

html,
body {
  height: 100%;
  margin: 0;
  padding: 0;
  background-color: #ffffff;
}
</style>
