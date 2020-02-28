const fs = require('fs');

if (!process.argv[2]) {
  throw new Error('Need the path to the graph you are going to change')
}
const data = require(process.argv[2]);


const xml = {
  gexf: {
    graph: {
      nodes: {
        node: data.nodes.map(node => ({
          "@id": node.id,
          "#text": ""
        }))
      },
      edges: {
        edge: data.links.map((edge, index) => ({
          "@id": index,
          "@source": edge.source,
          "@target": edge.target,
          "#text": ""
        }))
      }
    }
  }
}

console.log(json2xml(xml, ' '));






// dependency:  https://goessner.net/download/prj/jsonxml/

/*	This work is licensed under Creative Commons GNU LGPL License.

	License: http://creativecommons.org/licenses/LGPL/2.1/
   Version: 0.9
	Author:  Stefan Goessner/2006
	Web:     http://goessner.net/ 
*/
function json2xml(o, tab) {
  var toXml = function (v, name, ind) {
    var xml = "";
    if (v instanceof Array) {
      for (var i = 0, n = v.length; i < n; i++)
        xml += ind + toXml(v[i], name, ind + "\t") + "\n";
    }
    else if (typeof (v) == "object") {
      var hasChild = false;
      xml += ind + "<" + name;
      for (var m in v) {
        if (m.charAt(0) == "@")
          xml += " " + m.substr(1) + "=\"" + v[m].toString() + "\"";
        else
          hasChild = true;
      }
      xml += hasChild ? ">" : "/>";
      if (hasChild) {
        for (var m in v) {
          if (m == "#text")
            xml += v[m];
          else if (m == "#cdata")
            xml += "<![CDATA[" + v[m] + "]]>";
          else if (m.charAt(0) != "@")
            xml += toXml(v[m], m, ind + "\t");
        }
        xml += (xml.charAt(xml.length - 1) == "\n" ? ind : "") + "</" + name + ">";
      }
    }
    else {
      xml += ind + "<" + name + ">" + v.toString() + "</" + name + ">";
    }
    return xml;
  }, xml = "";
  for (var m in o)
    xml += toXml(o[m], m, "");
  return tab ? xml.replace(/\t/g, tab) : xml.replace(/\t|\n/g, "");
}