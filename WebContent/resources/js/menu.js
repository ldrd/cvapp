document.addEventListener("DOMContentLoaded", function(event) {
	document.getElementsByClassName("menu-search")[0].classList.add("hidden");
});

function showSearchResult() {
	document.getElementsByClassName("menu-search")[0].classList.remove("hidden");
}

function hideSearchResult() {
	document.getElementsByClassName("menu-search")[0].classList.add("hidden");
}