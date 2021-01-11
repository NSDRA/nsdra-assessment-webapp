var json = `
{
  "@context": {
    "bs": "https://bioschemas.org/",
	"schema": "https://schema.org/",
	"citation": "schema:citation",
	"name": "schema:name",
	"url": "schema:name",
	"variableMeasured": "schema:variableMeasured",
	"unitText": "schema:unitText"
  },
  "@type": "schema:Dataset"
}
`;

var jsonldTextArea = document.getElementById("generated-jsonld");

var dsId = document.getElementById("ds-id");
var dsTitle = document.getElementById("ds-title");
var dsUrl = document.getElementById("ds-url");
var dsCitation = document.getElementById("ds-citation");

var pchemSize = document.getElementById("pchem-size-name");
var pchemSizeUnit = document.getElementById("pchem-size-name-unit");

var pchemSsa = document.getElementById("pchem-ssa-name");
var pchemSsaUnit = document.getElementById("pchem-ssa-name-unit");

var pchemZeta = document.getElementById("pchem-zeta-name");
var pchemZetaUnit = document.getElementById("pchem-zeta-name-unit");

var pchemShape = document.getElementById("pchem-shape-name");
var pchemShapeUnit = document.getElementById("pchem-shape-name-unit");


jsonldTextArea.value = json;

var jsonObj = JSON.parse(json);

var inputArr = [dsTitle, dsId, dsUrl, dsCitation];

var inputMap = new Map();
inputMap.set('ds-id','@id');
inputMap.set('ds-title', 'name');
inputMap.set('ds-url', 'url');
inputMap.set('ds-citation', 'citation');

inputArr.forEach(function (element, index) {

    element.addEventListener('keyup', function (event) {
        if (event.isComposing || event.keyCode === 229) {
            return;
        }

        let jsonProp = inputMap.get(element.id);

        if(element.value == ""){
            delete jsonObj[jsonProp];
        }else if(!jsonObj.hasOwnProperty(jsonProp)){
            jsonObj[jsonProp] = element.value;
        }else{
            jsonObj[jsonProp] = element.value;
        }

        jsonldTextArea.value = JSON.stringify(jsonObj,null, "\t");

    });
});

var pchemMap = new Map();
pchemMap.set('pchem-size-name','Size');
pchemMap.set('pchem-ssa-name', 'Specific surface area');
pchemMap.set('pchem-zeta-name', 'Zeta potential');
pchemMap.set('pchem-shape-name', 'Shape');

var propCheck = document.querySelectorAll("input[id$='-name']");

propCheck.forEach(function (element, index) {

    element.addEventListener('change', function() {

        currentCheckbox = this;

        if (this.checked) {
            document.getElementById(this.id+"-unit").disabled = false;

            if(jsonObj.hasOwnProperty("variableMeasured")){
                jsonObj.variableMeasured.push({"@type": "schema:PropertyValue", "name": pchemMap.get(this.id)});
            }else{
                jsonObj.variableMeasured = [];
                jsonObj.variableMeasured.push({"@type": "schema:PropertyValue", "name": pchemMap.get(this.id)});
            }

        } else {
            document.getElementById(this.id+"-unit").disabled = true;
            document.getElementById(this.id+"-unit").checked = false;

            jsonObj.variableMeasured.forEach(function (element, index) {
                if(element.name === pchemMap.get(currentCheckbox.id)){
                    jsonObj.variableMeasured.splice(index,1);
                }
                if(jsonObj.variableMeasured.length == 0){
                    delete jsonObj.variableMeasured;
                }
            });
        }

        jsonldTextArea.value = JSON.stringify(jsonObj,null, '\t');
    });
});

var unitCheck = document.querySelectorAll("input[id$='-unit']");

unitCheck.forEach(function (element, index) {

    element.addEventListener('change', function() {

        currentCheckbox = this;
        propId = this.id.substr(0,this.id.length-5);
        currentPropChechbox = document.getElementById(propId);

        if (this.checked) {

            jsonObj.variableMeasured.forEach(function (element, index) {
                if(element.name === pchemMap.get(currentPropChechbox.id)){
                    jsonObj.variableMeasured[index].unitText = "reported";
                }
            });

        } else {
            jsonObj.variableMeasured.forEach(function (element, index) {
                if(element.name === pchemMap.get(currentPropChechbox.id)){
                    delete jsonObj.variableMeasured[index].unitText;
                }
            });
        }

        jsonldTextArea.value = JSON.stringify(jsonObj,null, '\t');
    });
});

document.getElementById("resetForm").addEventListener('click', function (event) {
    jsonldTextArea.value = json;
});