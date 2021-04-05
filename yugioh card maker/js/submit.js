

document.addEventListener('DOMContentLoaded',(event=>{

  var button = document.getElementById("generate");
  button.addEventListener('click',submitFunction);

  var randomb = document.getElementById("random");
  randomb.addEventListener('click',randome)

  function submitFunction(){
    let name = document.getElementById("name").value;
    let cardtype = document.getElementById("cardtype").value;
    let subtype = document.getElementById("subtype").value;
    let attribute = document.getElementById("attribute").value;
    let level = document.getElementById("level").value;
    let trapmagictype = document.getElementById("trapmagictype").value;
    let rarity = document.getElementById("rarity").value;
    let picture = document.getElementById("picture").value;
    let circulation = document.getElementById("circulation").value;
    let set1 = document.getElementById("set1").value, set2 = document.getElementById("set2").value;
    let type = document.getElementById("type").value;
    let description = document.getElementById("carddescription").value;
    let atk = document.getElementById("atk").value, def = document.getElementById("def").value;
    let creator = document.getElementById("creator").value;
    let year = document.getElementById("year").value;
    let serial = document.getElementById("serial").value;
    let types = [cardtype,subtype]

    let text = `${name};${picture};${set1}-${set2};${serial};${description};${atk};${def};${level};${attribute};${types};${trapmagictype};${rarity};${circulation};${type};${creator};${year}`
    console.log(text);

    download("yugioh-card-info",text)  

}

  function download(filename,text){
    var element = document.createElement('a');
    element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
    element.setAttribute('download', filename);

    element.style.display = 'none';
    document.body.appendChild(element);

    element.click();

    document.body.removeChild(element);
  }

  function random(){
    let serial = "";
    for (let i = 0; i < 8; i++) {
      serial += Math.floor(Math.random()*10);
    }
    document.getElementById("serial").value = serial;
  }

  function randome(){
    let min = 0;
    let max = 9;
    let serial = "";
    for (let i = 0; i < 8; i++) {
      serial +=(Math.floor(Math.pow(10,14)*Math.random()*Math.random())%(max-min+1))+min;
    }
    document.getElementById("serial").value = serial;
  }

}))
