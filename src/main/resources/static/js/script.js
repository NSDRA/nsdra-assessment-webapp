document.getElementById("submit").addEventListener('click', function (event) {

	event.preventDefault();

    document.getElementById("loading-sec").className = 'loading-sec';

    fetch('http://localhost:8086/FairAssessor/assess', {
      method: 'post',
      headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({url: document.getElementById("url").value, pattern: "", type: document.getElementById("type").value})
    }).then(res => res.json())
      .then(res => renderResults(res));
});

function renderResults(json)
{
    var span = '<span id="fetch-status" class="badge badge-info float-right"></span>';
    document.getElementById("acc-header").innerHTML = '<span class="float-left">'+json.url + '</span>' + span;
    document.getElementById("fetch-status").innerHTML = json.status;

    var table = document.getElementById("mi-table");
    table = table.getElementsByTagName("tbody")[0];

    table.innerHTML = "";

    for(var i = 0; i < json.results.length; i++) {

        var mi = json.results[i];
        var row = table.insertRow();

        var mi_id = row.insertCell(0);
        var mi_title = row.insertCell(1);
        var mi_comment = row.insertCell(2);
        var mi_status = row.insertCell(3);

        mi_id.innerHTML = '<a target="_blank" href="'+mi.url+'">'+mi.id+'</a>';
        mi_title.innerHTML = mi.title;
        mi_comment.innerHTML = mi.comment;
        if(mi.status == "PASS"){
            mi_status.innerHTML = '<span class="badge badge-success">'+mi.status+'</span>';
        }else if(mi.status == "FAIL"){
            mi_status.innerHTML = '<span class="badge badge-danger">'+mi.status+'</span>';
        }
    }

    document.getElementById("results-sec").className = 'results-sec';
    document.getElementById("loading-sec").className = 'loading-sec hidden';
}