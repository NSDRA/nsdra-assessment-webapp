<#include "/fragments/head.ftl">

<section class="bg">

	<div id="json-generator-icon-wrapper">
	
		<a id="json-generator-icon" target="_blank" href="/metadata-generator">Check Our <img width="50px" src="/images/json.svg"/> Generator</a>
	
	</div>

</section>

<section id="loading-sec" class="loading-sec hidden">

	<div id="loading">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<p class="h2"> Reusability assessment is in progress</p>
					<div>
						<img src="/images/spinner.svg"/>
					</div>		
				</div>
			</div>
		</div>
	</div>
</section>

<section class="heading-sec">

    <div class="text-block">
        <h4 class="text-center">NanoSafety Data Reusability Assessment</h4>
        <h6 class="text-center">Using FAIR Maturity Indicators</h6>
    </div>
</section>

<section id="search-sec" class="search-sec">
    <div class="container">
        <form action="#" method="post" novalidate="novalidate">
            <div class="row">
                <div class="col-lg-12">
                    <div class="row">
                        <div class="col-lg-10 col-md-10 col-sm-12 p-0">
                            <input id="url" name="url" type="text" value="http://127.0.0.1:8086/test.html" class="form-control search-slt" placeholder="Enter Dataset URL">
                        </div>
                        <div class="hidden col-lg-2 col-md-2 col-sm-12 p-0">
                            <select hidden="hidden" id="type" name="type" class="form-control search-slt" id="exampleFormControlSelect1">
                                <option value="static">Select Loading type</option>
                                <option value="dynamic">Dynamic</option>
                                <option value="static">Static</option>
                            </select>
                        </div>
                        <div class="col-lg-2 col-md-2 col-sm-12 p-0">
                            <button id="submit" type="button" class="btn btn-danger wrn-btn">Assess</button>
                        </div>
                    </div>
                </div>
            </div>
			<div class="row">
                <div class="col-lg-12 p-0">	
					<div class="form-check p-0">
						<input type="checkbox" id="presistResults" class="form-check-input presist-results-check" name="presistResults">
						<label for="presistResults" class="form-check-label presist-results-label">Save the results of the assessment for later use</label>
					</div>
				</div>
			</div>
        </form>
    </div>
</section>


<section id="results-sec" class="results-sec">
	
	<div class="container">	
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 p-0">
				<div id="accordion">
					<#assign mi_lists = lists?eval>
					
					<#list mi_lists.lists as mi_list>
						<div class="card">
							<div class="card-header" id="headingOne">
								<h5 class="mb-0">
									<button id="acc-header-${mi_list.reference["reference id"]["SHA-256"][0..9]}" class="acc-header btn btn-link collapsed" data-toggle="collapse" data-target="#collapseOne-${mi_list.reference["reference id"]["SHA-256"][0..9]}" aria-expanded="false" aria-controls="collapseOne-${mi_list.reference["reference id"]["SHA-256"][0..9]}">
										<span class="list-title float-left">${mi_list.title}</span> <span id="fetch-status-${mi_list.reference["reference id"]["SHA-256"][0..9]}" class="badge badge-info fetch-status float-right">${mi_list.reference["reference id"]["SHA-256"][0..9]}</span>
									</button>
								</h5>
							</div>
							<div id="collapseOne-${mi_list.reference["reference id"]["SHA-256"][0..9]}" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
								<div class="card-body">
									<table id="mi-table-${mi_list.reference["reference id"]["SHA-256"][0..9]}" class="table table-striped">
										<thead>
											<th>Maturity Indicator ID</th>
											<th>Maturity Indicator Title</th>
											<th>Comment</th>
											<th><input type="checkbox" class="selectall" name="selectall"></th>
										</thead>
										<tbody>
											<#list mi_list["maturity indicators"] as mi>
												<tr class="mi-item">
													<td><a href="${mi.url}" target="_blank">${mi.mi_id}</a></td>
													<td>${mi.name}</td>
													<td></td>
													<td>
														<input type="hidden" name="mi-id" value="${mi.mi_id}" />
														<input type="hidden" name="mi-title" value="${mi.name}" />
														<input type="hidden" name="mi-url" value="${mi.url}" />
														<input type="hidden" name="mi-variable" value="${mi.variable}" />
														<input type="hidden" name="mi-list-id" value="${mi_list.id}" />
														<input type="hidden" name="mi-list-title" value="${mi_list.title}" />
														<input type="hidden" name="mi-list-ref-url" value="${mi_list.reference["reference id"]["URL"]}" />
														<input type="checkbox" name="results"/>
													</td>
												</tr>
											</#list>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</#list>
					
				</div>
			</div>
		</div>
	</div>
</section>

<#include "/fragments/tail.ftl">