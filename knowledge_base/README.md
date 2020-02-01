# Graphs

Index of a bunch of existing graphs:
http://users.cecs.anu.edu.au/~bdm/data/graphs.html

Or use `geng` (included in the Nauty package) to generate them all.

You can use `showg` to translate the graphs from the .g6 extension to an adjacency matrix.

## Nauty

This is a bunch of tools for generating graphs (instead of storing the hundreds of GB to have them stored in our repo)

First, extract the zip, then inside of nauty26r12, run:

```
./configure
```

then run

```
make
```

Finally, `chmod +x FILE` to make `FILE` executable so you can run it via command line.

[Documentation](http://users.cecs.anu.edu.au/~bdm/nauty/nug26.pdf) is online or also downloaded as a PDF in the `tools/nauty` folder.

## ShowG: G6 graphs:

Use `./showg` to view, convert, and grab meta-data from graphs generated in the .g6 format.

[Documentation](http://users.cecs.anu.edu.au/~bdm/data/formats.html)

[Online Viewer](http://treedecompositions.com/#/graph/G%3Fr@%60_%0AG%3Fqa%60_%0AGCQR@O)
