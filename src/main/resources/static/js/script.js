if(document.getElementById("submit") != null){

    document.getElementById("submit").addEventListener('click', function (event) {

        event.preventDefault();

        document.getElementById("loading-sec").className = 'loading-sec';

        var items = document.querySelectorAll('.mi-item');

        var miListArr = [];

        for (var i=0; i < items.length; i++) {

                var selecedItem = items[i].querySelector('input[name=results]:checked') !== null;

                if(selecedItem){

                    obj = { miId: items[i].querySelector('input[name=mi-id]').value ,
                            url:items[i].querySelector('input[name=mi-url]').value ,
                            title: items[i].querySelector('input[name=mi-title]').value ,
                            variable: items[i].querySelector('input[name=mi-variable]').value,
                            comment: "",
                            listId: items[i].querySelector('input[name=mi-list-id]').value,
                            listTitle: items[i].querySelector('input[name=mi-list-title]').value,
                            listRefernceUrl: items[i].querySelector('input[name=mi-list-ref-url]').value
                          };
                    miListArr.push(obj);
                }
        }

        var presistResultsChecked = document.querySelector('input[name=presistResults]:checked') !== null;

        var presistResultsBoolean = false;

        if(presistResultsChecked){
            presistResultsBoolean = true;
        }

        fetch('/assess', {
            method: 'post',
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({url: document.getElementById("url").value, presistResults: presistResultsBoolean, type: document.getElementById("type").value, miList: miListArr })
        }).then(res => res.json())
            .then(res => renderResults(res));
    });
}

var cards = document.querySelectorAll(".card");
for (var i = 0; i < cards.length; i++) {

    var card = cards[i];
    var selectallCheck = card.querySelectorAll('input[name="selectall"]')[0];

    selectallCheck.onclick = function () {
        var parentTable = upTo(this,"table");
        var checkboxes = parentTable.querySelectorAll('input[name=results]');

        for (var checkbox of checkboxes) {
            checkbox.checked = this.checked;
        }
    };
}

function upTo(el, tagName) {
    tagName = tagName.toLowerCase();

    while (el && el.parentNode) {
        el = el.parentNode;
        if (el.tagName && el.tagName.toLowerCase() == tagName) {
            return el;
        }
    }
    return null;
}

function renderResults(json)
{
    if(json != null){
        var miListArr = json.miList;

        var permanentLink = "";

        if(json.presistResults){

            permanentLink = `
                <hr>
                <p class="mb-0">You have chosen to store the assessment results for later use. Thus, you can refer back to this assessment report using the permanent link <a href="https://w3id.org/nsdra/assessment/`+json.uuid+`" class="alert-link">https://w3id.org/nsdra/assessment/`+json.uuid+`</a>.</p>
            `;
        }

        var alert = "";

        if(json.status == "Assessed"){
            alert = `
            <div class="alert alert-success" role="alert">
                <h4 class="alert-heading">Reusability Assessment Succeeded</h4>
                <p> Please check the assessment report below.</p>

                <hr>

                <p><strong>Evaluated URL:</strong> <a href="`+json.url+`">`+json.url+`</a></p>
                <p><strong>Evaluation Timestamp:</strong> `+json.submissionTimestamp+`</p>
                <p><strong>UUID:</strong> `+json.uuid+`</p>

                `+permanentLink+`
            </div>
            `;
        }else{
            alert = `
            <div class="alert alert-danger" role="alert">
                <h4 class="alert-heading">Reusability Assessment Failed!!</h4>
                <p> Please make sure the provided URL is resolvable and contains JSON-LD markup.</p>
            </div>
            `;
        }

        var accordion = document.getElementById("accordion");
        accordion.innerHTML = "";

        var miGroups = _.groupBy(miListArr, 'listId');

        const keys = Object.keys(miGroups);

        var html = alert;

        keys.forEach((key, index) => {

            var miGroup = miGroups[key];

            var miRowsHtml="";

            for(var j = 0; j < miGroup.length; j++){

                var miGroupItem = miGroup[j];

                var statuBadge = "";

                if(miGroupItem.status == "PASS"){
                        statuBadge = '<span class="badge badge-success">'+miGroupItem.status+'</span>';
                }else if(miGroupItem.status == "FAIL"){
                        statuBadge = '<span class="badge badge-danger">'+miGroupItem.status+'</span>';
                }

                miRowsHtml += `
                    <tr class="mi-item">
                        <td><a href="`+miGroupItem.url+`" target="_blank">`+miGroupItem.miId+`</a></td>
                        <td>`+miGroupItem.title+`</td>
                        <td>`+miGroupItem.comment+`</td>
                        <td>`+statuBadge+`</td>
                    </tr>
                `;
            }

            html += `
                <div class="card">
                    <div class="card-header" id="headingOne">
                        <h5 class="mb-0">
                            <button id="acc-header-`+miGroup[0].listId+`" class="acc-header btn btn-link collapsed" data-toggle="collapse" data-target="#collapseOne-`+miGroup[0].listId+`" aria-expanded="false" aria-controls="collapseOne-`+miGroup[0].listId+`">
                                <span class="list-title float-left">`+miGroup[0].listTitle+`</span> <span id="fetch-status-`+miGroup[0].listId+`" class="badge badge-info fetch-status float-right">`+miGroup[0].listId+`</span>
                            </button>
                        </h5>
                    </div>
                    <div id="collapseOne-`+miGroup[0].listId+`" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
                        <div class="card-body">
                            <table id="mi-table-`+miGroup[0].listId+`" class="table table-striped">
                                <thead>
                                    <th>Maturity Indicator ID</th>
                                    <th>Maturity Indicator Title</th>
                                    <th>Comment</th>
                                    <th>Status</th>
                                </thead>
                                <tbody>
                                    `+miRowsHtml+`
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            `;
        });

        var doItAgain = `
            <div class="row">
                  <div class="col-lg-12 col-md-12 col-sm-12 p-0">
                    <button onclick="window.location.href='https://nsdra.org'" type="button" class="btn btn-danger wrn-btn">Do it again</button>
                  </div>
            </div>
        `;

        document.getElementById("accordion").innerHTML = html;

        if(document.getElementById("results-sec") != null){
            document.getElementById("results-sec").className = 'results-sec';
        }

        if(document.getElementById("loading-sec") != null){
            document.getElementById("loading-sec").className = 'loading-sec hidden';
            document.getElementById("search-sec").innerHTML = doItAgain;
        }

    }else{

        var errorHtml = `
            <div class="alert alert-danger" role="alert">
                <h4 class="alert-heading">The reusability report UUID is not valid</h4>
                <p> Please check the URL and make sure it is correct.</p>
            </div>
            `;
        document.getElementById("accordion").innerHTML = errorHtml;
    }
}