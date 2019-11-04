//Node.js Server
//Basically, the whole thing should probably have been done in Node, but it wasn't
//Oof

//@title node-file.js
//@author Michael G
//@purpose Web Server Hosting for Module to go into SIS


var http = require('http');
var fs = require("fs");
var url = require("url");
var filename;

http.createServer(function(req, res){
    
    if (req.url == "/true"){
        filename = "frame.html"
    }else{
        filename = "forbidden.html"
        var q = url.parse(req.url, true).query.programcode;
        if (typeof q === 'undefined'){
        ;
        }else{
            fs.writeFile('dataIn/sisData.txt', q, function (err) {
                if (err) throw error;
                    ;
            });   

        }
    }

    fs.readFile(filename, function(err, data){
        res.writeHead(200, {'Content-Type': 'text/html'});
        res.write(data);
        res.end();
    })
}).listen(8080);