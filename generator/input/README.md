# JSON to GEXF


### About

This folder houses scripts to change our native graph definitions, which are likely to be JSON format with the following structure:

```json
{
  "nodes":[
    { "id": 1},
    ...
  ],
  "links": [
    { "source": 1, "target":2 },
    ...
  ]
}
```

into the `.gexf` structure that Gephi expects.

### Running:

Run the json_to_gexf.js file with 
```
node json_to_gexf.js PATH_TO_JSON_GRAPH_HERE
```

For example, this command will pipe the result into another file:
```
node json_to_gexf.js ./data/graph_43_nodes^Cson > ./data/graph_43_nodes.gexf
```