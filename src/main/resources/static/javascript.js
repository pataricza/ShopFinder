function populateDropDownsWithTime() {
	var zero = ':00';
	var half = ':30';
	var dropdown = $('.dropdown');
	
	for(var i = 0; i < 24; i++) {
		var time = i+zero;
		
		dropdown.append($('<option></option>')
                .attr('value', time)
                .text(time));
	}
}

window.onload = function() {
	populateDropDownsWithTime();
}