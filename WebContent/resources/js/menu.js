document.addEventListener("click", function(event) {
	if (event.target.closest(".menu-search")) return;
	if (event.target.closest(".search-bar")) return;
	
	document.querySelector(".menu-search").classList.add("hidden");
});

function switchSearchResultVisibility() {
	var element = document.getElementsByClassName("menu-search")[0];
	if (element.classList.contains("hidden"))
		element.classList.remove("hidden");
}