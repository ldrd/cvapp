document.addEventListener("DOMContentLoaded", function(event) {
	document.getElementsByClassName("menu-search")[0].style.visibility = "hidden";
});

function showSearchResult() {
	document.getElementsByClassName("menu-search")[0].style.visibility = "visible";
}
/*
function hideSearchResult() {
	document.getElementsByClassName("menu-search")[0].style.visibility = "hidden";
}*/