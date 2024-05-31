/**
 * 
 */
const $div =  document.createElement("div");
$div.className="textdiv";
const $h1 =  document.createElement("h1");
$div.className="texth1";
const $h2 =  document.createElement("h2");
$div.className="texth2";
$h2.innerText="IN JEJU";
$h1.innerText="METHOD HOTEL";
console.log($h2);
$div.appendChild($h1);
$div.appendChild($h2);
const $background = document.getElementsByClassName("background")[0];
console.dir($background);
$background.appendChild($div);