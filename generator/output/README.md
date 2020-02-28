# JSON to GEXF


### About

This folder houses scripts to change the output of Gephi (a `.gexf`) to our native graph definitions, which are likely to be JSON format with the following structure:

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

### Setup:

Install NodeJS, then run `npm i` to install the single dependency (a lame xml -> json parser).



### Running:

Run the json_to_gexf.js file with 
```
node gexf_to_json.js ./data/gexf_output_test.gexf
node gexf_to_json.js PATH_TO_JSON_GRAPH_HERE
```

For example, this command will pipe the result into another file:
```
node gexf_to_json.js ./data/gexf_output_test.gexf > ./data/gexf_output_test.json
```