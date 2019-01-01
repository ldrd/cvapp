document.addEventListener("DOMContentLoaded", function(event) {
	document.getElementsByClassName("menu-search")[0].style.visibility = "hidden";
	document.addEventListener("click", function(event) {
		if (event.target.closest(".menu-search")) return;
		if (event.target.closest(".search-bar")) return;

		document.getElementsByClassName("menu-search")[0].style.visibility = "hidden";
	});
});

function showSearchResult() {
	document.getElementsByClassName("menu-search")[0].style.visibility = "visible";
}
/*
function hideSearchResult() {
	document.getElementsByClassName("menu-search")[0].style.visibility = "hidden";
}*/