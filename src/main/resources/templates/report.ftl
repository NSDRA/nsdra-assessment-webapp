<#include "/fragments/head.ftl">

<section class="bg">

	<div id="json-generator-icon-wrapper">
	
		<a id="json-generator-icon" target="_blank" href="/generator">Check Our <img width="50px" src="/images/json.svg"/> Generator</a>
	
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

<section id="report-sec" class="results-sec report-sec">
	
	<div class="container">	
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 p-0">
				<div id="accordion">
				
					
					
				</div>
			</div>
		</div>
	</div>
</section>

<script src="/js/script.js"></script>

<script>
 var jsonStr = `${submission}`;
 renderResults(JSON.parse(jsonStr));
</script>
	
</body>
</html>