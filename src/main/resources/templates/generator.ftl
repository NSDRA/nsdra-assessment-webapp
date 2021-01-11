<#include "/fragments/generator-head.ftl">

		<div class="panel unique-color intro-panel">
		
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="text-block intro-text">
							<h2 class="text-center">NanoSafety Data Reusability FAIR Assessment</h2>
							<h4 class="text-center">Using Maturity Indicators</h4>
						</div>
					</div>
				</div>
			</div>
		</div>
		<main class="jsonld-gen-form">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						<div class="jumbotron">
							<h2 class="h2-responsive"><strong>JSON-LD Generator Form</strong></h2>

							<div class="card-block">

								<!--Body-->
								<form id="form-generator">

									<div class="md-form">
										<input type="text" id="ds-title" class="form-control">
										<label for="ds-title" class="">Dataset Title</label>
									</div>
									
									<div class="md-form">
										<input type="text" id="ds-id" class="form-control">
										<label for="ds-id" class="">Dataset Unique ID (RDF IRI, DOI)</label>
									</div>
									
									<div class="md-form">
										<input type="text" id="ds-url" class="form-control">
										<label for="ds-url" class="">Dataset URL</label>
									</div>
									
									<div class="md-form">
										<input type="text" id="ds-citation" class="form-control">
										<label for="ds-citation" class="">Dataset Citation (publication)</label>
									</div>
									
									<h4 class="h4-responsive">Do you provide these physiochemical properties?</h4>

									<div id="switches">
										<div class="row pchem">
											<div class="col-md-7">
												<div class="custom-control custom-switch">
													<input type="checkbox" class="custom-control-input" id="pchem-size-name">
													<label class="custom-control-label" for="pchem-size-name"><strong>Size</strong></label>
												</div>
											</div>
											
											<div class="col-md-5">
												<div class="custom-control custom-switch">
													<input type="checkbox" class="custom-control-input" id="pchem-size-name-unit" disabled>
													<label class="custom-control-label" for="pchem-size-name-unit">Units are provided</label>
												</div>
											</div>
										</div>
										
										<div class="row pchem">
											<div class="col-md-7">
												<div class="custom-control custom-switch">
													<input type="checkbox" class="custom-control-input" id="pchem-ssa-name">
													<label class="custom-control-label" for="pchem-ssa-name"><strong>Specific Surface Area</strong></label>
												</div>
											</div>
											
											<div class="col-md-5">
												<div class="custom-control custom-switch">
													<input type="checkbox" class="custom-control-input" id="pchem-ssa-name-unit" disabled>
													<label class="custom-control-label" for="pchem-ssa-name-unit">Units are provided</label>
												</div>
											</div>
										</div>

										<div class="row pchem">
											<div class="col-md-7">
												<div class="custom-control custom-switch">
													<input type="checkbox" class="custom-control-input" id="pchem-zeta-name">
													<label class="custom-control-label" for="pchem-zeta-name"><strong>Surface Charge (Zeta Potential)</strong></label>
												</div>
											</div>
											
											<div class="col-md-5">
												<div class="custom-control custom-switch">
													<input type="checkbox" class="custom-control-input" id="pchem-zeta-name-unit" disabled>
													<label class="custom-control-label" for="pchem-zeta-name-unit">Units are provided</label>
												</div>
											</div>
										</div>


										<div class="row pchem">
											<div class="col-md-7">
												<div class="custom-control custom-switch">
													<input type="checkbox" class="custom-control-input" id="pchem-shape-name">
													<label class="custom-control-label" for="pchem-shape-name"><strong>Shape (Aspect Ratio/Circularity)</strong></label>
												</div>
											</div>
											
											<div class="col-md-5">
												<div class="custom-control custom-switch">
													<input type="checkbox" class="custom-control-input" id="pchem-shape-name-unit" disabled>
													<label class="custom-control-label" for="pchem-shape-name-unit">Units are provided</label>
												</div>
											</div>
										</div>									
									</div>
									
									<div class="text-xs-left">
										<button id="resetForm" type="reset" class="btn btn-danger">Reset Form</button>
									</div>
								</form>

								<div class="m-y-2">
									<p class="small-text small-thin-text" style="font-weight:300">JSON-LD will be generated as you fill-in the form</p>
								</div>

							</div>
							<!--Naked Form-->

						</div> <!-- jumbotron -->
					</div>
					<div class="col-md-6">
					
						<div class="jumbotron auto-generate">
							<h2 class="h2-responsive"><strong>JSON-LD Output</strong></h2>

							<div class="card-block">
							
								<div class="md-form">
									 <i class="fa fa-pencil prefix"></i>
									<textarea type="text" id="generated-jsonld" class="md-textarea"></textarea>
									<label for="generated-jsonld">Don't type here</label>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>	

<#include "/fragments/generator-tail.ftl">