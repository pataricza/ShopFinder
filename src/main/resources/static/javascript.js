function populateDropDownsWithTime() {
	var zero = ':00';
	var half = ':30';
	var dropdown = $('.dropdown');
	
	for(var i = 0; i < 48; i++) {
		var time;
		
		if(i % 2 == 0) {
			if(i < 20) {
				time = '0' + i/2 + zero;
			} else {
				time = i/2 + zero;
			}
		} else {
			if(i < 20) {
				time = '0' + Math.floor(i/2) +half;
			} else {
				time = Math.floor(i/2) + half;
			}
		}
		
		dropdown.append($('<option></option>')
                .attr('value', time)
                .text(time));
	}
}

window.onload = function() {
	populateDropDownsWithTime();
}