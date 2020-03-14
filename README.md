## The Graph Viz Program

### Setup

```bash
npm run setup
```

Also, compile the generator in `generator/gephi_generator` and build the jar.

Finally, place your graph in `generator/input`. If it is a JSON file, you need to run the converter script to get it into a GEXF file.

### Live Compile for Development

```bash
npm run build:watch
```

### Single Compilation

```bash
npm run build:services
```

### Run the Thing

Follow the instructions in one of the compilation sections, then run

```bash
npm start
```
