<#include "/fragments/head.ftl">

<section class="bg">

	<div id="json-generator-icon-wrapper">
	
		<a id="json-generator-icon" target="_blank" href="/FairAssessor/generator">Check Our <img width="50px" src="/FairAssessor/images/json.svg"/> Generator</a>
	
	</div>

</section>

<section id="loading-sec" class="loading-sec hidden">

	<div id="loading">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<p class="h2"> FAIR assessment is in progress</p>
					<div>
						<img src="/FairAssessor/images/spinner.svg"/>
					</div>		
				</div>
			</div>
		</div>
	</div>
</section>

<section class="heading-sec">

    <div class="text-block">
        <h4 class="text-center">NanoSafety Data Reusability FAIR Assessment</h4>
        <h6 class="text-center">Using Maturity Indicators</h6>
    </div>

</section>

<section class="search-sec">
    <div class="container">
        <form action="#" method="post" novalidate="novalidate">
            <div class="row">
                <div class="col-lg-12">
                    <div class="row">
                        <div class="col-lg-8 col-md-8 col-sm-12 p-0">
                            <input id="url" name="url" type="text" value="http://127.0.0.1/fair/index.html" class="form-control search-slt" placeholder="Enter Dataset URL">
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-12 p-0">
                            <select id="type" name="type" class="form-control search-slt" id="exampleFormControlSelect1">
                                <option value="dynamic">Select Loading type</option>
                                <option value="dynamic">Dynamic</option>
                                <option value="static">Static</option>
                            </select>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-12 p-0">
                            <button id="submit" type="button" class="btn btn-danger wrn-btn">Search</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</section>

<section id="results-sec" class="results-sec hidden">
	
	<div class="container">	
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 p-0">
				<div id="accordion">
					<div class="card">
						<div class="card-header" id="headingOne">
							<h5 class="mb-0">
								<button id="acc-header" class="btn btn-link" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
									<span id="fetch-status" class="badge badge-info float-right"></span>
								</button>
							</h5>
						</div>
						<div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">
							<div class="card-body">
								<table id="mi-table" class="table table-striped">
									<thead>
										<th>Maturity Indicator ID</th>
										<th>Maturity Indicator Title</th>
										<th>Comment</th>
										<th>Status</th>
									</thead>
									<tbody></tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<#include "/fragments/tail.ftl">