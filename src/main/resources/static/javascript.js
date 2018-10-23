var button = document.getElementById("detailButton");

button.onclick = function() {
    var style = $("#details").css("display");
    
    if (style === "none") {
    	$("#details").css("display", "block");
    	button.innerHTML = "Rejtsd el!";
    } else {
    	$("#details").css("display", "none");
    	button.innerHTML = "Mutasd!";
    }
} 